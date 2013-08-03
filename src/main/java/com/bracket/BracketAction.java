package com.bracket;

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

public class BracketAction extends ScheduledAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(BracketAction.class);

	private List<Bracket> m_brackets;

	private int m_bracketId;

	private BracketService m_bracketService;

	private Bracket m_bracket = new Bracket();

	private int m_tunnelId;

	private int m_tunnelSectionId;

	private TunnelService m_tunnelService;

	private TunnelSectionService m_tunnelSectionService;

	private List<Tunnel> m_tunnels;

	private List<TunnelSection> m_tunnelSections;

	@Override
	public String getActionModule() {
		return Modules.s_bracket_model;
	}

	public Bracket getBracket() {
		return m_bracket;
	}

	public List<Bracket> getBrackets() {
		return m_brackets;
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

	public String bracketAdd() {
		m_tunnels = m_tunnelService.queryAllTunnels();

		if (m_tunnelId == 0) {
			m_tunnelId = m_tunnelService.queryDefaultTunnelId();
		}
		m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);
		m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
		return SUCCESS;
	}

	public String bracketAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_bracket_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_documentService.insertDocument(Modules.s_bracket_model, m_uploadFile);
				m_bracket.setDocumentId(documentId);
			}
			m_schedule.setType(getActionModule());
			int scheduleId = m_scheduleService.insertSchedule(m_schedule);
			m_bracket.setScheduleId(scheduleId);

			int id = m_bracketService.insertBracket(m_bracket);
			if (id > 0) {
				Log log = createLog(Modules.s_bracket_model, Operation.s_operation_add, m_bracket);

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

	public String bracketDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_bracket_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_bracket = m_bracketService.findByPK(m_bracketId);
			m_documentService.deleteDocument(m_bracket.getDocumentId());
			m_scheduleService.deleteSchedule(m_bracket.getScheduleId());
			int count = m_bracketService.deleteBracket(m_bracketId);
			if (count > 0) {
				Log log = createLog(Modules.s_bracket_model, Operation.s_operation_delete, m_bracketId);

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

	public String queryAllBrackets() {
		m_brackets = m_bracketService.queryLimitedBrackets(m_tunnelId, m_tunnelSectionId, 0, Integer.MAX_VALUE);

		return SUCCESS;
	}

	public String bracketList() {
		Authority auth = checkAuthority(buildResource(Modules.s_bracket_model, Operation.s_operation_detail));
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

			m_totalSize = m_bracketService.querySizeByTunnelAndSection(m_tunnelId, m_tunnelSectionId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_brackets = m_bracketService.queryLimitedBrackets(m_tunnelId, m_tunnelSectionId, start, SIZE);
			for (Bracket bracket : m_brackets) {
				bracket.setTunnel(m_tunnelService.findByPK(bracket.getTunnelId()));
				int scheduleId = bracket.getScheduleId();
				if (scheduleId > 0) {
					Schedule schedule = m_scheduleService.findByPK(scheduleId);
					if (schedule != null) {
						bracket.setSchedule(schedule);
						schedule.setConstructionUnit(m_constructionUnitService.findByPK(schedule.getConstructionUnitId()));
					}
				}
				if (bracket.getDocumentId() > 0) {
					bracket.setDocument(m_documentService.findByPK(bracket.getDocumentId()));
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String bracketUpdate() {
		try {
			m_bracket = m_bracketService.findByPK(m_bracketId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_bracket.getTunnelId(), 0,
			      Integer.MAX_VALUE);
			m_schedule = m_scheduleService.findByPK(m_bracket.getScheduleId());
			m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
			int documentId = m_bracket.getDocumentId();

			if (documentId > 0) {
				m_bracket.setDocument(m_documentService.findByPK(m_bracket.getDocumentId()));
			}
			if (m_bracket != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String bracketUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_bracket_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_bracket.getDocumentId();
				if (documentId > 0) {
					Document document = m_documentService.findByPK(documentId);
					m_documentService.updateDocument(Modules.s_bracket_model, m_uploadFile, document);
				} else {
					documentId = m_documentService.insertDocument(Modules.s_bracket_model, m_uploadFile);
					m_bracket.setDocumentId(documentId);
				}
			}
			m_scheduleService.updateSchedule(m_schedule);
			int count = m_bracketService.updateBracket(m_bracket);
			if (count > 0) {
				Log log = createLog(Modules.s_bracket_model, Operation.s_operation_update, m_bracket);

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

	public void setBracket(Bracket bracket) {
		m_bracket = bracket;
	}

	public void setBracketId(int bracketId) {
		m_bracketId = bracketId;
	}

	public void setBracketService(BracketService bracketService) {
		m_bracketService = bracketService;
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