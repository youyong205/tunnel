package com.longitudinalFault;

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

public class LongitudinalFaultAction extends LineChartAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(LongitudinalFaultAction.class);

	private List<LongitudinalFault> m_longitudinalFaults;

	private int m_longitudinalFaultId;

	private LongitudinalFaultService m_longitudinalFaultService;

	private LiningRingService m_liningRingService;

	private LongitudinalFault m_longitudinalFault = new LongitudinalFault();

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

	private LongitudinalFault convert(Cell[] cells) {
		try {
			LongitudinalFault longitudinalFault = new LongitudinalFault();
			String name = convertToString(cells[0]);
			int blockIndex = convertToInteger(cells[1]);
			Date date = convertToDate(cells[2]);
			int type = convertToInteger(cells[3]);
			double value = convertToDouble(cells[4]);
			int serious = convertToInteger(cells[5]);
			String des = "";

			if (cells.length >= 7) {
				des = convertToString(cells[6]);
			}

			LiningRingConstruction construction = m_liningRingConstructionService.findByName(name);

			if (construction != null) {
				longitudinalFault.setTunnelId(construction.getTunnelId());
				longitudinalFault.setTunnelSectionId(construction.getTunnelSectionId());
				longitudinalFault.setLiningRingConstructionId(construction.getId());
				longitudinalFault.setBlockIndex(blockIndex);
				longitudinalFault.setDate(date);
				longitudinalFault.setType(type);
				longitudinalFault.setValue(value);
				longitudinalFault.setSerious(serious);
				longitudinalFault.setDes(des);
				return longitudinalFault;
			}
		} catch (Exception e) {
			m_logger.error(e);
		}

		return null;
	}

	@Override
	public String getActionModule() {
		return Modules.s_longitudinalFault_model;
	}

	public BatchInsertResult getBatchInsertResult() {
		return m_batchInsertResult;
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

	public LongitudinalFault getLongitudinalFault() {
		return m_longitudinalFault;
	}

	public List<LongitudinalFault> getLongitudinalFaults() {
		return m_longitudinalFaults;
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

	public String longitudinalFaultAdd() {
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

	public String longitudinalFaultAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_longitudinalFault_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int id = m_longitudinalFaultService.insertLongitudinalFault(m_longitudinalFault);
			if (id > 0) {
				Log log = createLog(Modules.s_longitudinalFault_model, Operation.s_operation_add, m_longitudinalFault);

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

	public String longitudinalFaultBatchAdd() {
		return SUCCESS;
	}

	public String longitudinalFaultBatchAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_longitudinalFault_model, Operation.s_operation_add));
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
					LongitudinalFault defarmation = convert(cols);

					if (defarmation != null) {
						m_longitudinalFaultService.insertLongitudinalFault(defarmation);
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

	public String longitudinalFaultBatchDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_longitudinalFault_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			StringBuilder sb = new StringBuilder();
			for (int id : m_deleteId) {
				if (id > 0) {
					m_longitudinalFaultService.deleteLongitudinalFault(id);
					sb.append(id).append(",");
				}
			}
			Log log = createLog(Modules.s_longitudinalFault_model, Operation.s_operation_batch_delete, sb.toString());

			m_logService.insertLog(log);
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String longitudinalFaultDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_longitudinalFault_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_longitudinalFault = m_longitudinalFaultService.findByPK(m_longitudinalFaultId);
			int count = m_longitudinalFaultService.deleteLongitudinalFault(m_longitudinalFaultId);
			if (count > 0) {
				Log log = createLog(Modules.s_longitudinalFault_model, Operation.s_operation_delete, m_longitudinalFaultId);

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

	public String longitudinalFaultList() {
		Authority auth = checkAuthority(buildResource(Modules.s_longitudinalFault_model, Operation.s_operation_detail));
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
			m_totalSize = m_longitudinalFaultService.querySizeByTunnelAndSection(m_tunnelId, m_tunnelSectionId,
			      m_liningRingConstructionId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_longitudinalFaults = m_longitudinalFaultService.queryLimitedLongitudinalFaults(m_tunnelId,
			      m_tunnelSectionId, m_liningRingConstructionId, start, SIZE);
			for (LongitudinalFault longitudinalFault : m_longitudinalFaults) {
				longitudinalFault.setTunnel(m_tunnelService.findByPK(longitudinalFault.getTunnelId()));
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String longitudinalFaultState() {
		Authority auth = checkAuthority(buildResource(Modules.s_longitudinalFault_model, Operation.s_operation_detail));
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
				upIds.add(construction.getLongitudinalFaultId());
			} else {
				downs.add(construction);
				downIds.add(construction.getLongitudinalFaultId());
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
		List<LongitudinalFault> longitudinalFaults = m_longitudinalFaultService.queryByIds(idList);
		Map<Integer, Double> idToMaxValue = new LinkedHashMap<Integer, Double>();
		Map<Integer, Double> idToAllValue = new LinkedHashMap<Integer, Double>();
		Map<Double, Double> sequenceToMaxValue = new LinkedHashMap<Double, Double>();
		Map<Double, Double> sequenceToAllValue = new LinkedHashMap<Double, Double>();

		for (LongitudinalFault longitudinalFault : longitudinalFaults) {
			int liningRingConstructionId = longitudinalFault.getLiningRingConstructionId();
			double value = longitudinalFault.getValue();

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
	
	public String longitudinalFaultQuery() {
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
		m_longitudinalFaults = m_longitudinalFaultService.queryLongitudinalFaultByDuration(m_liningRingConstructionId,
		      m_start, m_end);

		Map<Integer, Map<Long, Double>> allDatas = new TreeMap<Integer, Map<Long, Double>>();
		Map<Long, Double> all = new LinkedHashMap<Long, Double>();
		if (m_longitudinalFaults != null) {
			for (LongitudinalFault longitudinalFault : m_longitudinalFaults) {
				int blockIndex = longitudinalFault.getBlockIndex();
				Date date = longitudinalFault.getDate();
				long time = formatTime(date);

				Map<Long, Double> datas = allDatas.get(blockIndex);

				if (datas == null) {
					datas = new LinkedHashMap<Long, Double>();
					allDatas.put(blockIndex, datas);
				}
				Double value = all.get(time);
				double fault = longitudinalFault.getValue();

				if (value == null) {
					all.put(time, fault);
				} else {
					all.put(time, value + fault);
				}
				datas.put(time, fault);
			}
		}
		m_liningRingConstruction = m_liningRingConstructionService.findByPK(m_liningRingConstructionId);

		m_lineChart.addLong("所有块", all);
		for (Entry<Integer, Map<Long, Double>> entry : allDatas.entrySet()) {
			m_lineChart.addLong("第" + entry.getKey() + "块", entry.getValue());
		}
		return SUCCESS;
	}

	public String longitudinalFaultUpdate() {
		try {
			m_liningRings = m_liningRingService.queryAllLiningRings();
			m_longitudinalFault = m_longitudinalFaultService.findByPK(m_longitudinalFaultId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(
			      m_longitudinalFault.getTunnelId(), 0, Integer.MAX_VALUE);
			m_liningRingConstructions = m_liningRingConstructionService.queryLimitedLiningRingConstructions(
			      m_longitudinalFault.getTunnelId(), m_longitudinalFault.getTunnelSectionId(), 0, Integer.MAX_VALUE);
			int liningRingConstructionId = m_longitudinalFault.getLiningRingConstructionId();
			int liningRingId = m_liningRingConstructionService.findByPK(liningRingConstructionId).getLiningRingId();
			m_liningRingBlocks = m_liningRingBlockService.queryByLiningRingId(liningRingId);
			if (m_longitudinalFault != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String longitudinalFaultUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_longitudinalFault_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int count = m_longitudinalFaultService.updateLongitudinalFault(m_longitudinalFault);
			if (count > 0) {
				Log log = createLog(Modules.s_longitudinalFault_model, Operation.s_operation_update, m_longitudinalFault);

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

	public String queryAllLongitudinalFaults() {
		m_longitudinalFaults = m_longitudinalFaultService.queryLimitedLongitudinalFaults(m_tunnelId, m_tunnelSectionId,
		      0, 0, Integer.MAX_VALUE);

		return SUCCESS;
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

	public void setLongitudinalFault(LongitudinalFault longitudinalFault) {
		m_longitudinalFault = longitudinalFault;
	}

	public void setLongitudinalFaultId(int longitudinalFaultId) {
		m_longitudinalFaultId = longitudinalFaultId;
	}

	public void setLongitudinalFaultService(LongitudinalFaultService longitudinalFaultService) {
		m_longitudinalFaultService = longitudinalFaultService;
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