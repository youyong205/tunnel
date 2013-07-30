package com.buriedSection;

import java.util.List;

import org.apache.log4j.Logger;

import com.Constrants;
import com.ScheduledAction;
import com.document.Document;
import com.log.Log;
import com.schedule.Schedule;
import com.tunnel.Tunnel;
import com.tunnel.TunnelService;

public class BuriedSectionAction extends ScheduledAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(BuriedSectionAction.class);

	private List<BuriedSection> m_buriedSections;

	private List<Tunnel> m_tunnels;

	private int m_buriedSectionId;

	private int m_tunnelId;

	private BuriedSectionService m_buriedSectionService;

	private TunnelService m_tunnelService;

	private BuriedSection m_buriedSection = new BuriedSection();

	public String buriedSectionAdd() {
		try {
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e);
			return ERROR;
		}
	}

	public String buriedSectionAddSubmit() {
		try {
			int documentId = 0;
			if (m_uploadFile.getFile() != null) {
				documentId = m_documentService.insertDocument(Constrants.s_buriedSection_model, m_uploadFile);
			}
			m_buriedSection.setDocumentId(documentId);
			m_schedule.setType(getActionModule());
			int scheduleId = m_scheduleService.insertSchedule(m_schedule);
			m_buriedSection.setScheduleId(scheduleId);

			int id = m_buriedSectionService.insertBuriedSection(m_buriedSection);
			if (id > 0) {
				Log log = createLog(Constrants.s_buriedSection_model, Constrants.s_operation_add, m_buriedSection);

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

	public String buriedSectionDelete() {
		try {
			int count = m_buriedSectionService.deleteBuriedSection(m_buriedSectionId);
			if (count > 0) {
				Log log = createLog(Constrants.s_buriedSection_model, Constrants.s_operation_delete, m_buriedSectionId);

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

	public String buriedSectionList() {
		try {
			if (m_tunnelId == 0) {
				m_tunnelId = m_tunnelService.queryDefaultTunnelId();
			}
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_totalSize = m_buriedSectionService.querySizeByTunnelId(m_tunnelId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_buriedSections = m_buriedSectionService.queryLimitedBuriedSectionsByTunnelId(m_tunnelId, start, SIZE);
			for (BuriedSection channel : m_buriedSections) {
				channel.setTunnel(m_tunnelService.findByPK(channel.getTunnelId()));
				int scheduleId = channel.getScheduleId();
				if (scheduleId > 0) {
					Schedule schedule = m_scheduleService.findByPK(scheduleId);
					if (schedule != null) {
						channel.setSchedule(schedule);
						schedule.setConstructionUnit(m_constructionUnitService.findByPK(schedule.getConstructionUnitId()));
					}
				}
				if (channel.getDocumentId() > 0) {
					channel.setDocument(m_documentService.findByPK(channel.getDocumentId()));
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String buriedSectionUpdate() {
		try {
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_buriedSection = m_buriedSectionService.findByPK(m_buriedSectionId);
			m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
			m_schedule = m_scheduleService.findByPK(m_buriedSection.getScheduleId());
			if (m_buriedSection != null) {
				m_buriedSection.setTunnel(m_tunnelService.findByPK(m_buriedSection.getTunnelId()));

				if (m_buriedSection.getDocumentId() > 0) {
					m_buriedSection.setDocument(m_documentService.findByPK(m_buriedSection.getDocumentId()));
				}
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String buriedSectionUpdateSubmit() {
		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_buriedSection.getDocumentId();
				if (documentId > 0) {
					Document document = m_documentService.findByPK(documentId);
					m_documentService.updateDocument(Constrants.s_contactChannel_model, m_uploadFile, document);
				} else {
					documentId = m_documentService.insertDocument(Constrants.s_contactChannel_model, m_uploadFile);
					m_buriedSection.setDocumentId(documentId);
				}
			}
			m_scheduleService.updateSchedule(m_schedule);
			int count = m_buriedSectionService.updateBuriedSection(m_buriedSection);
			if (count > 0) {
				Log log = createLog(Constrants.s_buriedSection_model, Constrants.s_operation_update, m_buriedSection);

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

	@Override
	public String getActionModule() {
		return Constrants.s_buriedSection_model;
	}

	public BuriedSection getBuriedSection() {
		return m_buriedSection;
	}

	public List<BuriedSection> getBuriedSections() {
		return m_buriedSections;
	}

	public int getTunnelId() {
		return m_tunnelId;
	}

	public int getTunnelIndexId() {
		return m_tunnelId;
	}

	public List<Tunnel> getTunnels() {
		return m_tunnels;
	}

	public void setBuriedSection(BuriedSection buriedSection) {
		m_buriedSection = buriedSection;
	}

	public void setBuriedSectionId(int buriedSectionId) {
		m_buriedSectionId = buriedSectionId;
	}

	public void setBuriedSectionService(BuriedSectionService buriedSectionService) {
		m_buriedSectionService = buriedSectionService;
	}

	public void setTunnelId(int tunnelId) {
		m_tunnelId = tunnelId;
	}

	public void setTunnelService(TunnelService tunnelService) {
		m_tunnelService = tunnelService;
	}

}