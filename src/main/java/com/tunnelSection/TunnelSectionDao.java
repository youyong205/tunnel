package com.tunnelSection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class TunnelSectionDao {

	private BaseDao m_baseDao;

	public int deleteTunnelSection(int id) {
		return m_baseDao.delete("tunnelSection.delete", id);
	}

	public TunnelSection findByName(String name) {
		return (TunnelSection) m_baseDao.queryForObject("tunnelSection.findByName", name);
	}

	public TunnelSection findByPK(int id) {
		return (TunnelSection) m_baseDao.queryForObject("tunnelSection.findById", id);
	}

	public int insertTunnelSection(TunnelSection tunnelSection) {
		return (Integer) m_baseDao.insert("tunnelSection.insert", tunnelSection);
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedTunnelSectionsByTunnelId(int tunnelId, int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("start", start);
		parameters.put("size", size);
		parameters.put("tunnelId", tunnelId);
		return m_baseDao.queryForList("tunnelSection.queryLimitedTunnelSectionsByTunnelId", parameters);
	}

	public int querySizeByTunnelId(int tunnelId) {
		return (Integer) m_baseDao.queryForObject("tunnelSection.querySizeByTunnelId", tunnelId);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateTunnelSection(TunnelSection tunnelSection) {
		return m_baseDao.update("tunnelSection.update", tunnelSection);
	}

}
