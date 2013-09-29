package com.girthOpen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;

import com.Authority;
import com.BatchInsertResult;
import com.FileUploadAction;
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

public class GirthOpenAction extends FileUploadAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(GirthOpenAction.class);

	private List<GirthOpen> m_girthOpens;

	private int m_girthOpenId;

	private GirthOpenService m_girthOpenService;

	private LiningRingService m_liningRingService;

	private GirthOpen m_girthOpen = new GirthOpen();

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

	private GirthOpen convert(Cell[] cells) {
		try {
			GirthOpen girthOpen = new GirthOpen();

			String name = convertToString(cells[0]);
			int blockIndex = convertToInteger(cells[1]);
			Date date = convertToDate(cells[2]);
			String measuringPoing = convertToString(cells[3]);
			double value = convertToDouble(cells[4]);
			int type = convertToInteger(cells[5]);
			int serious = convertToInteger(cells[6]);
			String des = "";

			if (cells.length >= 8) {
				des = convertToString(cells[7]);
			}

			LiningRingConstruction construction = m_liningRingConstructionService.findByName(name);

			if (construction != null) {
				girthOpen.setTunnelId(construction.getTunnelId());
				girthOpen.setTunnelSectionId(construction.getTunnelSectionId());
				girthOpen.setLiningRingConstructionId(construction.getId());
				girthOpen.setBlockIndex(blockIndex);
				girthOpen.setDate(date);
				girthOpen.setType(type);
				girthOpen.setSerious(serious);
				girthOpen.setMeasuringPoing(measuringPoing);
				girthOpen.setValue(value);
				girthOpen.setDes(des);
				return girthOpen;
			}
		} catch (Exception e) {
			m_logger.error(e);
		}

		return null;
	}

	@Override
	public String getActionModule() {
		return Modules.s_girthOpen_model;
	}

	public BatchInsertResult getBatchInsertResult() {
		return m_batchInsertResult;
	}

	public GirthOpen getGirthOpen() {
		return m_girthOpen;
	}

	public List<GirthOpen> getGirthOpens() {
		return m_girthOpens;
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

	public String girthOpenAdd() {
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

	public String girthOpenAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_girthOpen_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int id = m_girthOpenService.insertGirthOpen(m_girthOpen);
			if (id > 0) {
				Log log = createLog(Modules.s_girthOpen_model, Operation.s_operation_add, m_girthOpen);

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

	public String girthOpenBatchAdd() {
		return SUCCESS;
	}

	public String girthOpenBatchAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_girthOpen_model, Operation.s_operation_add));
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
					GirthOpen defarmation = convert(cols);

					if (defarmation != null) {
						m_girthOpenService.insertGirthOpen(defarmation);
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

	public String girthOpenBatchDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_girthOpen_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			StringBuilder sb = new StringBuilder();
			for (int id : m_deleteId) {
				if (id > 0) {
					m_girthOpenService.deleteGirthOpen(id);
					sb.append(id).append(",");
				}
			}
			Log log = createLog(Modules.s_girthOpen_model, Operation.s_operation_batch_delete, sb.toString());

			m_logService.insertLog(log);
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String girthOpenDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_girthOpen_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_girthOpen = m_girthOpenService.findByPK(m_girthOpenId);
			int count = m_girthOpenService.deleteGirthOpen(m_girthOpenId);
			if (count > 0) {
				Log log = createLog(Modules.s_girthOpen_model, Operation.s_operation_delete, m_girthOpenId);

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

	public String girthOpenList() {
		Authority auth = checkAuthority(buildResource(Modules.s_girthOpen_model, Operation.s_operation_detail));
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
			m_totalSize = m_girthOpenService.querySizeByTunnelAndSection(m_tunnelId, m_tunnelSectionId,
			      m_liningRingConstructionId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_girthOpens = m_girthOpenService.queryLimitedGirthOpens(m_tunnelId, m_tunnelSectionId,
			      m_liningRingConstructionId, start, SIZE);
			for (GirthOpen girthOpen : m_girthOpens) {
				girthOpen.setTunnel(m_tunnelService.findByPK(girthOpen.getTunnelId()));
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String girthOpenUpdate() {
		try {
			m_liningRings = m_liningRingService.queryAllLiningRings();
			m_girthOpen = m_girthOpenService.findByPK(m_girthOpenId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_girthOpen.getTunnelId(), 0,
			      Integer.MAX_VALUE);
			m_liningRingConstructions = m_liningRingConstructionService.queryLimitedLiningRingConstructions(
			      m_girthOpen.getTunnelId(), m_girthOpen.getTunnelSectionId(), 0, Integer.MAX_VALUE);
			int liningRingConstructionId = m_girthOpen.getLiningRingConstructionId();
			int liningRingId = m_liningRingConstructionService.findByPK(liningRingConstructionId).getLiningRingId();
			m_liningRingBlocks = m_liningRingBlockService.queryByLiningRingId(liningRingId);
			if (m_girthOpen != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String girthOpenUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_girthOpen_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int count = m_girthOpenService.updateGirthOpen(m_girthOpen);
			if (count > 0) {
				Log log = createLog(Modules.s_girthOpen_model, Operation.s_operation_update, m_girthOpen);

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

	public String queryAllGirthOpens() {
		m_girthOpens = m_girthOpenService.queryLimitedGirthOpens(m_tunnelId, m_tunnelSectionId, 0, 0, Integer.MAX_VALUE);

		return SUCCESS;
	}

	public void setDeleteId(int[] deleteId) {
		m_deleteId = deleteId;
	}

	public void setGirthOpen(GirthOpen girthOpen) {
		m_girthOpen = girthOpen;
	}

	public void setGirthOpenId(int girthOpenId) {
		m_girthOpenId = girthOpenId;
	}

	public void setGirthOpenService(GirthOpenService girthOpenService) {
		m_girthOpenService = girthOpenService;
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