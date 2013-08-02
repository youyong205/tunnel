package com.rectangleComponent;

import java.util.List;

import org.apache.log4j.Logger;

import com.Constrants;
import com.ScheduledAction;
import com.document.Document;
import com.log.Log;
import com.schedule.Schedule;
import com.tunnel.Tunnel;
import com.tunnel.TunnelService;
import com.tunnelSection.TunnelSection;
import com.tunnelSection.TunnelSectionService;

public class RectangleComponentAction extends ScheduledAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(RectangleComponentAction.class);

	private List<RectangleComponent> m_rectangleComponents;

	private int m_rectangleComponentId;

	private RectangleComponentService m_rectangleComponentService;

	private RectangleComponent m_rectangleComponent = new RectangleComponent();

	private int m_tunnelId;

	private int m_tunnelSectionId;

	private TunnelService m_tunnelService;

	private TunnelSectionService m_tunnelSectionService;

	private List<Tunnel> m_tunnels;

	private List<TunnelSection> m_tunnelSections;

	@Override
	public String getActionModule() {
		return Constrants.s_rectangleComponent_model;
	}

	public RectangleComponent getRectangleComponent() {
		return m_rectangleComponent;
	}

	public List<RectangleComponent> getRectangleComponents() {
		return m_rectangleComponents;
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

	public String rectangleComponentAdd() {
		m_tunnels = m_tunnelService.queryAllTunnels();

		if (m_tunnelId == 0) {
			m_tunnelId = m_tunnelService.queryDefaultTunnelId();
		}
		m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);
		m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
		return SUCCESS;
	}

	public String rectangleComponentAddSubmit() {
		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_documentService.insertDocument(Constrants.s_contactChannel_model, m_uploadFile);
				m_rectangleComponent.setDocumentId(documentId);
			}
			m_schedule.setType(getActionModule());
			int scheduleId = m_scheduleService.insertSchedule(m_schedule);
			m_rectangleComponent.setScheduleId(scheduleId);

			int id = m_rectangleComponentService.insertRectangleComponent(m_rectangleComponent);
			if (id > 0) {
				Log log = createLog(Constrants.s_rectangleComponent_model, Constrants.s_operation_add, m_rectangleComponent);

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

	public String rectangleComponentDelete() {
		try {
			m_rectangleComponent = m_rectangleComponentService.findByPK(m_rectangleComponentId);
			m_documentService.deleteDocument(m_rectangleComponent.getDocumentId());
			m_scheduleService.deleteSchedule(m_rectangleComponent.getScheduleId());
			int count = m_rectangleComponentService.deleteRectangleComponent(m_rectangleComponentId);
			if (count > 0) {
				Log log = createLog(Constrants.s_rectangleComponent_model, Constrants.s_operation_delete,
				      m_rectangleComponentId);

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

	public String queryAllRectangleComponents() {
		m_rectangleComponents = m_rectangleComponentService.queryLimitedRectangleComponents(m_tunnelId,
		      m_tunnelSectionId, 0, Integer.MAX_VALUE);

		return SUCCESS;
	}

	public String rectangleComponentList() {
		try {
			if (m_tunnelId == 0) {
				m_tunnelId = m_tunnelService.queryDefaultTunnelId();
			}
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0,
			      Integer.MAX_VALUE);

			m_totalSize = m_rectangleComponentService.querySizeByTunnelAndSection(m_tunnelId, m_tunnelSectionId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_rectangleComponents = m_rectangleComponentService.queryLimitedRectangleComponents(m_tunnelId,
			      m_tunnelSectionId, start, SIZE);
			for (RectangleComponent rectangleComponent : m_rectangleComponents) {
				rectangleComponent.setTunnel(m_tunnelService.findByPK(rectangleComponent.getTunnelId()));
				int scheduleId = rectangleComponent.getScheduleId();
				if (scheduleId > 0) {
					Schedule schedule = m_scheduleService.findByPK(scheduleId);
					if (schedule != null) {
						rectangleComponent.setSchedule(schedule);
						schedule.setConstructionUnit(m_constructionUnitService.findByPK(schedule.getConstructionUnitId()));
					}
				}
				if (rectangleComponent.getDocumentId() > 0) {
					rectangleComponent.setDocument(m_documentService.findByPK(rectangleComponent.getDocumentId()));
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String rectangleComponentUpdate() {
		try {
			m_rectangleComponent = m_rectangleComponentService.findByPK(m_rectangleComponentId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(
			      m_rectangleComponent.getTunnelId(), 0, Integer.MAX_VALUE);
			m_schedule = m_scheduleService.findByPK(m_rectangleComponent.getScheduleId());
			m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
			int documentId = m_rectangleComponent.getDocumentId();

			if (documentId > 0) {
				m_rectangleComponent.setDocument(m_documentService.findByPK(m_rectangleComponent.getDocumentId()));
			}
			if (m_rectangleComponent != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String rectangleComponentUpdateSubmit() {
		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_rectangleComponent.getDocumentId();
				if (documentId > 0) {
					Document document = m_documentService.findByPK(documentId);
					m_documentService.updateDocument(Constrants.s_contactChannel_model, m_uploadFile, document);
				} else {
					documentId = m_documentService.insertDocument(Constrants.s_contactChannel_model, m_uploadFile);
					m_rectangleComponent.setDocumentId(documentId);
				}
			}
			m_scheduleService.updateSchedule(m_schedule);
			int count = m_rectangleComponentService.updateRectangleComponent(m_rectangleComponent);
			if (count > 0) {
				Log log = createLog(Constrants.s_rectangleComponent_model, Constrants.s_operation_update,
				      m_rectangleComponent);

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

	public void setRectangleComponent(RectangleComponent rectangleComponent) {
		m_rectangleComponent = rectangleComponent;
	}

	public void setRectangleComponentId(int rectangleComponentId) {
		m_rectangleComponentId = rectangleComponentId;
	}

	public void setRectangleComponentService(RectangleComponentService rectangleComponentService) {
		m_rectangleComponentService = rectangleComponentService;
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