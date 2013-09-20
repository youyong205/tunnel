package com.linePipe;

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

public class LinePipeAction extends ScheduledAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(LinePipeAction.class);

	private List<LinePipe> m_linePipes;

	private int m_linePipeId;

	private LinePipeService m_linePipeService;

	private LinePipe m_linePipe = new LinePipe();

	private int m_tunnelId;

	private int m_tunnelSectionId;

	private TunnelService m_tunnelService;

	private TunnelSectionService m_tunnelSectionService;

	private List<Tunnel> m_tunnels;

	private List<TunnelSection> m_tunnelSections;

	@Override
	public String getActionModule() {
		return Modules.s_linePipe_model;
	}

	public LinePipe getLinePipe() {
		return m_linePipe;
	}

	public List<LinePipe> getLinePipes() {
		return m_linePipes;
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

	public String linePipeAdd() {
		m_tunnels = m_tunnelService.queryAllTunnels();

		if (m_tunnelId == 0) {
			m_tunnelId = m_tunnelService.queryDefaultTunnelId();
		}
		m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);
		m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
		return SUCCESS;
	}

	public String linePipeAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_linePipe_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_documentService.insertDocument(Modules.s_linePipe_model, m_uploadFile);
				m_linePipe.setDocumentId(documentId);
			}
			m_schedule.setType(getActionModule());
			int scheduleId = m_scheduleService.insertSchedule(m_schedule);
			m_linePipe.setScheduleId(scheduleId);

			int id = m_linePipeService.insertLinePipe(m_linePipe);
			if (id > 0) {
				Log log = createLog(Modules.s_linePipe_model, Operation.s_operation_add, m_linePipe);

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

	public String linePipeDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_linePipe_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_linePipe = m_linePipeService.findByPK(m_linePipeId);
			m_documentService.deleteDocument(m_linePipe.getDocumentId());
			m_scheduleService.deleteSchedule(m_linePipe.getScheduleId());
			int count = m_linePipeService.deleteLinePipe(m_linePipeId);
			if (count > 0) {
				Log log = createLog(Modules.s_linePipe_model, Operation.s_operation_delete, m_linePipeId);

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

	public String linePipeList() {
		Authority auth = checkAuthority(buildResource(Modules.s_linePipe_model, Operation.s_operation_detail));
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

			m_totalSize = m_linePipeService.querySizeByTunnelAndSection(m_tunnelId, m_tunnelSectionId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_linePipes = m_linePipeService.queryLimitedLinePipes(m_tunnelId, m_tunnelSectionId, start, SIZE);
			for (LinePipe linePipe : m_linePipes) {
				linePipe.setTunnel(m_tunnelService.findByPK(linePipe.getTunnelId()));
				int scheduleId = linePipe.getScheduleId();
				if (scheduleId > 0) {
					Schedule schedule = m_scheduleService.findByPK(scheduleId);
					if (schedule != null) {
						linePipe.setSchedule(schedule);
						schedule.setConstructionUnit(m_constructionUnitService.findByPK(schedule.getConstructionUnitId()));
					}
				}
				if (linePipe.getDocumentId() > 0) {
					linePipe.setDocument(m_documentService.findByPK(linePipe.getDocumentId()));
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String linePipeUpdate() {
		try {
			m_linePipe = m_linePipeService.findByPK(m_linePipeId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_linePipe.getTunnelId(), 0,
			      Integer.MAX_VALUE);
			m_schedule = m_scheduleService.findByPK(m_linePipe.getScheduleId());
			m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
			int documentId = m_linePipe.getDocumentId();

			if (documentId > 0) {
				m_linePipe.setDocument(m_documentService.findByPK(m_linePipe.getDocumentId()));
			}
			if (m_linePipe != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String linePipeUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_linePipe_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_linePipe.getDocumentId();
				if (documentId > 0) {
					Document document = m_documentService.findByPK(documentId);
					m_documentService.updateDocument(Modules.s_linePipe_model, m_uploadFile, document);
				} else {
					documentId = m_documentService.insertDocument(Modules.s_linePipe_model, m_uploadFile);
					m_linePipe.setDocumentId(documentId);
				}
			}
			m_scheduleService.updateSchedule(m_schedule);
			int count = m_linePipeService.updateLinePipe(m_linePipe);
			if (count > 0) {
				Log log = createLog(Modules.s_linePipe_model, Operation.s_operation_update, m_linePipe);

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

	public String queryAllLinePipes() {
		m_linePipes = m_linePipeService.queryLimitedLinePipes(m_tunnelId, m_tunnelSectionId, 0, Integer.MAX_VALUE);

		return SUCCESS;
	}

	public void setLinePipe(LinePipe linePipe) {
		m_linePipe = linePipe;
	}

	public void setLinePipeId(int linePipeId) {
		m_linePipeId = linePipeId;
	}

	public void setLinePipeService(LinePipeService linePipeService) {
		m_linePipeService = linePipeService;
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