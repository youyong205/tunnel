package com.workingWell;

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

public class WorkingWellAction extends ScheduledAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(WorkingWellAction.class);

	private List<WorkingWell> m_workingWells;

	private List<Tunnel> m_tunnels;

	private List<TunnelSection> m_tunnelSections;

	private int m_workingWellId;

	private int m_tunnelId;

	private WorkingWellService m_workingWellService;

	private TunnelService m_tunnelService;

	private TunnelSectionService m_tunnelSectionService;

	private Integer[] m_tunnelSectionIdSelect;

	private WorkingWell m_workingWell = new WorkingWell();

	@Override
	public String getActionModule() {
		return Modules.s_workingWell_model;
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

	public WorkingWell getWorkingWell() {
		return m_workingWell;
	}

	public List<WorkingWell> getWorkingWells() {
		return m_workingWells;
	}

	public String queryWorkingWellListsByTunnelId() {
		if (m_tunnelId == 0) {
			m_tunnelId = m_tunnelService.queryDefaultTunnelId();
		}

		m_workingWells = m_workingWellService.queryLimitedWorkingWellsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);
		return SUCCESS;
	}

	public void setTunnelId(int tunnelId) {
		m_tunnelId = tunnelId;
	}

	public void setTunnelService(TunnelService tunnelService) {
		m_tunnelService = tunnelService;
	}

	public void setWorkingWell(WorkingWell workingWell) {
		m_workingWell = workingWell;
	}

	public void setWorkingWellId(int workingWellId) {
		m_workingWellId = workingWellId;
	}

	public void setWorkingWellService(WorkingWellService workingWellService) {
		m_workingWellService = workingWellService;
	}

	public String workingWellAdd() {
		try {
			m_tunnels = m_tunnelService.queryAllTunnels();
			if (m_tunnelId == 0) {
				m_tunnelId = m_tunnelService.queryDefaultTunnelId();
			}
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0,
			      Integer.MAX_VALUE);
			m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e);
			return ERROR;
		}
	}

	public String workingWellAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_workingWell_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int documentId = 0;
			if (m_uploadFile.getFile() != null) {
				documentId = m_documentService.insertDocument(Modules.s_workingWell_model, m_uploadFile);
			}
			m_schedule.setType(getActionModule());
			int scheduleId = m_scheduleService.insertSchedule(m_schedule);
			m_workingWell.setScheduleId(scheduleId);

			m_workingWell.setDocumentId(documentId);
			int count = m_workingWellService.insertWorkingWell(m_workingWell);

			if (m_tunnelSectionIdSelect != null) {
				for (Integer i : m_tunnelSectionIdSelect) {
					if (i != null && i > 0) {
						WorkingWellPosition position = new WorkingWellPosition();
						System.out.println("workingWellId = " +i+" tunnelSectionId:"+count);
						
						position.setWorkingWellId(m_workingWell.getId());
						position.setTunnelSectionId(i);

						m_workingWellService.insertWorkingWellPosition(position);
					}
				}
			}
			if (count > 0) {
				Log log = createLog(Modules.s_workingWell_model, Operation.s_operation_add, m_workingWell);

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

	public String workingWellDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_workingWell_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_workingWell = m_workingWellService.findByPK(m_workingWellId);
			m_scheduleService.deleteSchedule(m_workingWell.getScheduleId());
			m_documentService.deleteDocument(m_workingWell.getDocumentId());
			int count = m_workingWellService.deleteWorkingWell(m_workingWellId);
			if (count > 0) {
				Log log = createLog(Modules.s_workingWell_model, Operation.s_operation_delete, m_workingWellId);

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

	public String workingWellList() {
		Authority auth = checkAuthority(buildResource(Modules.s_workingWell_model, Operation.s_operation_detail));
		if (auth != null) {
			return auth.getName();
		}
		try {
			if (m_tunnelId == 0) {
				m_tunnelId = m_tunnelService.queryDefaultTunnelId();
			}
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_totalSize = m_workingWellService.querySizeByTunnelId(m_tunnelId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_workingWells = m_workingWellService.queryLimitedWorkingWellsByTunnelId(m_tunnelId, start, SIZE);
			for (WorkingWell channel : m_workingWells) {
				channel.setTunnel(m_tunnelService.findByPK(channel.getTunnelId()));

				if (channel.getDocumentId() > 0) {
					channel.setDocument(m_documentService.findByPK(channel.getDocumentId()));
				}
				int scheduleId = channel.getScheduleId();
				Schedule schedule = m_scheduleService.findByPK(scheduleId);
				if (schedule != null) {
					channel.setSchedule(schedule);
					schedule.setConstructionUnit(m_constructionUnitService.findByPK(schedule.getConstructionUnitId()));
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String workingWellUpdate() {
		try {
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_workingWell = m_workingWellService.findByPK(m_workingWellId);
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_workingWell.getTunnelId(), 0,
			      Integer.MAX_VALUE);
			List<WorkingWellPosition> positions = m_workingWellService.queryWorkingWellPositions(m_workingWellId);

			if (positions != null) {
				int positionSize = positions.size();
				m_tunnelSectionIdSelect = new Integer[positionSize];

				for (int i = 0; i < positionSize; i++) {
					m_tunnelSectionIdSelect[i] = positions.get(i).getTunnelSectionId();
				}
			}
			m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
			m_schedule = m_scheduleService.findByPK(m_workingWell.getScheduleId());

			if (m_workingWell != null) {
				m_workingWell.setTunnel(m_tunnelService.findByPK(m_workingWell.getTunnelId()));

				if (m_workingWell.getDocumentId() > 0) {
					m_workingWell.setDocument(m_documentService.findByPK(m_workingWell.getDocumentId()));
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

	public String workingWellUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_workingWell_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_workingWell.getDocumentId();
				if (documentId > 0) {
					Document document = m_documentService.findByPK(documentId);
					m_documentService.updateDocument(Modules.s_workingWell_model, m_uploadFile, document);
				} else {
					documentId = m_documentService.insertDocument(Modules.s_workingWell_model, m_uploadFile);
					m_workingWell.setDocumentId(documentId);
				}
			}
			m_scheduleService.updateSchedule(m_schedule);
			int count = m_workingWellService.updateWorkingWell(m_workingWell);
			if (m_tunnelSectionIdSelect != null) {
				m_workingWellService.deleteByWorkingWellId(m_workingWell.getId());
				for (Integer i : m_tunnelSectionIdSelect) {
					if (i != null && i > 0) {
						WorkingWellPosition position = new WorkingWellPosition();

						position.setWorkingWellId(m_workingWell.getId());
						position.setTunnelSectionId(i);

						m_workingWellService.insertWorkingWellPosition(position);
					}
				}
			}

			if (count > 0) {
				Log log = createLog(Modules.s_workingWell_model, Operation.s_operation_update, m_workingWell);

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

	public List<TunnelSection> getTunnelSections() {
		return m_tunnelSections;
	}

	public void setTunnelSectionService(TunnelSectionService tunnelSectionService) {
		m_tunnelSectionService = tunnelSectionService;
	}

	public Integer[] getTunnelSectionIdSelect() {
		return m_tunnelSectionIdSelect;
	}

	public void setTunnelSectionIdSelect(Integer[] tunnelSectionIdSelect) {
		m_tunnelSectionIdSelect = tunnelSectionIdSelect;
	}

}