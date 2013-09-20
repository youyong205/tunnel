package com.settlement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;

import com.Authority;
import com.BatchInsertResult;
import com.FileUploadAction;
import com.Modules;
import com.Operation;
import com.liningRing.LiningRing;
import com.liningRing.LiningRingService;
import com.liningRingConstruction.LiningRingConstruction;
import com.liningRingConstruction.LiningRingConstructionService;
import com.log.Log;
import com.tunnel.Tunnel;
import com.tunnel.TunnelService;
import com.tunnelSection.TunnelSection;
import com.tunnelSection.TunnelSectionService;

public class SettlementAction extends FileUploadAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(SettlementAction.class);

	private List<Settlement> m_settlements;

	private int m_settlementId;

	private SettlementService m_settlementService;

	private LiningRingService m_liningRingService;

	private Settlement m_settlement = new Settlement();

	private int m_tunnelId;

	private int m_tunnelSectionId;

	private int m_liningRingConstructionId;

	private TunnelService m_tunnelService;

	private TunnelSectionService m_tunnelSectionService;

	private LiningRingConstructionService m_liningRingConstructionService;

	private List<Tunnel> m_tunnels;

	private List<TunnelSection> m_tunnelSections;

	private List<LiningRing> m_liningRings;

	private List<LiningRingConstruction> m_liningRingConstructions;

	private SimpleDateFormat m_sdf = new SimpleDateFormat("yyyy-MM-dd");

	private int[] m_deleteId = new int[SIZE];

	private BatchInsertResult m_batchInsertResult = new BatchInsertResult();

	private Settlement convert(Cell[] cells) {
		try {
			Settlement defarmation = new Settlement();
			String name = cells[0].getContents();
			Date date = null;
			if (cells[1].getType() == CellType.DATE) {
				DateCell dateCell = (DateCell) cells[1];
				date = dateCell.getDate();
			} else {
				date = m_sdf.parse(cells[1].getContents());
			}
			String measuringPoing = cells[2].getContents();
			double value = Double.parseDouble(cells[3].getContents());
			String des = "";

			if (cells.length > 4) {
				des = cells[4].getContents();
			}

			LiningRingConstruction construction = m_liningRingConstructionService.findByName(name);

			if (construction != null) {
				defarmation.setTunnelId(construction.getTunnelId());
				defarmation.setTunnelSectionId(construction.getTunnelSectionId());
				defarmation.setLiningRingConstructionId(construction.getId());
				defarmation.setDate(date);
				defarmation.setMeasuringPoing(measuringPoing);
				defarmation.setValue(value);
				defarmation.setDes(des);
				return defarmation;
			}
		} catch (Exception e) {
			m_logger.error(e);
		}

		return null;
	}

	@Override
	public String getActionModule() {
		return Modules.s_settlement_model;
	}

	public BatchInsertResult getBatchInsertResult() {
		return m_batchInsertResult;
	}

	public int getLiningRingConstructionId() {
		return m_liningRingConstructionId;
	}

	public List<LiningRingConstruction> getLiningRingConstructions() {
		return m_liningRingConstructions;
	}

	public Settlement getSettlement() {
		return m_settlement;
	}

	public List<Settlement> getSettlements() {
		return m_settlements;
	}

	public List<LiningRing> getLiningRings() {
		return m_liningRings;
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

	public String settlementAdd() {
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
		return SUCCESS;
	}

	public String settlementAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_settlement_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int id = m_settlementService.insertSettlement(m_settlement);
			if (id > 0) {
				Log log = createLog(Modules.s_settlement_model, Operation.s_operation_add,
				      m_settlement);

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

	public String settlementBatchAdd() {
		return SUCCESS;
	}

	public String settlementBatchAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_settlement_model, Operation.s_operation_add));
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
					Settlement defarmation = convert(cols);

					if (defarmation != null) {
						m_settlementService.insertSettlement(defarmation);
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

	public String settlementBatchDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_settlement_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			StringBuilder sb = new StringBuilder();
			for (int id : m_deleteId) {
				if (id > 0) {
					m_settlementService.deleteSettlement(id);
					sb.append(id).append(",");
				}
			}
			Log log = createLog(Modules.s_settlement_model, Operation.s_operation_batch_delete, sb.toString());

			m_logService.insertLog(log);
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String settlementDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_settlement_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_settlement = m_settlementService.findByPK(m_settlementId);
			int count = m_settlementService.deleteSettlement(m_settlementId);
			if (count > 0) {
				Log log = createLog(Modules.s_settlement_model, Operation.s_operation_delete,
				      m_settlementId);

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

	public String settlementList() {
		Authority auth = checkAuthority(buildResource(Modules.s_settlement_model, Operation.s_operation_detail));
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
			m_totalSize = m_settlementService.querySizeByTunnelAndSection(m_tunnelId, m_tunnelSectionId,
			      m_liningRingConstructionId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_settlements = m_settlementService.queryLimitedSettlements(m_tunnelId,
			      m_tunnelSectionId, m_liningRingConstructionId, start, SIZE);
			for (Settlement settlement : m_settlements) {
				settlement.setTunnel(m_tunnelService.findByPK(settlement.getTunnelId()));
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String settlementUpdate() {
		try {
			m_liningRings = m_liningRingService.queryAllLiningRings();
			m_settlement = m_settlementService.findByPK(m_settlementId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(
			      m_settlement.getTunnelId(), 0, Integer.MAX_VALUE);
			m_liningRingConstructions = m_liningRingConstructionService.queryLimitedLiningRingConstructions(
			      m_settlement.getTunnelId(), m_settlement.getTunnelSectionId(), 0,
			      Integer.MAX_VALUE);
			if (m_settlement != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String settlementUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_settlement_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int count = m_settlementService.updateSettlement(m_settlement);
			if (count > 0) {
				Log log = createLog(Modules.s_settlement_model, Operation.s_operation_update,
				      m_settlement);

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

	public String queryAllSettlements() {
		m_settlements = m_settlementService.queryLimitedSettlements(m_tunnelId,
		      m_tunnelSectionId, 0, 0, Integer.MAX_VALUE);

		return SUCCESS;
	}

	public void setDeleteId(int[] deleteId) {
		m_deleteId = deleteId;
	}

	public void setLiningRingConstructionId(int liningRingConstructionId) {
		m_liningRingConstructionId = liningRingConstructionId;
	}

	public void setLiningRingConstructionService(LiningRingConstructionService liningRingConstructionService) {
		m_liningRingConstructionService = liningRingConstructionService;
	}

	public void setSettlement(Settlement settlement) {
		m_settlement = settlement;
	}

	public void setSettlementId(int settlementId) {
		m_settlementId = settlementId;
	}

	public void setSettlementService(SettlementService settlementService) {
		m_settlementService = settlementService;
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