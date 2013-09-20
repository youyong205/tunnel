package com.flueSheet;

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

public class FlueSheetAction extends ScheduledAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(FlueSheetAction.class);

	private List<FlueSheet> m_flueSheets;

	private int m_flueSheetId;

	private FlueSheetService m_flueSheetService;

	private FlueSheet m_flueSheet = new FlueSheet();

	private int m_tunnelId;

	private int m_tunnelSectionId;

	private TunnelService m_tunnelService;

	private TunnelSectionService m_tunnelSectionService;

	private List<Tunnel> m_tunnels;

	private List<TunnelSection> m_tunnelSections;

	public String flueSheetAdd() {
		m_tunnels = m_tunnelService.queryAllTunnels();

		if (m_tunnelId == 0) {
			m_tunnelId = m_tunnelService.queryDefaultTunnelId();
		}
		m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);
		m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
		return SUCCESS;
	}

	public String flueSheetAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_flueSheet_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}

		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_documentService.insertDocument(Modules.s_flueSheet_model, m_uploadFile);
				m_flueSheet.setDocumentId(documentId);
			}
			m_schedule.setType(getActionModule());
			int scheduleId = m_scheduleService.insertSchedule(m_schedule);
			m_flueSheet.setScheduleId(scheduleId);

			int id = m_flueSheetService.insertFlueSheet(m_flueSheet);
			if (id > 0) {
				Log log = createLog(Modules.s_flueSheet_model, Operation.s_operation_add, m_flueSheet);

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

	public String flueSheetDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_flueSheet_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_flueSheet = m_flueSheetService.findByPK(m_flueSheetId);
			m_documentService.deleteDocument(m_flueSheet.getDocumentId());
			m_scheduleService.deleteSchedule(m_flueSheet.getScheduleId());
			int count = m_flueSheetService.deleteFlueSheet(m_flueSheetId);
			if (count > 0) {
				Log log = createLog(Modules.s_flueSheet_model, Operation.s_operation_delete,
				      m_flueSheetId);

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

	public String flueSheetList() {
		Authority auth = checkAuthority(buildResource(Modules.s_flueSheet_model, Operation.s_operation_delete));
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

			m_totalSize = m_flueSheetService.querySizeByTunnelAndSection(m_tunnelId, m_tunnelSectionId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_flueSheets = m_flueSheetService.queryLimitedFlueSheets(m_tunnelId,
			      m_tunnelSectionId, start, SIZE);
			for (FlueSheet flueSheet : m_flueSheets) {
				flueSheet.setTunnel(m_tunnelService.findByPK(flueSheet.getTunnelId()));
				int scheduleId = flueSheet.getScheduleId();
				if (scheduleId > 0) {
					Schedule schedule = m_scheduleService.findByPK(scheduleId);
					if (schedule != null) {
						flueSheet.setSchedule(schedule);
						schedule.setConstructionUnit(m_constructionUnitService.findByPK(schedule.getConstructionUnitId()));
					}
				}
				if (flueSheet.getDocumentId() > 0) {
					flueSheet.setDocument(m_documentService.findByPK(flueSheet.getDocumentId()));
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String flueSheetUpdate() {
		try {
			m_flueSheet = m_flueSheetService.findByPK(m_flueSheetId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(
			      m_flueSheet.getTunnelId(), 0, Integer.MAX_VALUE);
			m_schedule = m_scheduleService.findByPK(m_flueSheet.getScheduleId());
			m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
			int documentId = m_flueSheet.getDocumentId();

			if (documentId > 0) {
				m_flueSheet.setDocument(m_documentService.findByPK(m_flueSheet.getDocumentId()));
			}
			if (m_flueSheet != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String flueSheetUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_flueSheet_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}

		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_flueSheet.getDocumentId();
				if (documentId > 0) {
					Document document = m_documentService.findByPK(documentId);
					m_documentService.updateDocument(Modules.s_flueSheet_model, m_uploadFile, document);
				} else {
					documentId = m_documentService.insertDocument(Modules.s_flueSheet_model, m_uploadFile);
					m_flueSheet.setDocumentId(documentId);
				}
			}
			m_scheduleService.updateSchedule(m_schedule);
			int count = m_flueSheetService.updateFlueSheet(m_flueSheet);
			if (count > 0) {
				Log log = createLog(Modules.s_flueSheet_model, Operation.s_operation_update,
				      m_flueSheet);

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
		return Modules.s_flueSheet_model;
	}

	public FlueSheet getFlueSheet() {
		return m_flueSheet;
	}

	public List<FlueSheet> getFlueSheets() {
		return m_flueSheets;
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

	public String queryAllFlueSheets() {
		m_flueSheets = m_flueSheetService.queryLimitedFlueSheets(m_tunnelId,
		      m_tunnelSectionId, 0, Integer.MAX_VALUE);

		return SUCCESS;
	}

	public void setFlueSheet(FlueSheet flueSheet) {
		m_flueSheet = flueSheet;
	}

	public void setFlueSheetId(int flueSheetId) {
		m_flueSheetId = flueSheetId;
	}

	public void setFlueSheetService(FlueSheetService flueSheetService) {
		m_flueSheetService = flueSheetService;
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