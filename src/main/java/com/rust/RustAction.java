package com.rust;

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

public class RustAction extends FileUploadAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(RustAction.class);

	private List<Rust> m_rusts;

	private int m_rustId;

	private RustService m_rustService;

	private LiningRingService m_liningRingService;

	private Rust m_rust = new Rust();

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

	private SimpleDateFormat m_sdf = new SimpleDateFormat("yyyy-MM-dd");

	private int[] m_deleteId = new int[SIZE];

	private BatchInsertResult m_batchInsertResult = new BatchInsertResult();

	private Rust convert(Cell[] cells) {
		try {
			Rust rust = new Rust();
			String name = cells[0].getContents();
			int blockIndex = Integer.parseInt(cells[1].getContents());
			Date date = null;
			if (cells[2].getType() == CellType.DATE) {
				DateCell dateCell = (DateCell) cells[2];
				date = dateCell.getDate();
			} else {
				date = m_sdf.parse(cells[2].getContents());
			}
			String measuringPoing = cells[3].getContents();
			double value = Double.parseDouble(cells[4].getContents());
			int type = Integer.parseInt(cells[5].getContents());
			int serious = Integer.parseInt(cells[6].getContents());
			String des = "";

			if (cells.length > 6) {
				des = cells[4].getContents();
			}

			LiningRingConstruction construction = m_liningRingConstructionService.findByName(name);

			if (construction != null) {
				rust.setTunnelId(construction.getTunnelId());
				rust.setTunnelSectionId(construction.getTunnelSectionId());
				rust.setLiningRingConstructionId(construction.getId());
				rust.setBlockIndex(blockIndex);
				rust.setDate(date);
				rust.setDes(des);
				return rust;
			}
		} catch (Exception e) {
			m_logger.error(e);
		}

		return null;
	}

	@Override
	public String getActionModule() {
		return Modules.s_rust_model;
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

	public Rust getRust() {
		return m_rust;
	}

	public List<Rust> getRusts() {
		return m_rusts;
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

	public String rustAdd() {
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

	public String rustAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_rust_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int id = m_rustService.insertRust(m_rust);
			if (id > 0) {
				Log log = createLog(Modules.s_rust_model, Operation.s_operation_add, m_rust);

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

	public String rustBatchAdd() {
		return SUCCESS;
	}

	public String rustBatchAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_rust_model, Operation.s_operation_add));
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
					Rust defarmation = convert(cols);

					if (defarmation != null) {
						m_rustService.insertRust(defarmation);
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

	public String rustBatchDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_rust_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			StringBuilder sb = new StringBuilder();
			for (int id : m_deleteId) {
				if (id > 0) {
					m_rustService.deleteRust(id);
					sb.append(id).append(",");
				}
			}
			Log log = createLog(Modules.s_rust_model, Operation.s_operation_batch_delete, sb.toString());

			m_logService.insertLog(log);
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String rustDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_rust_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_rust = m_rustService.findByPK(m_rustId);
			int count = m_rustService.deleteRust(m_rustId);
			if (count > 0) {
				Log log = createLog(Modules.s_rust_model, Operation.s_operation_delete, m_rustId);

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

	public String rustList() {
		Authority auth = checkAuthority(buildResource(Modules.s_rust_model, Operation.s_operation_detail));
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
			m_totalSize = m_rustService.querySizeByTunnelAndSection(m_tunnelId, m_tunnelSectionId,
			      m_liningRingConstructionId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_rusts = m_rustService.queryLimitedRusts(m_tunnelId, m_tunnelSectionId,
			      m_liningRingConstructionId, start, SIZE);
			for (Rust rust : m_rusts) {
				rust.setTunnel(m_tunnelService.findByPK(rust.getTunnelId()));
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String rustUpdate() {
		try {
			m_liningRings = m_liningRingService.queryAllLiningRings();
			m_rust = m_rustService.findByPK(m_rustId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_rust.getTunnelId(), 0,
			      Integer.MAX_VALUE);
			m_liningRingConstructions = m_liningRingConstructionService.queryLimitedLiningRingConstructions(
			      m_rust.getTunnelId(), m_rust.getTunnelSectionId(), 0, Integer.MAX_VALUE);
			int liningRingConstructionId = m_rust.getLiningRingConstructionId();
			int liningRingId = m_liningRingConstructionService.findByPK(liningRingConstructionId).getLiningRingId();
			m_liningRingBlocks = m_liningRingBlockService.queryByLiningRingId(liningRingId);
			if (m_rust != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String rustUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_rust_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int count = m_rustService.updateRust(m_rust);
			if (count > 0) {
				Log log = createLog(Modules.s_rust_model, Operation.s_operation_update, m_rust);

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

	public String queryAllRusts() {
		m_rusts = m_rustService.queryLimitedRusts(m_tunnelId, m_tunnelSectionId, 0, 0, Integer.MAX_VALUE);

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

	public void setRust(Rust rust) {
		m_rust = rust;
	}

	public void setRustId(int rustId) {
		m_rustId = rustId;
	}

	public void setRustService(RustService rustService) {
		m_rustService = rustService;
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

	public void setLiningRingBlockService(LiningRingBlockService liningRingBlockService) {
		m_liningRingBlockService = liningRingBlockService;
	}

	public List<LiningRingBlock> getLiningRingBlocks() {
		return m_liningRingBlocks;
	}

}