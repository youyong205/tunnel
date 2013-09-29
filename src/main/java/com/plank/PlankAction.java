package com.plank;

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

public class PlankAction extends ScheduledAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(PlankAction.class);

	private List<Plank> m_planks;

	private int m_plankId;

	private PlankService m_plankService;

	private Plank m_plank = new Plank();

	private int m_tunnelId;

	private int m_tunnelSectionId;

	private TunnelService m_tunnelService;

	private TunnelSectionService m_tunnelSectionService;

	private List<Tunnel> m_tunnels;

	private List<TunnelSection> m_tunnelSections;

	@Override
	public String getActionModule() {
		return Modules.s_plank_model;
	}

	public int getParentTunnelSectionId() {
		return m_tunnelSectionId;
	}

	public Plank getPlank() {
		return m_plank;
	}

	public List<Plank> getPlanks() {
		return m_planks;
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

	public String plankAdd() {
		m_tunnels = m_tunnelService.queryAllTunnels();

		if (m_tunnelId == 0) {
			m_tunnelId = m_tunnelService.queryDefaultTunnelId();
		}
		m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);
		m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
		return SUCCESS;
	}

	public String plankAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_plank_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_documentService.insertDocument(Modules.s_plank_model, m_uploadFile);
				m_plank.setDocumentId(documentId);
			}
			m_schedule.setType(getActionModule());
			int scheduleId = m_scheduleService.insertSchedule(m_schedule);
			m_plank.setScheduleId(scheduleId);

			int id = m_plankService.insertPlank(m_plank);
			if (id > 0) {
				Log log = createLog(Modules.s_plank_model, Operation.s_operation_add, m_plank);

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

	public String plankDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_plank_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_plank = m_plankService.findByPK(m_plankId);
			m_documentService.deleteDocument(m_plank.getDocumentId());
			m_scheduleService.deleteSchedule(m_plank.getScheduleId());
			int count = m_plankService.deletePlank(m_plankId);
			if (count > 0) {
				Log log = createLog(Modules.s_plank_model, Operation.s_operation_delete, m_plankId);

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

	public String plankList() {
		Authority auth = checkAuthority(buildResource(Modules.s_plank_model, Operation.s_operation_detail));
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

			m_totalSize = m_plankService.querySizeByTunnelAndSection(m_tunnelId, m_tunnelSectionId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_planks = m_plankService.queryLimitedPlanks(m_tunnelId, m_tunnelSectionId, start, SIZE);
			for (Plank plank : m_planks) {
				plank.setTunnel(m_tunnelService.findByPK(plank.getTunnelId()));
				int scheduleId = plank.getScheduleId();
				if (scheduleId > 0) {
					Schedule schedule = m_scheduleService.findByPK(scheduleId);
					if (schedule != null) {
						plank.setSchedule(schedule);
						schedule.setConstructionUnit(m_constructionUnitService.findByPK(schedule.getConstructionUnitId()));
					}
				}
				if (plank.getDocumentId() > 0) {
					plank.setDocument(m_documentService.findByPK(plank.getDocumentId()));
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String plankUpdate() {
		try {
			m_plank = m_plankService.findByPK(m_plankId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_plank.getTunnelId(), 0,
			      Integer.MAX_VALUE);
			m_schedule = m_scheduleService.findByPK(m_plank.getScheduleId());
			m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
			int documentId = m_plank.getDocumentId();

			if (documentId > 0) {
				m_plank.setDocument(m_documentService.findByPK(m_plank.getDocumentId()));
			}
			if (m_plank != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String plankUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_plank_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_plank.getDocumentId();
				if (documentId > 0) {
					Document document = m_documentService.findByPK(documentId);
					m_documentService.updateDocument(Modules.s_plank_model, m_uploadFile, document);
				} else {
					documentId = m_documentService.insertDocument(Modules.s_plank_model, m_uploadFile);
					m_plank.setDocumentId(documentId);
				}
			}
			m_scheduleService.updateSchedule(m_schedule);
			int count = m_plankService.updatePlank(m_plank);
			if (count > 0) {
				Log log = createLog(Modules.s_plank_model, Operation.s_operation_update, m_plank);

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

	public String queryAllPlanks() {
		m_planks = m_plankService.queryLimitedPlanks(m_tunnelId, m_tunnelSectionId, 0, Integer.MAX_VALUE);

		return SUCCESS;
	}

	public void setPlank(Plank plank) {
		m_plank = plank;
	}

	public void setPlankId(int plankId) {
		m_plankId = plankId;
	}

	public void setPlankService(PlankService plankService) {
		m_plankService = plankService;
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