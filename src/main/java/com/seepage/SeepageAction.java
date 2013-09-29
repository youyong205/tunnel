package com.seepage;

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

public class SeepageAction extends FileUploadAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(SeepageAction.class);

	private List<Seepage> m_seepages;

	private int m_seepageId;

	private SeepageService m_seepageService;

	private LiningRingService m_liningRingService;

	private Seepage m_seepage = new Seepage();

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

	private Seepage convert(Cell[] cells) {
		try {
			Seepage seepage = new Seepage();
			String name = convertToString(cells[0]);
			int blockIndex = convertToInteger(cells[1]);
			Date date = convertToDate(cells[2]);
			String shape = convertToString(cells[3]);
			double size = convertToDouble(cells[4]);
			double startAngle = convertToDouble(cells[5]);
			double endAngle = convertToDouble(cells[6]);
			int affect = convertToInteger(cells[7]);
			String des = "";

			if (cells.length >= 9) {
				des = convertToString(cells[8]);
			}

			LiningRingConstruction construction = m_liningRingConstructionService.findByName(name);

			if (construction != null) {
				seepage.setTunnelId(construction.getTunnelId());
				seepage.setTunnelSectionId(construction.getTunnelSectionId());
				seepage.setLiningRingConstructionId(construction.getId());
				seepage.setBlockIndex(blockIndex);
				seepage.setDate(date);
				seepage.setShape(shape);
				seepage.setSize(size);
				seepage.setStartAngle(startAngle);
				seepage.setEndAngle(endAngle);
				seepage.setAffect(affect);
				seepage.setDes(des);
				return seepage;
			}
		} catch (Exception e) {
			m_logger.error(e);
		}

		return null;
	}

	@Override
	public String getActionModule() {
		return Modules.s_seepage_model;
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

	public int getParentLiningRingConstructionId(){
		return m_liningRingConstructionId;
	}

	public int getParentTunnelSectionId() {
		return m_tunnelSectionId;
	}

	public Seepage getSeepage() {
		return m_seepage;
	}

	public List<Seepage> getSeepages() {
		return m_seepages;
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

	public String queryAllSeepages() {
		m_seepages = m_seepageService.queryLimitedSeepages(m_tunnelId, m_tunnelSectionId, 0, 0, Integer.MAX_VALUE);

		return SUCCESS;
	}

	public String seepageAdd() {
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

	public String seepageAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_seepage_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int id = m_seepageService.insertSeepage(m_seepage);
			if (id > 0) {
				Log log = createLog(Modules.s_seepage_model, Operation.s_operation_add, m_seepage);

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

	public String seepageBatchAdd() {
		return SUCCESS;
	}

	public String seepageBatchAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_seepage_model, Operation.s_operation_add));
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
					Seepage defarmation = convert(cols);

					if (defarmation != null) {
						m_seepageService.insertSeepage(defarmation);
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

	public String seepageBatchDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_seepage_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			StringBuilder sb = new StringBuilder();
			for (int id : m_deleteId) {
				if (id > 0) {
					m_seepageService.deleteSeepage(id);
					sb.append(id).append(",");
				}
			}
			Log log = createLog(Modules.s_seepage_model, Operation.s_operation_batch_delete, sb.toString());

			m_logService.insertLog(log);
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String seepageDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_seepage_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_seepage = m_seepageService.findByPK(m_seepageId);
			int count = m_seepageService.deleteSeepage(m_seepageId);
			if (count > 0) {
				Log log = createLog(Modules.s_seepage_model, Operation.s_operation_delete, m_seepageId);

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

	public String seepageList() {
		Authority auth = checkAuthority(buildResource(Modules.s_seepage_model, Operation.s_operation_detail));
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
			m_totalSize = m_seepageService.querySizeByTunnelAndSection(m_tunnelId, m_tunnelSectionId,
			      m_liningRingConstructionId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_seepages = m_seepageService.queryLimitedSeepages(m_tunnelId, m_tunnelSectionId,
			      m_liningRingConstructionId, start, SIZE);
			for (Seepage seepage : m_seepages) {
				seepage.setTunnel(m_tunnelService.findByPK(seepage.getTunnelId()));
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String seepageUpdate() {
		try {
			m_liningRings = m_liningRingService.queryAllLiningRings();
			m_seepage = m_seepageService.findByPK(m_seepageId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_seepage.getTunnelId(), 0,
			      Integer.MAX_VALUE);
			m_liningRingConstructions = m_liningRingConstructionService.queryLimitedLiningRingConstructions(
			      m_seepage.getTunnelId(), m_seepage.getTunnelSectionId(), 0, Integer.MAX_VALUE);
			int liningRingConstructionId = m_seepage.getLiningRingConstructionId();
			int liningRingId = m_liningRingConstructionService.findByPK(liningRingConstructionId).getLiningRingId();
			m_liningRingBlocks = m_liningRingBlockService.queryByLiningRingId(liningRingId);
			if (m_seepage != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String seepageUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_seepage_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int count = m_seepageService.updateSeepage(m_seepage);
			if (count > 0) {
				Log log = createLog(Modules.s_seepage_model, Operation.s_operation_update, m_seepage);

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

	public void setSeepage(Seepage seepage) {
		m_seepage = seepage;
	}

	public void setSeepageId(int seepageId) {
		m_seepageId = seepageId;
	}

	public void setSeepageService(SeepageService seepageService) {
		m_seepageService = seepageService;
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