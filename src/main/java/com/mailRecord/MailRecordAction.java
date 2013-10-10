package com.mailRecord;

import java.util.List;

import org.apache.log4j.Logger;

import com.Authority;
import com.Modules;
import com.Operation;
import com.PagedAction;
import com.log.Log;

public class MailRecordAction extends PagedAction {

	private static final long serialVersionUID = 4901652667413788534L;

	private Logger m_logger = Logger.getLogger(MailRecordAction.class);

	private List<MailRecord> m_mailRecords;

	private int m_mailRecordId;

	private MailRecordService m_mailRecordService;

	private MailRecord m_mailRecord = new MailRecord();

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
			m_totalSize = m_mailRecordService.queryAllSize();
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_mailRecords = m_mailRecordService.queryLimitedMailRecords(start, SIZE);
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String mailRecordUpdate() {
		try {
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
}