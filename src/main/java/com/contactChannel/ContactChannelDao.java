package com.contactChannel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class ContactChannelDao {

	private BaseDao m_baseDao;

	public int deleteContactChannel(int id) {
		return m_baseDao.delete("contactChannel.delete", id);
	}

	public ContactChannel findByName(String name) {
		return (ContactChannel) m_baseDao.queryForObject("contactChannel.findByName", name);
	}

	public ContactChannel findByPK(int id) {
		return (ContactChannel) m_baseDao.queryForObject("contactChannel.findById", id);
	}

	public int insertContactChannel(ContactChannel contactChannel) {
		return (Integer) m_baseDao.insert("contactChannel.insert", contactChannel);
	}

	public int queryAllSize() {
		return (Integer) m_baseDao.queryForObject("contactChannel.queryAllSize", null);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllContactChannels() {
		return m_baseDao.queryForList("contactChannel.queryAllContactChannels");
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedContactChannels(int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("contactChannel.queryLimitedContactChannels", parameters);
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedContactChannelsByTunnelId(int tunnelId, int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("start", start);
		parameters.put("size", size);
		parameters.put("tunnelId", tunnelId);
		return m_baseDao.queryForList("contactChannel.queryLimitedContactChannelsByTunnelId", parameters);
	}

	public int querySizeByTunnelId(int tunnelId) {
		return (Integer) m_baseDao.queryForObject("contactChannel.querySizeByTunnelId", tunnelId);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateContactChannel(ContactChannel contactChannel) {
		return m_baseDao.update("contactChannel.update", contactChannel);
	}

}
