package com.mailRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class MailRecordServiceImpl implements MailRecordService {

	private MailRecordDao m_mailRecordDao;

	private Logger m_logger = Logger.getLogger(MailRecordServiceImpl.class);

	@Override
	public int deleteMailRecord(int id) {
		try {
			return m_mailRecordDao.deleteMailRecord(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public MailRecord findByPK(int id) {
		try {
			return m_mailRecordDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public int insertMailRecord(MailRecord mailRecord) {
		try {
			return m_mailRecordDao.insertMailRecord(mailRecord);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MailRecord> queryAllMailRecords() {
		try {
			return m_mailRecordDao.queryAllMailRecords();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<MailRecord>();
		}
	}

	@Override
	public int queryAllSizeByTunnelAndType(int tunnelId, int type) {
		try {
			return m_mailRecordDao.queryAllSizeByTunnelAndType(tunnelId, type);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MailRecord> queryLimitedMailRecordsByTunnelAndType(int tunnelId, int type, int start, int size) {
		try {
			return m_mailRecordDao.queryLimitedMailRecordsByTunnelAndType(tunnelId, type, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<MailRecord>();
		}
	}

	public void setMailRecordDao(MailRecordDao mailRecordDao) {
		m_mailRecordDao = mailRecordDao;
	}

	@Override
	public int updateMailRecord(MailRecord mailRecord) {
		try {
			return m_mailRecordDao.updateMailRecord(mailRecord);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public MailRecord findDailyRecordByTime(int tunnelId, Date date) {
		try {
			return m_mailRecordDao.findDailyRecordByTime(tunnelId, date);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}
}
