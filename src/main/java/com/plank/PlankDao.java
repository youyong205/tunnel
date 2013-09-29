package com.plank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class PlankDao {

	private BaseDao m_baseDao;

	public int deletePlank(int id) {
		return m_baseDao.delete("plank.delete", id);
	}

	public Plank findByName(String name) {
		return (Plank) m_baseDao.queryForObject("plank.findByName", name);
	}

	public Plank findByPK(int id) {
		return (Plank) m_baseDao.queryForObject("plank.findById", id);
	}

	public int insertPlank(Plank plank) {
		return (Integer) m_baseDao.insert("plank.insert", plank);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllPlanks() {
		return m_baseDao.queryForList("plank.queryAllPlanks");
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedPlanks(int tunnelId, int tunnelSectionId, int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("plank.queryLimitedPlanks", parameters);
	}

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);

		return (Integer) m_baseDao.queryForObject("plank.querySizeByTunnelAndSection", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updatePlank(Plank plank) {
		return m_baseDao.update("plank.update", plank);
	}

}
