package com.contactChannel;

import java.util.List;

import org.apache.log4j.Logger;

import com.Constrants;
import com.ScheduledAction;
import com.document.Document;
import com.log.Log;
import com.schedule.Schedule;
import com.tunnel.Tunnel;
import com.tunnel.TunnelService;

public class ContactChannelAction extends ScheduledAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(ContactChannelAction.class);

	private List<ContactChannel> m_contactChannels;

	private List<Tunnel> m_tunnels;

	private int m_contactChannelId;

	private int m_tunnelId;

	private ContactChannelService m_contactChannelService;

	private TunnelService m_tunnelService;

	private ContactChannel m_contactChannel = new ContactChannel();

	public String contactChannelAdd() {
		try {
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e);
			return ERROR;
		}
	}

	public String contactChannelAddSubmit() {
		try {
			int documentId = 0;
			if (m_uploadFile.getFile() != null) {
				documentId = m_documentService.insertDocument(Constrants.s_contactChannel_model, m_uploadFile);
			}
			m_schedule.setType(getActionModule());
			int scheduleId = m_scheduleService.insertSchedule(m_schedule);
			m_contactChannel.setScheduleId(scheduleId);
			m_contactChannel.setDocumentId(documentId);
			int id = m_contactChannelService.insertContactChannel(m_contactChannel);
			if (id > 0) {
				Log log = createLog(Constrants.s_contactChannel_model, Constrants.s_operation_add, m_contactChannel);

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

	public String contactChannelDelete() {
		try {
			m_contactChannel = m_contactChannelService.findByPK(m_contactChannelId);
			m_scheduleService.deleteSchedule(m_contactChannel.getScheduleId());
			m_documentService.deleteDocument(m_contactChannel.getDocumentId());
			int count = m_contactChannelService.deleteContactChannel(m_contactChannelId);
			if (count > 0) {
				Log log = createLog(Constrants.s_contactChannel_model, Constrants.s_operation_delete, m_contactChannelId);

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

	public String contactChannelList() {
		try {
			if (m_tunnelId == 0) {
				m_tunnelId = m_tunnelService.queryDefaultTunnelId();
			}
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_totalSize = m_contactChannelService.querySizeByTunnelId(m_tunnelId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_contactChannels = m_contactChannelService.queryLimitedContactChannelsByTunnelId(m_tunnelId, start, SIZE);
			for (ContactChannel channel : m_contactChannels) {
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

	public String contactChannelUpdate() {
		try {
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
			m_contactChannel = m_contactChannelService.findByPK(m_contactChannelId);
			m_schedule = m_scheduleService.findByPK(m_contactChannel.getScheduleId());
			if (m_contactChannel != null) {
				m_contactChannel.setTunnel(m_tunnelService.findByPK(m_contactChannel.getTunnelId()));

				if (m_contactChannel.getDocumentId() > 0) {
					m_contactChannel.setDocument(m_documentService.findByPK(m_contactChannel.getDocumentId()));
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

	public String contactChannelUpdateSubmit() {
		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_contactChannel.getDocumentId();

				if (documentId > 0) {
					Document document = m_documentService.findByPK(documentId);
					m_documentService.updateDocument(Constrants.s_contactChannel_model, m_uploadFile, document);
				} else {
					documentId = m_documentService.insertDocument(Constrants.s_contactChannel_model, m_uploadFile);
					m_contactChannel.setDocumentId(documentId);
				}
			}
			m_scheduleService.updateSchedule(m_schedule);
			int count = m_contactChannelService.updateContactChannel(m_contactChannel);
			if (count > 0) {
				Log log = createLog(Constrants.s_contactChannel_model, Constrants.s_operation_update, m_contactChannel);

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
		return Constrants.s_contactChannel_model;
	}

	public ContactChannel getContactChannel() {
		return m_contactChannel;
	}

	public List<ContactChannel> getContactChannels() {
		return m_contactChannels;
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

	public void setContactChannel(ContactChannel contactChannel) {
		m_contactChannel = contactChannel;
	}

	public void setContactChannelId(int contactChannelId) {
		m_contactChannelId = contactChannelId;
	}

	public void setContactChannelService(ContactChannelService contactChannelService) {
		m_contactChannelService = contactChannelService;
	}

	public void setTunnelId(int tunnelId) {
		m_tunnelId = tunnelId;
	}

	public void setTunnelService(TunnelService tunnelService) {
		m_tunnelService = tunnelService;
	}

}