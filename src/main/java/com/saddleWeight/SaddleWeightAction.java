package com.saddleWeight;

import java.util.List;

import org.apache.log4j.Logger;

import com.Authority;
import com.Modules;
import com.Operation;
import com.ScheduledAction;
import com.document.Document;
import com.log.Log;
import com.schedule.Schedule;
import com.tunnel.Tunnel;
import com.tunnel.TunnelService;
import com.tunnelSection.TunnelSection;
import com.tunnelSection.TunnelSectionService;

public class SaddleWeightAction extends ScheduledAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(SaddleWeightAction.class);

	private List<SaddleWeight> m_saddleWeights;

	private int m_saddleWeightId;

	private SaddleWeightService m_saddleWeightService;

	private SaddleWeight m_saddleWeight = new SaddleWeight();

	private int m_tunnelId;

	private int m_tunnelSectionId;

	private TunnelService m_tunnelService;

	private TunnelSectionService m_tunnelSectionService;

	private List<Tunnel> m_tunnels;

	private List<TunnelSection> m_tunnelSections;

	@Override
	public String getActionModule() {
		return Modules.s_saddleWeight_model;
	}

	public int getParentTunnelSectionId() {
		return m_tunnelSectionId;
	}

	public SaddleWeight getSaddleWeight() {
		return m_saddleWeight;
	}

	public List<SaddleWeight> getSaddleWeights() {
		return m_saddleWeights;
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

	public String queryAllSaddleWeights() {
		m_saddleWeights = m_saddleWeightService.queryLimitedSaddleWeights(m_tunnelId, m_tunnelSectionId, 0,
		      Integer.MAX_VALUE);

		return SUCCESS;
	}

	public String saddleWeightAdd() {
		m_tunnels = m_tunnelService.queryAllTunnels();

		if (m_tunnelId == 0) {
			m_tunnelId = m_tunnelService.queryDefaultTunnelId();
		}
		m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);
		m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
		return SUCCESS;
	}

	public String saddleWeightAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_saddleWeight_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_documentService.insertDocument(Modules.s_saddleWeight_model, m_uploadFile);
				m_saddleWeight.setDocumentId(documentId);
			}
			m_schedule.setType(getActionModule());
			int scheduleId = m_scheduleService.insertSchedule(m_schedule);
			m_saddleWeight.setScheduleId(scheduleId);

			int id = m_saddleWeightService.insertSaddleWeight(m_saddleWeight);
			if (id > 0) {
				Log log = createLog(Modules.s_saddleWeight_model, Operation.s_operation_add, m_saddleWeight);

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

	public String saddleWeightDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_saddleWeight_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_saddleWeight = m_saddleWeightService.findByPK(m_saddleWeightId);
			m_documentService.deleteDocument(m_saddleWeight.getDocumentId());
			m_scheduleService.deleteSchedule(m_saddleWeight.getScheduleId());
			int count = m_saddleWeightService.deleteSaddleWeight(m_saddleWeightId);
			if (count > 0) {
				Log log = createLog(Modules.s_saddleWeight_model, Operation.s_operation_delete, m_saddleWeightId);

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

	public String saddleWeightList() {
		Authority auth = checkAuthority(buildResource(Modules.s_saddleWeight_model, Operation.s_operation_detail));
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

			m_totalSize = m_saddleWeightService.querySizeByTunnelAndSection(m_tunnelId, m_tunnelSectionId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_saddleWeights = m_saddleWeightService.queryLimitedSaddleWeights(m_tunnelId, m_tunnelSectionId, start, SIZE);
			for (SaddleWeight saddleWeight : m_saddleWeights) {
				saddleWeight.setTunnel(m_tunnelService.findByPK(saddleWeight.getTunnelId()));
				int scheduleId = saddleWeight.getScheduleId();
				if (scheduleId > 0) {
					Schedule schedule = m_scheduleService.findByPK(scheduleId);
					if (schedule != null) {
						saddleWeight.setSchedule(schedule);
						schedule.setConstructionUnit(m_constructionUnitService.findByPK(schedule.getConstructionUnitId()));
					}
				}
				if (saddleWeight.getDocumentId() > 0) {
					saddleWeight.setDocument(m_documentService.findByPK(saddleWeight.getDocumentId()));
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String saddleWeightUpdate() {
		try {
			m_saddleWeight = m_saddleWeightService.findByPK(m_saddleWeightId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_saddleWeight.getTunnelId(),
			      0, Integer.MAX_VALUE);
			m_schedule = m_scheduleService.findByPK(m_saddleWeight.getScheduleId());
			m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
			int documentId = m_saddleWeight.getDocumentId();

			if (documentId > 0) {
				m_saddleWeight.setDocument(m_documentService.findByPK(m_saddleWeight.getDocumentId()));
			}
			if (m_saddleWeight != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String saddleWeightUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_saddleWeight_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_saddleWeight.getDocumentId();
				if (documentId > 0) {
					Document document = m_documentService.findByPK(documentId);
					m_documentService.updateDocument(Modules.s_saddleWeight_model, m_uploadFile, document);
				} else {
					documentId = m_documentService.insertDocument(Modules.s_saddleWeight_model, m_uploadFile);
					m_saddleWeight.setDocumentId(documentId);
				}
			}
			m_scheduleService.updateSchedule(m_schedule);
			int count = m_saddleWeightService.updateSaddleWeight(m_saddleWeight);
			if (count > 0) {
				Log log = createLog(Modules.s_saddleWeight_model, Operation.s_operation_update, m_saddleWeight);

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

	public void setSaddleWeight(SaddleWeight saddleWeight) {
		m_saddleWeight = saddleWeight;
	}

	public void setSaddleWeightId(int saddleWeightId) {
		m_saddleWeightId = saddleWeightId;
	}

	public void setSaddleWeightService(SaddleWeightService saddleWeightService) {
		m_saddleWeightService = saddleWeightService;
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