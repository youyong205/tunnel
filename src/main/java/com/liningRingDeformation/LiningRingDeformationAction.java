package com.liningRingDeformation;

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
import com.liningRing.LiningRingService;
import com.liningRingConstruction.LiningRingConstruction;
import com.liningRingConstruction.LiningRingConstructionService;
import com.log.Log;
import com.tunnel.Tunnel;
import com.tunnel.TunnelService;
import com.tunnelSection.TunnelSection;
import com.tunnelSection.TunnelSectionService;

public class LiningRingDeformationAction extends FileUploadAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(LiningRingDeformationAction.class);

	private List<LiningRingDeformation> m_liningRingDeformations;

	private int m_liningRingDeformationId;

	private LiningRingDeformationService m_liningRingDeformationService;

	private LiningRingService m_liningRingService;

	private LiningRingDeformation m_liningRingDeformation = new LiningRingDeformation();

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

	private int[] m_deleteId = new int[SIZE];

	private BatchInsertResult m_batchInsertResult = new BatchInsertResult();

	private LiningRingDeformation convert(Cell[] cells) {
		try {
			LiningRingDeformation defarmation = new LiningRingDeformation();
			String name = convertToString(cells[0]);
			Date date = convertToDate(cells[1]);
			String measuringPoing = convertToString(cells[2]);
			double value = convertToDouble(cells[3]);
			String des = "";

			if (cells.length >= 5) {
				des = convertToString(cells[4]);
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
		return Modules.s_liningRingDeformation_model;
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

	public LiningRingDeformation getLiningRingDeformation() {
		return m_liningRingDeformation;
	}

	public List<LiningRingDeformation> getLiningRingDeformations() {
		return m_liningRingDeformations;
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

	public String liningRingDeformationAdd() {
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

	public String liningRingDeformationAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_liningRingDeformation_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int id = m_liningRingDeformationService.insertLiningRingDeformation(m_liningRingDeformation);
			if (id > 0) {
				Log log = createLog(Modules.s_liningRingDeformation_model, Operation.s_operation_add,
				      m_liningRingDeformation);

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

	public String liningRingDeformationBatchAdd() {
		return SUCCESS;
	}

	public String liningRingDeformationBatchAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_liningRingDeformation_model, Operation.s_operation_add));
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
					LiningRingDeformation defarmation = convert(cols);

					if (defarmation != null) {
						m_liningRingDeformationService.insertLiningRingDeformation(defarmation);
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

	public String liningRingDeformationBatchDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_liningRingDeformation_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			StringBuilder sb = new StringBuilder();
			for (int id : m_deleteId) {
				if (id > 0) {
					m_liningRingDeformationService.deleteLiningRingDeformation(id);
					sb.append(id).append(",");
				}
			}
			Log log = createLog(Modules.s_liningRingDeformation_model, Operation.s_operation_batch_delete, sb.toString());

			m_logService.insertLog(log);
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String liningRingDeformationDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_liningRingDeformation_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_liningRingDeformation = m_liningRingDeformationService.findByPK(m_liningRingDeformationId);
			int count = m_liningRingDeformationService.deleteLiningRingDeformation(m_liningRingDeformationId);
			if (count > 0) {
				Log log = createLog(Modules.s_liningRingDeformation_model, Operation.s_operation_delete,
				      m_liningRingDeformationId);

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

	public String liningRingDeformationList() {
		Authority auth = checkAuthority(buildResource(Modules.s_liningRingDeformation_model, Operation.s_operation_detail));
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
			m_totalSize = m_liningRingDeformationService.querySizeByTunnelAndSection(m_tunnelId, m_tunnelSectionId,
			      m_liningRingConstructionId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_liningRingDeformations = m_liningRingDeformationService.queryLimitedLiningRingDeformations(m_tunnelId,
			      m_tunnelSectionId, m_liningRingConstructionId, start, SIZE);
			for (LiningRingDeformation liningRingDeformation : m_liningRingDeformations) {
				liningRingDeformation.setTunnel(m_tunnelService.findByPK(liningRingDeformation.getTunnelId()));
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String liningRingDeformationUpdate() {
		try {
			m_liningRings = m_liningRingService.queryAllLiningRings();
			m_liningRingDeformation = m_liningRingDeformationService.findByPK(m_liningRingDeformationId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(
			      m_liningRingDeformation.getTunnelId(), 0, Integer.MAX_VALUE);
			m_liningRingConstructions = m_liningRingConstructionService.queryLimitedLiningRingConstructions(
			      m_liningRingDeformation.getTunnelId(), m_liningRingDeformation.getTunnelSectionId(), 0,
			      Integer.MAX_VALUE);
			if (m_liningRingDeformation != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String liningRingDeformationUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_liningRingDeformation_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int count = m_liningRingDeformationService.updateLiningRingDeformation(m_liningRingDeformation);
			if (count > 0) {
				Log log = createLog(Modules.s_liningRingDeformation_model, Operation.s_operation_update,
				      m_liningRingDeformation);

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

	public String queryAllLiningRingDeformations() {
		m_liningRingDeformations = m_liningRingDeformationService.queryLimitedLiningRingDeformations(m_tunnelId,
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

	public void setLiningRingDeformation(LiningRingDeformation liningRingDeformation) {
		m_liningRingDeformation = liningRingDeformation;
	}

	public void setLiningRingDeformationId(int liningRingDeformationId) {
		m_liningRingDeformationId = liningRingDeformationId;
	}

	public void setLiningRingDeformationService(LiningRingDeformationService liningRingDeformationService) {
		m_liningRingDeformationService = liningRingDeformationService;
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

	public int getParentLiningRingConstructionId() {
		return m_liningRingConstructionId;
	}

}