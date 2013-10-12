package com.mailRecord;

import java.util.List;

import org.apache.log4j.Logger;

import com.Authority;
import com.Modules;
import com.Operation;
import com.PagedAction;
import com.log.Log;
import com.tunnel.Tunnel;
import com.tunnel.TunnelService;

public class MailRecordAction extends PagedAction {

	private static final long serialVersionUID = 4901652667413788534L;

	private Logger m_logger = Logger.getLogger(MailRecordAction.class);

	private List<MailRecord> m_mailRecords;

	private int m_mailRecordId;
	
	private int m_tunnelId;
	
	private int m_type;
	
	private MailRecordService m_mailRecordService;

	private MailRecord m_mailRecord = new MailRecord();
	
	private List<Tunnel> m_tunnels;
	
	private TunnelService m_tunnelService;

	@Override
	public String getActionModule() {
		return Modules.s_mailRecord_model;
	}

	public MailRecord getMailRecord() {
		return m_mailRecord;
	}

	public List<MailRecord> getMailRecords() {
		return m_mailRecords;
	}

	public String mailRecordAdd() {
		m_tunnels = m_tunnelService.queryAllTunnels();
		return SUCCESS;
	}

	public String mailRecordAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_mailRecord_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int id = m_mailRecordService.insertMailRecord(m_mailRecord);
			if (id > 0) {
				Log log = createLog(Modules.s_mailRecord_model, Operation.s_operation_add, m_mailRecord);

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

	public String mailRecordDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_mailRecord_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int count = m_mailRecordService.deleteMailRecord(m_mailRecordId);
			if (count > 0) {
				Log log = createLog(Modules.s_mailRecord_model, Operation.s_operation_delete, m_mailRecordId);

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

	public String mailRecordList() {
		Authority auth = checkAuthority(buildResource(Modules.s_mailRecord_model, Operation.s_operation_detail));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_totalSize = m_mailRecordService.queryAllSizeByTunnelAndType(m_tunnelId,m_type);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_mailRecords = m_mailRecordService.queryLimitedMailRecordsByTunnelAndType(m_tunnelId,m_type,start, SIZE);
			
			for(MailRecord mailRecord:m_mailRecords){
				mailRecord.setTunnel(m_tunnelService.findByPK(mailRecord.getTunnelId()));
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String mailRecordUpdate() {
		try {
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_mailRecord = m_mailRecordService.findByPK(m_mailRecordId);
			if (m_mailRecord != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String mailRecordUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_mailRecord_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int count = m_mailRecordService.updateMailRecord(m_mailRecord);
			if (count > 0) {
				Log log = createLog(Modules.s_mailRecord_model, Operation.s_operation_update, m_mailRecord);

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

	public void setMailRecord(MailRecord mailRecord) {
		m_mailRecord = mailRecord;
	}

	public void setMailRecordId(int mailRecordId) {
		m_mailRecordId = mailRecordId;
	}

	public void setMailRecordService(MailRecordService mailRecordService) {
		m_mailRecordService = mailRecordService;
	}

	public List<Tunnel> getTunnels() {
   	return m_tunnels;
   }

	public void setTunnelService(TunnelService tunnelService) {
   	m_tunnelService = tunnelService;
   }

	public void setTunnelId(int tunnelId) {
   	m_tunnelId = tunnelId;
   }

	public void setType(int type) {
   	m_type = type;
   }

	public int getTunnelId() {
   	return m_tunnelId;
   }

	public int getType() {
   	return m_type;
   }

}