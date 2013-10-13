package com.girthFault;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;

import com.Authority;
import com.BatchInsertResult;
import com.LineChart;
import com.LineChartAction;
import com.Modules;
import com.Operation;
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

public class GirthFaultAction extends LineChartAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(GirthFaultAction.class);

	private List<GirthFault> m_girthFaults;

	private int m_girthFaultId;

	private GirthFaultService m_girthFaultService;

	private LiningRingService m_liningRingService;

	private GirthFault m_girthFault = new GirthFault();

	private int m_tunnelId;

	private int m_tunnelSectionId;

	private int m_liningRingConstructionId;

	private TunnelService m_tunnelService;

	private TunnelSectionService m_tunnelSectionService;

	private LiningRingBlockService m_liningRingBlockService;
	
	private List<LiningRingBlock> m_liningRingBlocks;

	private LiningRingConstructionService m_liningRingConstructionService;

	private List<Tunnel> m_tunnels;

	private List<TunnelSection> m_tunnelSections;

	private List<LiningRing> m_liningRings;

	private List<LiningRingConstruction> m_liningRingConstructions;

	private int[] m_deleteId = new int[SIZE];

	private BatchInsertResult m_batchInsertResult = new BatchInsertResult();

	private GirthFault convert(Cell[] cells) {
		try {
			GirthFault fault = new GirthFault();
			String name = convertToString(cells[0]);
			int blockIndex =  convertToInteger(cells[1]);
			Date date = convertToDate(cells[2]);
			int type = convertToInteger(cells[3]);
			double value = convertToDouble(cells[4]);
			String des = "";

			if (cells.length > 5) {
				des = convertToString(cells[5]);
			}

			LiningRingConstruction construction = m_liningRingConstructionService.findByName(name);

			if (construction != null) {
				fault.setTunnelId(construction.getTunnelId());
				fault.setTunnelSectionId(construction.getTunnelSectionId());
				fault.setLiningRingConstructionId(construction.getId());
				fault.setDate(date);
				fault.setBlockIndex(blockIndex);
				fault.setValue(value);
				fault.setType(type);
				fault.setDes(des);
				return fault;
			}
		} catch (Exception e) {
			m_logger.error(e);
		}

		return null;
	}

	@Override
	public String getActionModule() {
		return Modules.s_girthFault_model;
	}

	public BatchInsertResult getBatchInsertResult() {
		return m_batchInsertResult;
	}

	public GirthFault getGirthFault() {
		return m_girthFault;
	}

	public List<GirthFault> getGirthFaults() {
		return m_girthFaults;
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

	public String girthFaultAdd() {
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

	public String girthFaultAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_girthFault_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int id = m_girthFaultService.insertGirthFault(m_girthFault);
			if (id > 0) {
				Log log = createLog(Modules.s_girthFault_model, Operation.s_operation_add, m_girthFault);

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

	public String girthFaultBatchAdd() {
		return SUCCESS;
	}

	public String girthFaultBatchAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_girthFault_model, Operation.s_operation_add));
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
					GirthFault defarmation = convert(cols);

					if (defarmation != null) {
						m_girthFaultService.insertGirthFault(defarmation);
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

	public String girthFaultBatchDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_girthFault_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			StringBuilder sb = new StringBuilder();
			for (int id : m_deleteId) {
				if (id > 0) {
					m_girthFaultService.deleteGirthFault(id);
					sb.append(id).append(",");
				}
			}
			Log log = createLog(Modules.s_girthFault_model, Operation.s_operation_batch_delete, sb.toString());

			m_logService.insertLog(log);
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String girthFaultDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_girthFault_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_girthFault = m_girthFaultService.findByPK(m_girthFaultId);
			int count = m_girthFaultService.deleteGirthFault(m_girthFaultId);
			if (count > 0) {
				Log log = createLog(Modules.s_girthFault_model, Operation.s_operation_delete, m_girthFaultId);

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

	public String girthFaultList() {
		Authority auth = checkAuthority(buildResource(Modules.s_girthFault_model, Operation.s_operation_detail));
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
			m_totalSize = m_girthFaultService.querySizeByTunnelAndSection(m_tunnelId, m_tunnelSectionId,
			      m_liningRingConstructionId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_girthFaults = m_girthFaultService.queryLimitedGirthFaults(m_tunnelId, m_tunnelSectionId,
			      m_liningRingConstructionId, start, SIZE);
			for (GirthFault girthFault : m_girthFaults) {
				girthFault.setTunnel(m_tunnelService.findByPK(girthFault.getTunnelId()));
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String girthFaultUpdate() {
		try {
			m_liningRings = m_liningRingService.queryAllLiningRings();
			m_girthFault = m_girthFaultService.findByPK(m_girthFaultId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_girthFault.getTunnelId(), 0,
			      Integer.MAX_VALUE);
			m_liningRingConstructions = m_liningRingConstructionService.queryLimitedLiningRingConstructions(
			      m_girthFault.getTunnelId(), m_girthFault.getTunnelSectionId(), 0, Integer.MAX_VALUE);
			int liningRingConstructionId = m_girthFault.getLiningRingConstructionId();
			int liningRingId = m_liningRingConstructionService.findByPK(liningRingConstructionId).getLiningRingId();
			m_liningRingBlocks = m_liningRingBlockService.queryByLiningRingId(liningRingId);
			if (m_girthFault != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String girthFaultUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_girthFault_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int count = m_girthFaultService.updateGirthFault(m_girthFault);
			if (count > 0) {
				Log log = createLog(Modules.s_girthFault_model, Operation.s_operation_update, m_girthFault);

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

	public String girthFaultQuery() {
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

		m_lineChart = new LineChart();
		m_girthFaults = m_girthFaultService.queryGirthFaultByDuration(
		      m_liningRingConstructionId, m_start, m_end);

		Map<Long, Double> datas = new LinkedHashMap<Long, Double>();
		if (m_girthFaults != null) {
			for (GirthFault deformation : m_girthFaults) {
				Date date = deformation.getDate();

				datas.put(formatTime(date), deformation.getValue());
			}
		}
		m_liningRingConstruction = m_liningRingConstructionService.findByPK(m_liningRingConstructionId);
		m_lineChart.add("环缝错台", datas);

		m_girthFaults = m_girthFaultService.queryLimitedGirthFaults(m_tunnelId,
		      m_tunnelSectionId, m_liningRingConstructionId, 0, 1);

		if (m_girthFaults != null && m_girthFaults.size() == 1) {
			m_girthFault = m_girthFaults.get(0);
		}
		return SUCCESS;
	}
	public String queryAllGirthFaults() {
		m_girthFaults = m_girthFaultService.queryLimitedGirthFaults(m_tunnelId, m_tunnelSectionId, 0, 0,
		      Integer.MAX_VALUE);

		return SUCCESS;
	}

	public void setDeleteId(int[] deleteId) {
		m_deleteId = deleteId;
	}

	public void setGirthFault(GirthFault girthFault) {
		m_girthFault = girthFault;
	}

	public void setGirthFaultId(int girthFaultId) {
		m_girthFaultId = girthFaultId;
	}

	public void setGirthFaultService(GirthFaultService girthFaultService) {
		m_girthFaultService = girthFaultService;
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

	public List<LiningRingBlock> getLiningRingBlocks() {
   	return m_liningRingBlocks;
   }

	public void setLiningRingBlockService(LiningRingBlockService liningRingBlockService) {
   	m_liningRingBlockService = liningRingBlockService;
   }

}