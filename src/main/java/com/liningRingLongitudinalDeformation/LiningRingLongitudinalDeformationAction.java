package com.liningRingLongitudinalDeformation;

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

public class LiningRingLongitudinalDeformationAction extends FileUploadAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(LiningRingLongitudinalDeformationAction.class);

	private List<LiningRingLongitudinalDeformation> m_liningRingLongitudinalDeformations;

	private int m_liningRingLongitudinalDeformationId;

	private LiningRingLongitudinalDeformationService m_liningRingLongitudinalDeformationService;

	private LiningRingService m_liningRingService;

	private LiningRingLongitudinalDeformation m_liningRingLongitudinalDeformation = new LiningRingLongitudinalDeformation();

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

	private LiningRingLongitudinalDeformation convert(Cell[] cells) {
		try {
			LiningRingLongitudinalDeformation defarmation = new LiningRingLongitudinalDeformation();
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
		return Modules.s_liningRingLongitudinalDeformation_model;
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

	public LiningRingLongitudinalDeformation getLiningRingLongitudinalDeformation() {
		return m_liningRingLongitudinalDeformation;
	}

	public List<LiningRingLongitudinalDeformation> getLiningRingLongitudinalDeformations() {
		return m_liningRingLongitudinalDeformations;
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

	public String liningRingLongitudinalDeformationAdd() {
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

	public String liningRingLongitudinalDeformationAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_liningRingLongitudinalDeformation_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int id = m_liningRingLongitudinalDeformationService.insertLiningRingLongitudinalDeformation(m_liningRingLongitudinalDeformation);
			if (id > 0) {
				Log log = createLog(Modules.s_liningRingLongitudinalDeformation_model, Operation.s_operation_add,
				      m_liningRingLongitudinalDeformation);

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

	public String liningRingLongitudinalDeformationBatchAdd() {
		return SUCCESS;
	}

	public String liningRingLongitudinalDeformationBatchAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_liningRingLongitudinalDeformation_model, Operation.s_operation_add));
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
					LiningRingLongitudinalDeformation defarmation = convert(cols);

					if (defarmation != null) {
						m_liningRingLongitudinalDeformationService.insertLiningRingLongitudinalDeformation(defarmation);
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

	public String liningRingLongitudinalDeformationBatchDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_liningRingLongitudinalDeformation_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			StringBuilder sb = new StringBuilder();
			for (int id : m_deleteId) {
				if (id > 0) {
					m_liningRingLongitudinalDeformationService.deleteLiningRingLongitudinalDeformation(id);
					sb.append(id).append(",");
				}
			}
			Log log = createLog(Modules.s_liningRingLongitudinalDeformation_model, Operation.s_operation_batch_delete, sb.toString());

			m_logService.insertLog(log);
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String liningRingLongitudinalDeformationDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_liningRingLongitudinalDeformation_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_liningRingLongitudinalDeformation = m_liningRingLongitudinalDeformationService.findByPK(m_liningRingLongitudinalDeformationId);
			int count = m_liningRingLongitudinalDeformationService.deleteLiningRingLongitudinalDeformation(m_liningRingLongitudinalDeformationId);
			if (count > 0) {
				Log log = createLog(Modules.s_liningRingLongitudinalDeformation_model, Operation.s_operation_delete,
				      m_liningRingLongitudinalDeformationId);

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

	public String liningRingLongitudinalDeformationList() {
		Authority auth = checkAuthority(buildResource(Modules.s_liningRingLongitudinalDeformation_model, Operation.s_operation_detail));
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
			m_totalSize = m_liningRingLongitudinalDeformationService.querySizeByTunnelAndSection(m_tunnelId, m_tunnelSectionId,
			      m_liningRingConstructionId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_liningRingLongitudinalDeformations = m_liningRingLongitudinalDeformationService.queryLimitedLiningRingLongitudinalDeformations(m_tunnelId,
			      m_tunnelSectionId, m_liningRingConstructionId, start, SIZE);
			for (LiningRingLongitudinalDeformation liningRingLongitudinalDeformation : m_liningRingLongitudinalDeformations) {
				liningRingLongitudinalDeformation.setTunnel(m_tunnelService.findByPK(liningRingLongitudinalDeformation.getTunnelId()));
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String liningRingLongitudinalDeformationUpdate() {
		try {
			m_liningRings = m_liningRingService.queryAllLiningRings();
			m_liningRingLongitudinalDeformation = m_liningRingLongitudinalDeformationService.findByPK(m_liningRingLongitudinalDeformationId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(
			      m_liningRingLongitudinalDeformation.getTunnelId(), 0, Integer.MAX_VALUE);
			m_liningRingConstructions = m_liningRingConstructionService.queryLimitedLiningRingConstructions(
			      m_liningRingLongitudinalDeformation.getTunnelId(), m_liningRingLongitudinalDeformation.getTunnelSectionId(), 0,
			      Integer.MAX_VALUE);
			if (m_liningRingLongitudinalDeformation != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String liningRingLongitudinalDeformationUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_liningRingLongitudinalDeformation_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int count = m_liningRingLongitudinalDeformationService.updateLiningRingLongitudinalDeformation(m_liningRingLongitudinalDeformation);
			if (count > 0) {
				Log log = createLog(Modules.s_liningRingLongitudinalDeformation_model, Operation.s_operation_update,
				      m_liningRingLongitudinalDeformation);

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

	public String queryAllLiningRingLongitudinalDeformations() {
		m_liningRingLongitudinalDeformations = m_liningRingLongitudinalDeformationService.queryLimitedLiningRingLongitudinalDeformations(m_tunnelId,
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

	public void setLiningRingLongitudinalDeformation(LiningRingLongitudinalDeformation liningRingLongitudinalDeformation) {
		m_liningRingLongitudinalDeformation = liningRingLongitudinalDeformation;
	}

	public void setLiningRingLongitudinalDeformationId(int liningRingLongitudinalDeformationId) {
		m_liningRingLongitudinalDeformationId = liningRingLongitudinalDeformationId;
	}

	public void setLiningRingLongitudinalDeformationService(LiningRingLongitudinalDeformationService liningRingLongitudinalDeformationService) {
		m_liningRingLongitudinalDeformationService = liningRingLongitudinalDeformationService;
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
	
	public int getParentLiningRingConstructionId(){
		return m_liningRingConstructionId;
	}

}