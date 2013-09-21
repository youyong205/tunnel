package com.coverLoss;

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

public class CoverLossAction extends FileUploadAction {

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
			String des = "";

			if (cells.length >= 10) {
				des = convertToString(cells[9]);
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
				return coverLoss;
			}
		} catch (Exception e) {
			m_logger.error(e);
		}

		return null;
	}

	@Override
	public String getActionModule() {
		return Modules.s_coverLoss_model;
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

	public CoverLoss getCoverLoss() {
		return m_coverLoss;
	}

	public List<CoverLoss> getCoverLosss() {
		return m_coverLosss;
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

	public String queryAllCoverLosss() {
		m_coverLosss = m_coverLossService.queryLimitedCoverLosss(m_tunnelId, m_tunnelSectionId, 0, 0, Integer.MAX_VALUE);

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

	public void setCoverLoss(CoverLoss coverLoss) {
		m_coverLoss = coverLoss;
	}

	public void setCoverLossId(int coverLossId) {
		m_coverLossId = coverLossId;
	}

	public void setCoverLossService(CoverLossService coverLossService) {
		m_coverLossService = coverLossService;
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

	public int getParentLiningRingConstructionId() {
		return m_liningRingConstructionId;
	}

}