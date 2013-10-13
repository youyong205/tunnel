package com.coverLoss;

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

public class CoverLossAction extends LineChartAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(CoverLossAction.class);

	private List<CoverLoss> m_coverLosss;

	private int m_coverLossId;

	private CoverLossService m_coverLossService;

	private LiningRingService m_liningRingService;

	private CoverLoss m_coverLoss = new CoverLoss();

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

	private CoverLoss convert(Cell[] cells) {
		try {
			CoverLoss coverLoss = new CoverLoss();
			String name = convertToString(cells[0]);
			int blockIndex = convertToInteger(cells[1]);
			Date date = convertToDate(cells[2]);
			String type = convertToString(cells[3]);
			String shape = convertToString(cells[4]);
			double width = convertToDouble(cells[5]);
			double height = convertToDouble(cells[6]);
			double depth = convertToDouble(cells[7]);
			double area = convertToDouble(cells[8]);
			int serious = convertToInteger(cells[9]);
			String des = "";

			if (cells.length >= 11) {
				des = convertToString(cells[10]);
			}

			LiningRingConstruction construction = m_liningRingConstructionService.findByName(name);

			if (construction != null) {
				coverLoss.setTunnelId(construction.getTunnelId());
				coverLoss.setTunnelSectionId(construction.getTunnelSectionId());
				coverLoss.setLiningRingConstructionId(construction.getId());
				coverLoss.setBlockIndex(blockIndex);
				coverLoss.setDate(date);
				coverLoss.setType(type);
				coverLoss.setShape(shape);
				coverLoss.setWidth(width);
				coverLoss.setHeight(height);
				coverLoss.setDepth(depth);
				coverLoss.setArea(area);
				coverLoss.setDes(des);
				coverLoss.setSerious(serious);
				return coverLoss;
			}
		} catch (Exception e) {
			m_logger.error(e);
		}

		return null;
	}

	public String coverLossAdd() {
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

	public String coverLossAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_coverLoss_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int id = m_coverLossService.insertCoverLoss(m_coverLoss);
			if (id > 0) {
				Log log = createLog(Modules.s_coverLoss_model, Operation.s_operation_add, m_coverLoss);

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

	public String coverLossBatchAdd() {
		return SUCCESS;
	}

	public String coverLossBatchAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_coverLoss_model, Operation.s_operation_add));
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
					CoverLoss defarmation = convert(cols);

					if (defarmation != null) {
						m_coverLossService.insertCoverLoss(defarmation);
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

	public String coverLossBatchDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_coverLoss_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			StringBuilder sb = new StringBuilder();
			for (int id : m_deleteId) {
				if (id > 0) {
					m_coverLossService.deleteCoverLoss(id);
					sb.append(id).append(",");
				}
			}
			Log log = createLog(Modules.s_coverLoss_model, Operation.s_operation_batch_delete, sb.toString());

			m_logService.insertLog(log);
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String coverLossDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_coverLoss_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_coverLoss = m_coverLossService.findByPK(m_coverLossId);
			int count = m_coverLossService.deleteCoverLoss(m_coverLossId);
			if (count > 0) {
				Log log = createLog(Modules.s_coverLoss_model, Operation.s_operation_delete, m_coverLossId);

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

	public String coverLossList() {
		Authority auth = checkAuthority(buildResource(Modules.s_coverLoss_model, Operation.s_operation_detail));
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
			m_totalSize = m_coverLossService.querySizeByTunnelAndSection(m_tunnelId, m_tunnelSectionId,
			      m_liningRingConstructionId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_coverLosss = m_coverLossService.queryLimitedCoverLosss(m_tunnelId, m_tunnelSectionId,
			      m_liningRingConstructionId, start, SIZE);
			for (CoverLoss coverLoss : m_coverLosss) {
				coverLoss.setTunnel(m_tunnelService.findByPK(coverLoss.getTunnelId()));
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String coverLossState() {
		Authority auth = checkAuthority(buildResource(Modules.s_coverLoss_model, Operation.s_operation_detail));
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
				upIds.add(construction.getCoverLossId());
			} else {
				downs.add(construction);
				downIds.add(construction.getCoverLossId());
			}
		}
		m_generalChart = new LineChart();
		m_generalChart2 = new LineChart();
		buildLineChart(m_generalChart, ups, upIds);
		buildLineChart(m_generalChart2, downs, downIds);
		return SUCCESS;
	}

	private void buildLineChart(LineChart lineChart, List<LiningRingConstruction> constructions, List<String> ids) {
		List<Integer> idList = convertToString(ids);
		List<CoverLoss> coverLosss = m_coverLossService.queryByIds(idList);
		Map<Integer, Double> idToMaxValue = new LinkedHashMap<Integer, Double>();
		Map<Integer, Double> idToAllValue = new LinkedHashMap<Integer, Double>();
		Map<Double, Double> sequenceToMaxValue = new LinkedHashMap<Double, Double>();
		Map<Double, Double> sequenceToAllValue = new LinkedHashMap<Double, Double>();

		for (CoverLoss coverLoss : coverLosss) {
			int liningRingConstructionId = coverLoss.getLiningRingConstructionId();
			double value = coverLoss.getDepth();

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
		List<ChartItem> items = new ArrayList<ChartItem>();

		items.add(new ChartItem("单块最大值", sequenceToMaxValue));
		items.add(new ChartItem("所有块总和", sequenceToAllValue));
		lineChart.add(items);
	}

	public String coverLossQuery() {
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
		m_coverLosss = m_coverLossService.queryCoverLossByDuration(m_liningRingConstructionId, m_start, m_end);

		Map<Integer, Map<Long, Double>> allDatas = new TreeMap<Integer, Map<Long, Double>>();
		Map<Long, Double> all = new LinkedHashMap<Long, Double>();
		if (m_coverLosss != null) {
			for (CoverLoss coverLoss : m_coverLosss) {
				int blockIndex = coverLoss.getBlockIndex();
				Date date = coverLoss.getDate();
				long time = formatTime(date);

				Map<Long, Double> datas = allDatas.get(blockIndex);

				if (datas == null) {
					datas = new LinkedHashMap<Long, Double>();
					allDatas.put(blockIndex, datas);
				}

				Double value = all.get(time);
				if (value == null) {
					value = coverLoss.getWidth();
					all.put(time, value);
				} else {
					all.put(time, value + coverLoss.getWidth());
				}

				datas.put(time, coverLoss.getWidth());
			}
		}
		m_liningRingConstruction = m_liningRingConstructionService.findByPK(m_liningRingConstructionId);

		m_lineChart.addLong("所有块", all);
		for (Entry<Integer, Map<Long, Double>> entry : allDatas.entrySet()) {
			m_lineChart.addLong("第" + entry.getKey() + "块", entry.getValue());
		}
		return SUCCESS;
	}

	public String coverLossUpdate() {
		try {
			m_liningRings = m_liningRingService.queryAllLiningRings();
			m_coverLoss = m_coverLossService.findByPK(m_coverLossId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_coverLoss.getTunnelId(), 0,
			      Integer.MAX_VALUE);
			m_liningRingConstructions = m_liningRingConstructionService.queryLimitedLiningRingConstructions(
			      m_coverLoss.getTunnelId(), m_coverLoss.getTunnelSectionId(), 0, Integer.MAX_VALUE);
			int liningRingConstructionId = m_coverLoss.getLiningRingConstructionId();
			int liningRingId = m_liningRingConstructionService.findByPK(liningRingConstructionId).getLiningRingId();
			m_liningRingBlocks = m_liningRingBlockService.queryByLiningRingId(liningRingId);
			if (m_coverLoss != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String coverLossUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_coverLoss_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int count = m_coverLossService.updateCoverLoss(m_coverLoss);
			if (count > 0) {
				Log log = createLog(Modules.s_coverLoss_model, Operation.s_operation_update, m_coverLoss);

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
		return Modules.s_coverLoss_model;
	}

	public BatchInsertResult getBatchInsertResult() {
		return m_batchInsertResult;
	}

	public CoverLoss getCoverLoss() {
		return m_coverLoss;
	}

	public List<CoverLoss> getCoverLosss() {
		return m_coverLosss;
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

	public String queryAllCoverLosss() {
		m_coverLosss = m_coverLossService.queryLimitedCoverLosss(m_tunnelId, m_tunnelSectionId, 0, 0, Integer.MAX_VALUE);

		return SUCCESS;
	}

	public void setCoverLoss(CoverLoss coverLoss) {
		m_coverLoss = coverLoss;
	}

	public void setCoverLossId(int coverLossId) {
		m_coverLossId = coverLossId;
	}

	public void setCoverLossService(CoverLossService coverLossService) {
		m_coverLossService = coverLossService;
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

}