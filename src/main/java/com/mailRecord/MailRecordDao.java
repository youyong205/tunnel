package com.mailRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class MailRecordDao {

	private BaseDao m_baseDao;

	public int deleteMailRecord(int id) {
		return m_baseDao.delete("mailRecord.delete", id);
	}

	public MailRecord findByNamePassword(String mailRecordName, String password) {
		Map<String, String> pars = new HashMap<String, String>();

		pars.put("mailRecordName", mailRecordName);
		pars.put("password", password);
		return (MailRecord) m_baseDao.queryForObject("mailRecord.findByName", pars);
	}

	public MailRecord findByPK(int id) {
		return (MailRecord) m_baseDao.queryForObject("mailRecord.findById", id);
	}

	public int insertMailRecord(MailRecord mailRecord) {
		return (Integer) m_baseDao.insert("mailRecord.insert", mailRecord);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllMailRecords() {
		return m_baseDao.queryForList("mailRecord.queryAllMailRecords");
	}

	public int queryAllSizeByTunnelAndType(int tunnelId, int type) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("type", type);
		return (Integer) m_baseDao.queryForObject("mailRecord.queryAllSize", parameters);
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedMailRecordsByTunnelAndType(int tunnelId, int type, int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("start", start);
		parameters.put("size", size);
		parameters.put("tunnelId", tunnelId);
		parameters.put("type", type);

		return m_baseDao.queryForList("mailRecord.queryLimitedMailRecords", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateMailRecord(MailRecord mailRecord) {
		return m_baseDao.update("mailRecord.update", mailRecord);
	}

}
