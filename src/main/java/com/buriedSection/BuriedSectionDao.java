package com.buriedSection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class BuriedSectionDao {

	private BaseDao m_baseDao;

	public int deleteBuriedSection(int id) {
		return m_baseDao.delete("buriedSection.delete", id);
	}

	public BuriedSection findByName(String name) {
		return (BuriedSection) m_baseDao.queryForObject("buriedSection.findByName", name);
	}

	public BuriedSection findByPK(int id) {
		return (BuriedSection) m_baseDao.queryForObject("buriedSection.findById", id);
	}

	public int insertBuriedSection(BuriedSection buriedSection) {
		return (Integer) m_baseDao.insert("buriedSection.insert", buriedSection);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllBuriedSections() {
		return m_baseDao.queryForList("buriedSection.queryAllBuriedSections");
	}

	public int queryAllSize() {
		return (Integer) m_baseDao.queryForObject("buriedSection.queryAllSize", null);
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedBuriedSections(int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("buriedSection.queryLimitedBuriedSections", parameters);
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedBuriedSectionsByTunnelId(int tunnelId, int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("start", start);
		parameters.put("size", size);
		parameters.put("tunnelId", tunnelId);
		return m_baseDao.queryForList("buriedSection.queryLimitedBuriedSectionsByTunnelId", parameters);
	}

	public int querySizeByTunnelId(int tunnelId) {
		return (Integer) m_baseDao.queryForObject("buriedSection.querySizeByTunnelId", tunnelId);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateBuriedSection(BuriedSection buriedSection) {
		return m_baseDao.update("buriedSection.update", buriedSection);
	}

}
