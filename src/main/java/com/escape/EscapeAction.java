package com.escape;

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

public class EscapeAction extends ScheduledAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(EscapeAction.class);

	private List<Escape> m_escapes;

	private int m_escapeId;

	private EscapeService m_escapeService;

	private Escape m_escape = new Escape();

	private int m_tunnelId;

	private int m_tunnelSectionId;

	private TunnelService m_tunnelService;

	private TunnelSectionService m_tunnelSectionService;

	private List<Tunnel> m_tunnels;

	private List<TunnelSection> m_tunnelSections;

	public String escapeAdd() {
		m_tunnels = m_tunnelService.queryAllTunnels();

		if (m_tunnelId == 0) {
			m_tunnelId = m_tunnelService.queryDefaultTunnelId();
		}
		m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);
		m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
		return SUCCESS;
	}

	public String escapeAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_escape_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_documentService.insertDocument(Modules.s_escape_model, m_uploadFile);
				m_escape.setDocumentId(documentId);
			}
			m_schedule.setType(getActionModule());
			int scheduleId = m_scheduleService.insertSchedule(m_schedule);
			m_escape.setScheduleId(scheduleId);

			int id = m_escapeService.insertEscape(m_escape);
			if (id > 0) {
				Log log = createLog(Modules.s_escape_model, Operation.s_operation_add, m_escape);

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

	public String escapeDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_escape_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_escape = m_escapeService.findByPK(m_escapeId);
			m_documentService.deleteDocument(m_escape.getDocumentId());
			m_scheduleService.deleteSchedule(m_escape.getScheduleId());
			int count = m_escapeService.deleteEscape(m_escapeId);
			if (count > 0) {
				Log log = createLog(Modules.s_escape_model, Operation.s_operation_delete, m_escapeId);

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

	public String escapeList() {
		Authority auth = checkAuthority(buildResource(Modules.s_escape_model, Operation.s_operation_detail));
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

			m_totalSize = m_escapeService.querySizeByTunnelAndSection(m_tunnelId, m_tunnelSectionId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_escapes = m_escapeService.queryLimitedEscapes(m_tunnelId, m_tunnelSectionId, start, SIZE);
			for (Escape escape : m_escapes) {
				escape.setTunnel(m_tunnelService.findByPK(escape.getTunnelId()));
				int scheduleId = escape.getScheduleId();
				if (scheduleId > 0) {
					Schedule schedule = m_scheduleService.findByPK(scheduleId);
					if (schedule != null) {
						escape.setSchedule(schedule);
						schedule.setConstructionUnit(m_constructionUnitService.findByPK(schedule.getConstructionUnitId()));
					}
				}
				if (escape.getDocumentId() > 0) {
					escape.setDocument(m_documentService.findByPK(escape.getDocumentId()));
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String escapeUpdate() {
		try {
			m_escape = m_escapeService.findByPK(m_escapeId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_escape.getTunnelId(), 0,
			      Integer.MAX_VALUE);
			m_schedule = m_scheduleService.findByPK(m_escape.getScheduleId());
			m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
			int documentId = m_escape.getDocumentId();

			if (documentId > 0) {
				m_escape.setDocument(m_documentService.findByPK(m_escape.getDocumentId()));
			}
			if (m_escape != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String escapeUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_escape_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_escape.getDocumentId();
				if (documentId > 0) {
					Document document = m_documentService.findByPK(documentId);
					m_documentService.updateDocument(Modules.s_escape_model, m_uploadFile, document);
				} else {
					documentId = m_documentService.insertDocument(Modules.s_escape_model, m_uploadFile);
					m_escape.setDocumentId(documentId);
				}
			}
			m_scheduleService.updateSchedule(m_schedule);
			int count = m_escapeService.updateEscape(m_escape);
			if (count > 0) {
				Log log = createLog(Modules.s_escape_model, Operation.s_operation_update, m_escape);

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
		return Modules.s_escape_model;
	}

	public Escape getEscape() {
		return m_escape;
	}

	public List<Escape> getEscapes() {
		return m_escapes;
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

	public String queryAllEscapes() {
		m_escapes = m_escapeService.queryLimitedEscapes(m_tunnelId, m_tunnelSectionId, 0, Integer.MAX_VALUE);

		return SUCCESS;
	}

	public void setEscape(Escape escape) {
		m_escape = escape;
	}

	public void setEscapeId(int escapeId) {
		m_escapeId = escapeId;
	}

	public void setEscapeService(EscapeService escapeService) {
		m_escapeService = escapeService;
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