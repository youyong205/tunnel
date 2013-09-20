package com.cracks;

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

public class CracksAction extends FileUploadAction {

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

	private SimpleDateFormat m_sdf = new SimpleDateFormat("yyyy-MM-dd");

	private int[] m_deleteId = new int[SIZE];

	private BatchInsertResult m_batchInsertResult = new BatchInsertResult();

	private Cracks convert(Cell[] cells) {
		try {
			Cracks cracks = new Cracks();
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
				cracks.setTunnelId(construction.getTunnelId());
				cracks.setTunnelSectionId(construction.getTunnelSectionId());
				cracks.setLiningRingConstructionId(construction.getId());
				cracks.setBlockIndex(blockIndex);
				cracks.setDate(date);
				return cracks;
			}
		} catch (Exception e) {
			m_logger.error(e);
		}

		return null;
	}

	@Override
	public String getActionModule() {
		return Modules.s_cracks_model;
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

	public Cracks getCracks() {
		return m_cracks;
	}

	public List<Cracks> getCrackss() {
		return m_crackss;
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
			m_crackss = m_cracksService.queryLimitedCrackss(m_tunnelId, m_tunnelSectionId,
			      m_liningRingConstructionId, start, SIZE);
			for (Cracks cracks : m_crackss) {
				cracks.setTunnel(m_tunnelService.findByPK(cracks.getTunnelId()));
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
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

	public String queryAllCrackss() {
		m_crackss = m_cracksService.queryLimitedCrackss(m_tunnelId, m_tunnelSectionId, 0, 0, Integer.MAX_VALUE);

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

	public void setCracks(Cracks cracks) {
		m_cracks = cracks;
	}

	public void setCracksId(int cracksId) {
		m_cracksId = cracksId;
	}

	public void setCracksService(CracksService cracksService) {
		m_cracksService = cracksService;
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
	
	public int getParentLiningRingConstructionId(){
		return m_liningRingConstructionId;
	}

}