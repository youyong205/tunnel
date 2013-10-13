package com.cracks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;

import com.Authority;
import com.BatchInsertResult;
import com.LineChart;
import com.LineChart.ChartItem;
import com.LineChartAction;
import com.Modules;
import com.Operation;
import com.TimeLineChart;
import com.liningRing.LiningRing;
import com.liningRing.LiningRingBlock;
import com.liningRing.LiningRingBlockService;
import com.liningRing.LiningRingService;
import com.liningRingConstruction.LiningRingConstruction;
import com.liningRingConstruction.LiningRingConstructionService;
import com.log.Log;
import com.tunnel.Tunnel;
import com.tunnel.TunnelService;
import com.tunnelSection.TunnelSection;
import com.tunnelSection.TunnelSectionService;

public class CracksAction extends LineChartAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(CracksAction.class);

	private List<Cracks> m_crackss;

	private int m_cracksId;

	private CracksService m_cracksService;

	private LiningRingService m_liningRingService;

	private Cracks m_cracks = new Cracks();

	private int m_tunnelId;

	private int m_tunnelSectionId;

	private int m_liningRingConstructionId;

	private TunnelService m_tunnelService;

	private TunnelSectionService m_tunnelSectionService;

	private List<LiningRingBlock> m_liningRingBlocks;

	private LiningRingBlockService m_liningRingBlockService;

	private LiningRingConstructionService m_liningRingConstructionService;

	private List<Tunnel> m_tunnels;

	private List<TunnelSection> m_tunnelSections;

	private List<LiningRing> m_liningRings;

	private List<LiningRingConstruction> m_liningRingConstructions;

	private int[] m_deleteId = new int[SIZE];

	private BatchInsertResult m_batchInsertResult = new BatchInsertResult();

	private void buildChart(TimeLineChart lineChart, List<Cracks> crackss, CracksFunction function) {
		Map<Integer, Map<Long, Double>> allDatas = new TreeMap<Integer, Map<Long, Double>>();
		Map<Long, Double> all = new LinkedHashMap<Long, Double>();
		if (crackss != null) {
			for (Cracks cracks : crackss) {
				int blockIndex = cracks.getBlockIndex();
				Date date = cracks.getDate();
				long time = formatTime(date);

				Map<Long, Double> datas = allDatas.get(blockIndex);

				if (datas == null) {
					datas = new LinkedHashMap<Long, Double>();
					allDatas.put(blockIndex, datas);
				}

				Double value = all.get(time);
				double width = function.getValue(cracks);
				if (value == null) {
					all.put(time, width);
				} else {
					all.put(time, value + width);
				}

				datas.put(time, width);
			}
		}

		lineChart.addLong("所有块", all);
		for (Entry<Integer, Map<Long, Double>> entry : allDatas.entrySet()) {
			lineChart.addLong("第" + entry.getKey() + "块", entry.getValue());
		}
	}

	private Item buildData(List<LiningRingConstruction> constructions, List<Cracks> crackss, CracksFunction function) {
		Map<Integer, Double> idToMaxValue = new LinkedHashMap<Integer, Double>();
		Map<Integer, Double> idToAllValue = new LinkedHashMap<Integer, Double>();
		Map<Double, Double> sequenceToMaxValue = new LinkedHashMap<Double, Double>();
		Map<Double, Double> sequenceToAllValue = new LinkedHashMap<Double, Double>();

		for (Cracks cracks : crackss) {
			int liningRingConstructionId = cracks.getLiningRingConstructionId();
			double value = function.getValue(cracks);

			findOrCreateMax(idToMaxValue, liningRingConstructionId, value);
			findOrCreateSum(idToAllValue, liningRingConstructionId, value);
		}
		for (LiningRingConstruction construction : constructions) {
			double sequence = construction.getSequence();
			int id = construction.getId();
			Double maxValue = idToMaxValue.get(id);
			Double allValue = idToAllValue.get(id);

			if (maxValue == null) {
				sequenceToMaxValue.put(sequence, 0.0);
			} else {
				sequenceToMaxValue.put(sequence, maxValue);
			}
			if (allValue == null) {
				sequenceToAllValue.put(sequence, 0.0);
			} else {
				sequenceToAllValue.put(sequence, allValue);
			}
		}
		Item item = new Item();
		item.setSequenceToAllValue(sequenceToAllValue);
		item.setSequenceToMaxValue(sequenceToMaxValue);

		return item;
	}

	private void buildLineChart(LineChart lineChart, List<LiningRingConstruction> constructions, List<String> ids) {
		List<Integer> idList = convertToString(ids);
		List<Cracks> crackss = m_cracksService.queryByIds(idList);
		CracksFunction number = new CracksFunction() {

			@Override
			public double getValue(Cracks cracks) {
				return cracks.getNumber();
			}
		};
		CracksFunction width = new CracksFunction() {

			@Override
			public double getValue(Cracks cracks) {
				return cracks.getWidth();
			}
		};
		Item numberItem = buildData(constructions, crackss, number);
		Item widthItem = buildData(constructions, crackss, width);

		List<ChartItem> items = new ArrayList<ChartItem>();

		items.add(new ChartItem("裂缝数量单块最大值", numberItem.getSequenceToMaxValue()));
		items.add(new ChartItem("裂缝数量所有块总和", numberItem.getSequenceToAllValue()));
		items.add(new ChartItem("裂缝宽度单块最大值", widthItem.getSequenceToMaxValue()));
		items.add(new ChartItem("裂缝宽度所有块总和", widthItem.getSequenceToAllValue()));
		lineChart.add(items);
	}

	private Cracks convert(Cell[] cells) {
		try {
			Cracks cracks = new Cracks();
			String name = convertToString(cells[0]);
			int blockIndex = convertToInteger(cells[1]);
			Date date = convertToDate(cells[2]);
			String type = convertToString(cells[3]);
			int number = convertToInteger(cells[4]);
			double length = convertToDouble(cells[5]);
			double width = convertToDouble(cells[6]);
			double angle = convertToDouble(cells[7]);
			double dip = convertToDouble(cells[8]);
			int serious = convertToInteger(cells[9]);
			String des = "";

			if (cells.length >= 11) {
				des = convertToString(cells[10]);
			}

			LiningRingConstruction construction = m_liningRingConstructionService.findByName(name);

			if (construction != null) {
				cracks.setTunnelId(construction.getTunnelId());
				cracks.setTunnelSectionId(construction.getTunnelSectionId());
				cracks.setLiningRingConstructionId(construction.getId());
				cracks.setBlockIndex(blockIndex);
				cracks.setType(type);
				cracks.setDate(date);
				cracks.setNumber(number);
				cracks.setWidth(width);
				cracks.setLength(length);
				cracks.setAngle(angle);
				cracks.setDip(dip);
				cracks.setDes(des);
				cracks.setSerious(serious);
				return cracks;
			}
		} catch (Exception e) {
			m_logger.error(e);
		}

		return null;
	}

	public String cracksAdd() {
		m_tunnels = m_tunnelService.queryAllTunnels();
		m_liningRings = m_liningRingService.queryAllLiningRings();
		if (m_tunnelId == 0) {
			m_tunnelId = m_tunnelService.queryDefaultTunnelId();
		}
		m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);
		if (m_tunnelSectionId == 0 && m_tunnelSections != null && m_tunnelSections.size() > 0) {
			m_tunnelSectionId = m_tunnelSections.get(0).getId();
		}
		if (m_tunnelSectionId > 0) {
			m_liningRingConstructions = m_liningRingConstructionService.queryLimitedLiningRingConstructions(m_tunnelId,
			      m_tunnelSectionId, 0, Integer.MAX_VALUE);
		} else {
			m_liningRingConstructions = new ArrayList<LiningRingConstruction>();
		}
		if (m_liningRingConstructions.size() > 0) {
			int liningRingId = m_liningRingConstructions.get(0).getLiningRingId();
			m_liningRingBlocks = m_liningRingBlockService.queryByLiningRingId(liningRingId);
		}
		return SUCCESS;
	}

	public String cracksAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_cracks_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int id = m_cracksService.insertCracks(m_cracks);
			if (id > 0) {
				Log log = createLog(Modules.s_cracks_model, Operation.s_operation_add, m_cracks);

				m_logService.insertLog(log);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String cracksBatchAdd() {
		return SUCCESS;
	}

	public String cracksBatchAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_cracks_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}

		if (m_uploadFile.getFile() != null) {
			int i;
			Sheet sheet;
			Workbook book = null;
			try {
				book = Workbook.getWorkbook(m_uploadFile.getFile());
				sheet = book.getSheet(0);
				i = 1;
				while (true) {
					Cell[] cols = sheet.getRow(i);
					Cracks defarmation = convert(cols);

					if (defarmation != null) {
						m_cracksService.insertCracks(defarmation);
						m_batchInsertResult.addSuccess();
					} else {
						m_batchInsertResult.addFail(i + 1);
					}
					i++;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
			} catch (Exception e) {
				m_logger.error(e);
			} finally {
				book.close();
			}
		}
		return SUCCESS;
	}

	public String cracksBatchDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_cracks_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			StringBuilder sb = new StringBuilder();
			for (int id : m_deleteId) {
				if (id > 0) {
					m_cracksService.deleteCracks(id);
					sb.append(id).append(",");
				}
			}
			Log log = createLog(Modules.s_cracks_model, Operation.s_operation_batch_delete, sb.toString());

			m_logService.insertLog(log);
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String cracksDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_cracks_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_cracks = m_cracksService.findByPK(m_cracksId);
			int count = m_cracksService.deleteCracks(m_cracksId);
			if (count > 0) {
				Log log = createLog(Modules.s_cracks_model, Operation.s_operation_delete, m_cracksId);

				m_logService.insertLog(log);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String cracksList() {
		Authority auth = checkAuthority(buildResource(Modules.s_cracks_model, Operation.s_operation_detail));
		if (auth != null) {
			return auth.getName();
		}
		try {
			if (m_tunnelId == 0) {
				m_tunnelId = m_tunnelService.queryDefaultTunnelId();
			}
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0,
			      Integer.MAX_VALUE);
			if (m_tunnelSectionId == 0 && m_tunnelSections != null && m_tunnelSections.size() > 0) {
				m_tunnelSectionId = m_tunnelSections.get(0).getId();
			}
			if (m_tunnelSectionId > 0) {
				m_liningRingConstructions = m_liningRingConstructionService.queryLimitedLiningRingConstructions(m_tunnelId,
				      m_tunnelSectionId, 0, Integer.MAX_VALUE);
			} else {
				m_liningRingConstructions = new ArrayList<LiningRingConstruction>();
			}
			m_totalSize = m_cracksService.querySizeByTunnelAndSection(m_tunnelId, m_tunnelSectionId,
			      m_liningRingConstructionId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_crackss = m_cracksService.queryLimitedCrackss(m_tunnelId, m_tunnelSectionId, m_liningRingConstructionId,
			      start, SIZE);
			for (Cracks cracks : m_crackss) {
				cracks.setTunnel(m_tunnelService.findByPK(cracks.getTunnelId()));
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String cracksQuery() {
		if (m_start == null || m_end == null) {
			m_end = new Date();

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);
			m_start = cal.getTime();
		}
		if (m_tunnelId == 0) {
			m_tunnelId = m_tunnelService.queryDefaultTunnelId();
		}
		m_tunnels = m_tunnelService.queryAllTunnels();
		m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);
		if (m_tunnelSectionId == 0 && m_tunnelSections != null && m_tunnelSections.size() > 0) {
			m_tunnelSectionId = m_tunnelSections.get(0).getId();
		}
		if (m_tunnelSectionId > 0) {
			m_liningRingConstructions = m_liningRingConstructionService.queryLimitedLiningRingConstructions(m_tunnelId,
			      m_tunnelSectionId, 0, Integer.MAX_VALUE);
		} else {
			m_liningRingConstructions = new ArrayList<LiningRingConstruction>();
		}

		if (m_liningRingConstructionId == 0 && m_liningRingConstructions.size() > 0) {
			m_liningRingConstructionId = m_liningRingConstructions.get(0).getId();
		}

		m_lineChart = new TimeLineChart();
		m_crackss = m_cracksService.queryCracksByDuration(m_liningRingConstructionId, m_start, m_end);

		CracksFunction widthImpl = new CracksFunction() {
			@Override
			public double getValue(Cracks cracks) {
				return cracks.getWidth();
			}
		};

		CracksFunction numberImple = new CracksFunction() {
			@Override
			public double getValue(Cracks cracks) {
				return cracks.getNumber();
			}
		};
		m_lineChart = new TimeLineChart();
		m_secondLineChart = new TimeLineChart();
		buildChart(m_lineChart, m_crackss, widthImpl);
		buildChart(m_secondLineChart, m_crackss, numberImple);
		m_liningRingConstruction = m_liningRingConstructionService.findByPK(m_liningRingConstructionId);
		return SUCCESS;
	}

	public String cracksState() {
		Authority auth = checkAuthority(buildResource(Modules.s_cracks_model, Operation.s_operation_detail));
		if (auth != null) {
			return auth.getName();
		}
		if (m_tunnelId == 0) {
			m_tunnelId = m_tunnelService.queryDefaultTunnelId();
		}
		m_tunnels = m_tunnelService.queryAllTunnels();
		m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);
		m_liningRingConstructions = m_liningRingConstructionService.queryLimitedLiningRingConstructions(m_tunnelId,
		      m_tunnelSectionId, 0, Integer.MAX_VALUE);
		List<LiningRingConstruction> ups = new ArrayList<LiningRingConstruction>();
		List<LiningRingConstruction> downs = new ArrayList<LiningRingConstruction>();
		List<String> upIds = new ArrayList<String>();
		List<String> downIds = new ArrayList<String>();

		for (LiningRingConstruction construction : m_liningRingConstructions) {
			String lineType = construction.getLineType();
			if (("上行").equals(lineType)) {
				ups.add(construction);
				upIds.add(construction.getCracksId());
			} else {
				downs.add(construction);
				downIds.add(construction.getCracksId());
			}
		}
		m_generalChart = new LineChart();
		m_generalChart2 = new LineChart();
		buildLineChart(m_generalChart, ups, upIds);
		buildLineChart(m_generalChart2, downs, downIds);
		return SUCCESS;
	}

	public String cracksUpdate() {
		try {
			m_liningRings = m_liningRingService.queryAllLiningRings();
			m_cracks = m_cracksService.findByPK(m_cracksId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_cracks.getTunnelId(), 0,
			      Integer.MAX_VALUE);
			m_liningRingConstructions = m_liningRingConstructionService.queryLimitedLiningRingConstructions(
			      m_cracks.getTunnelId(), m_cracks.getTunnelSectionId(), 0, Integer.MAX_VALUE);
			int liningRingConstructionId = m_cracks.getLiningRingConstructionId();
			int liningRingId = m_liningRingConstructionService.findByPK(liningRingConstructionId).getLiningRingId();
			m_liningRingBlocks = m_liningRingBlockService.queryByLiningRingId(liningRingId);
			if (m_cracks != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String cracksUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_cracks_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int count = m_cracksService.updateCracks(m_cracks);
			if (count > 0) {
				Log log = createLog(Modules.s_cracks_model, Operation.s_operation_update, m_cracks);

				m_logService.insertLog(log);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	@Override
	public String getActionModule() {
		return Modules.s_cracks_model;
	}

	public BatchInsertResult getBatchInsertResult() {
		return m_batchInsertResult;
	}

	public Cracks getCracks() {
		return m_cracks;
	}

	public List<Cracks> getCrackss() {
		return m_crackss;
	}

	public List<LiningRingBlock> getLiningRingBlocks() {
		return m_liningRingBlocks;
	}

	public int getLiningRingConstructionId() {
		return m_liningRingConstructionId;
	}

	public List<LiningRingConstruction> getLiningRingConstructions() {
		return m_liningRingConstructions;
	}

	public List<LiningRing> getLiningRings() {
		return m_liningRings;
	}

	public int getParentLiningRingConstructionId() {
		return m_liningRingConstructionId;
	}

	public int getParentTunnelSectionId() {
		return m_tunnelSectionId;
	}

	public int getTunnelId() {
		return m_tunnelId;
	}

	public List<Tunnel> getTunnels() {
		return m_tunnels;
	}

	public int getTunnelSectionId() {
		return m_tunnelSectionId;
	}

	public List<TunnelSection> getTunnelSections() {
		return m_tunnelSections;
	}

	public String queryAllCrackss() {
		m_crackss = m_cracksService.queryLimitedCrackss(m_tunnelId, m_tunnelSectionId, 0, 0, Integer.MAX_VALUE);

		return SUCCESS;
	}

	public void setCracks(Cracks cracks) {
		m_cracks = cracks;
	}

	public void setCracksId(int cracksId) {
		m_cracksId = cracksId;
	}

	public void setCracksService(CracksService cracksService) {
		m_cracksService = cracksService;
	}

	public void setDeleteId(int[] deleteId) {
		m_deleteId = deleteId;
	}

	public void setLiningRingBlockService(LiningRingBlockService liningRingBlockService) {
		m_liningRingBlockService = liningRingBlockService;
	}

	public void setLiningRingConstructionId(int liningRingConstructionId) {
		m_liningRingConstructionId = liningRingConstructionId;
	}

	public void setLiningRingConstructionService(LiningRingConstructionService liningRingConstructionService) {
		m_liningRingConstructionService = liningRingConstructionService;
	}

	public void setLiningRingService(LiningRingService liningRingService) {
		m_liningRingService = liningRingService;
	}

	public void setTunnelId(int tunnelId) {
		m_tunnelId = tunnelId;
	}

	public void setTunnelSectionId(int tunnelSectionId) {
		m_tunnelSectionId = tunnelSectionId;
	}

	public void setTunnelSectionService(TunnelSectionService tunnelSectionService) {
		m_tunnelSectionService = tunnelSectionService;
	}

	public void setTunnelService(TunnelService tunnelService) {
		m_tunnelService = tunnelService;
	}

	public interface CracksFunction {

		double getValue(Cracks cracks);
	}

	private static class Item {
		Map<Double, Double> sequenceToMaxValue;

		Map<Double, Double> sequenceToAllValue;

		public Map<Double, Double> getSequenceToAllValue() {
			return sequenceToAllValue;
		}

		public Map<Double, Double> getSequenceToMaxValue() {
			return sequenceToMaxValue;
		}

		public void setSequenceToAllValue(Map<Double, Double> sequenceToAllValue) {
			this.sequenceToAllValue = sequenceToAllValue;
		}

		public void setSequenceToMaxValue(Map<Double, Double> sequenceToMaxValue) {
			this.sequenceToMaxValue = sequenceToMaxValue;
		}

	}

}