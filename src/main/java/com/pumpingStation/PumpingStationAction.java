package com.pumpingStation;

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

public class PumpingStationAction extends ScheduledAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(PumpingStationAction.class);

	private List<PumpingStation> m_pumpingStations;

	private int m_pumpingStationId;

	private PumpingStationService m_pumpingStationService;

	private PumpingStation m_pumpingStation = new PumpingStation();

	private int m_tunnelId;

	private int m_tunnelSectionId;

	private TunnelService m_tunnelService;

	private TunnelSectionService m_tunnelSectionService;

	private List<Tunnel> m_tunnels;

	private List<TunnelSection> m_tunnelSections;

	@Override
	public String getActionModule() {
		return Modules.s_pumpingStation_model;
	}

	public int getParentTunnelSectionId() {
		return m_tunnelSectionId;
	}

	public PumpingStation getPumpingStation() {
		return m_pumpingStation;
	}

	public List<PumpingStation> getPumpingStations() {
		return m_pumpingStations;
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

	public String pumpingStationAdd() {
		m_tunnels = m_tunnelService.queryAllTunnels();

		if (m_tunnelId == 0) {
			m_tunnelId = m_tunnelService.queryDefaultTunnelId();
		}
		m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);
		m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
		return SUCCESS;
	}

	public String pumpingStationAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_pumpingStation_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_documentService.insertDocument(Modules.s_pumpingStation_model, m_uploadFile);
				m_pumpingStation.setDocumentId(documentId);
			}
			m_schedule.setType(getActionModule());
			int scheduleId = m_scheduleService.insertSchedule(m_schedule);
			m_pumpingStation.setScheduleId(scheduleId);

			int id = m_pumpingStationService.insertPumpingStation(m_pumpingStation);
			if (id > 0) {
				Log log = createLog(Modules.s_pumpingStation_model, Operation.s_operation_add, m_pumpingStation);

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

	public String pumpingStationDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_pumpingStation_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_pumpingStation = m_pumpingStationService.findByPK(m_pumpingStationId);
			m_documentService.deleteDocument(m_pumpingStation.getDocumentId());
			m_scheduleService.deleteSchedule(m_pumpingStation.getScheduleId());
			int count = m_pumpingStationService.deletePumpingStation(m_pumpingStationId);
			if (count > 0) {
				Log log = createLog(Modules.s_pumpingStation_model, Operation.s_operation_delete, m_pumpingStationId);

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

	public String pumpingStationList() {
		Authority auth = checkAuthority(buildResource(Modules.s_pumpingStation_model, Operation.s_operation_detail));
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

			m_totalSize = m_pumpingStationService.querySizeByTunnelAndSection(m_tunnelId, m_tunnelSectionId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_pumpingStations = m_pumpingStationService.queryLimitedPumpingStations(m_tunnelId, m_tunnelSectionId, start, SIZE);
			for (PumpingStation pumpingStation : m_pumpingStations) {
				pumpingStation.setTunnel(m_tunnelService.findByPK(pumpingStation.getTunnelId()));
				int scheduleId = pumpingStation.getScheduleId();
				if (scheduleId > 0) {
					Schedule schedule = m_scheduleService.findByPK(scheduleId);
					if (schedule != null) {
						pumpingStation.setSchedule(schedule);
						schedule.setConstructionUnit(m_constructionUnitService.findByPK(schedule.getConstructionUnitId()));
					}
				}
				if (pumpingStation.getDocumentId() > 0) {
					pumpingStation.setDocument(m_documentService.findByPK(pumpingStation.getDocumentId()));
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String pumpingStationUpdate() {
		try {
			m_pumpingStation = m_pumpingStationService.findByPK(m_pumpingStationId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_pumpingStation.getTunnelId(), 0,
			      Integer.MAX_VALUE);
			m_schedule = m_scheduleService.findByPK(m_pumpingStation.getScheduleId());
			m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
			int documentId = m_pumpingStation.getDocumentId();

			if (documentId > 0) {
				m_pumpingStation.setDocument(m_documentService.findByPK(m_pumpingStation.getDocumentId()));
			}
			if (m_pumpingStation != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String pumpingStationUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_pumpingStation_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_pumpingStation.getDocumentId();
				if (documentId > 0) {
					Document document = m_documentService.findByPK(documentId);
					m_documentService.updateDocument(Modules.s_pumpingStation_model, m_uploadFile, document);
				} else {
					documentId = m_documentService.insertDocument(Modules.s_pumpingStation_model, m_uploadFile);
					m_pumpingStation.setDocumentId(documentId);
				}
			}
			m_scheduleService.updateSchedule(m_schedule);
			int count = m_pumpingStationService.updatePumpingStation(m_pumpingStation);
			if (count > 0) {
				Log log = createLog(Modules.s_pumpingStation_model, Operation.s_operation_update, m_pumpingStation);

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

	public String queryAllPumpingStations() {
		m_pumpingStations = m_pumpingStationService.queryLimitedPumpingStations(m_tunnelId, m_tunnelSectionId, 0, Integer.MAX_VALUE);

		return SUCCESS;
	}

	public void setPumpingStation(PumpingStation pumpingStation) {
		m_pumpingStation = pumpingStation;
	}

	public void setPumpingStationId(int pumpingStationId) {
		m_pumpingStationId = pumpingStationId;
	}

	public void setPumpingStationService(PumpingStationService pumpingStationService) {
		m_pumpingStationService = pumpingStationService;
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