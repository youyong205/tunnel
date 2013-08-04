package com.facility;

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

public class FacilityAction extends ScheduledAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(FacilityAction.class);

	private List<Facility> m_facilitys;

	private int m_facilityId;

	private FacilityService m_facilityService;

	private Facility m_facility = new Facility();

	private int m_tunnelId;

	private int m_tunnelSectionId;

	private TunnelService m_tunnelService;

	private TunnelSectionService m_tunnelSectionService;

	private List<Tunnel> m_tunnels;

	private List<TunnelSection> m_tunnelSections;

	@Override
	public String getActionModule() {
		return Modules.s_facility_model;
	}

	public Facility getFacility() {
		return m_facility;
	}

	public List<Facility> getFacilitys() {
		return m_facilitys;
	}

	public int getTunnelId() {
		return m_tunnelId;
	}

	public int getTunnelSectionId() {
		return m_tunnelSectionId;
	}

	public int getParentTunnelSectionId() {
		return m_tunnelSectionId;
	}

	public String facilityAdd() {
		m_tunnels = m_tunnelService.queryAllTunnels();

		if (m_tunnelId == 0) {
			m_tunnelId = m_tunnelService.queryDefaultTunnelId();
		}
		m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);
		m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
		return SUCCESS;
	}

	public String facilityAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_facility_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_documentService.insertDocument(Modules.s_facility_model, m_uploadFile);
				m_facility.setDocumentId(documentId);
			}
			m_schedule.setType(getActionModule());
			int scheduleId = m_scheduleService.insertSchedule(m_schedule);
			m_facility.setScheduleId(scheduleId);

			int id = m_facilityService.insertFacility(m_facility);
			if (id > 0) {
				Log log = createLog(Modules.s_facility_model, Operation.s_operation_add, m_facility);

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

	public String facilityDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_facility_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_facility = m_facilityService.findByPK(m_facilityId);
			m_documentService.deleteDocument(m_facility.getDocumentId());
			m_scheduleService.deleteSchedule(m_facility.getScheduleId());
			int count = m_facilityService.deleteFacility(m_facilityId);
			if (count > 0) {
				Log log = createLog(Modules.s_facility_model, Operation.s_operation_delete, m_facilityId);

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

	public String queryAllFacilitys() {
		m_facilitys = m_facilityService.queryLimitedFacilitys(m_tunnelId, m_tunnelSectionId, 0, Integer.MAX_VALUE);

		return SUCCESS;
	}

	public String facilityList() {
		Authority auth = checkAuthority(buildResource(Modules.s_facility_model, Operation.s_operation_detail));
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

			m_totalSize = m_facilityService.querySizeByTunnelAndSection(m_tunnelId, m_tunnelSectionId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_facilitys = m_facilityService.queryLimitedFacilitys(m_tunnelId, m_tunnelSectionId, start, SIZE);
			for (Facility facility : m_facilitys) {
				facility.setTunnel(m_tunnelService.findByPK(facility.getTunnelId()));
				int scheduleId = facility.getScheduleId();
				if (scheduleId > 0) {
					Schedule schedule = m_scheduleService.findByPK(scheduleId);
					if (schedule != null) {
						facility.setSchedule(schedule);
						schedule.setConstructionUnit(m_constructionUnitService.findByPK(schedule.getConstructionUnitId()));
					}
				}
				if (facility.getDocumentId() > 0) {
					facility.setDocument(m_documentService.findByPK(facility.getDocumentId()));
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String facilityUpdate() {
		try {
			m_facility = m_facilityService.findByPK(m_facilityId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_facility.getTunnelId(), 0,
			      Integer.MAX_VALUE);
			m_schedule = m_scheduleService.findByPK(m_facility.getScheduleId());
			m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
			int documentId = m_facility.getDocumentId();

			if (documentId > 0) {
				m_facility.setDocument(m_documentService.findByPK(m_facility.getDocumentId()));
			}
			if (m_facility != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String facilityUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_facility_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_facility.getDocumentId();
				if (documentId > 0) {
					Document document = m_documentService.findByPK(documentId);
					m_documentService.updateDocument(Modules.s_facility_model, m_uploadFile, document);
				} else {
					documentId = m_documentService.insertDocument(Modules.s_facility_model, m_uploadFile);
					m_facility.setDocumentId(documentId);
				}
			}
			m_scheduleService.updateSchedule(m_schedule);
			int count = m_facilityService.updateFacility(m_facility);
			if (count > 0) {
				Log log = createLog(Modules.s_facility_model, Operation.s_operation_update, m_facility);

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

	public void setFacility(Facility facility) {
		m_facility = facility;
	}

	public void setFacilityId(int facilityId) {
		m_facilityId = facilityId;
	}

	public void setFacilityService(FacilityService facilityService) {
		m_facilityService = facilityService;
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

	public List<Tunnel> getTunnels() {
		return m_tunnels;
	}

	public List<TunnelSection> getTunnelSections() {
		return m_tunnelSections;
	}

}