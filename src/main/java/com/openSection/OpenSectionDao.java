package com.openSection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class OpenSectionDao {

	private BaseDao m_baseDao;

	public int deleteOpenSection(int id) {
		return m_baseDao.delete("openSection.delete", id);
	}

	public OpenSection findByName(String name) {
		return (OpenSection) m_baseDao.queryForObject("openSection.findByName", name);
	}

	public OpenSection findByPK(int id) {
		return (OpenSection) m_baseDao.queryForObject("openSection.findById", id);
	}

	public int insertOpenSection(OpenSection openSection) {
		return (Integer) m_baseDao.insert("openSection.insert", openSection);
	}

	public int queryAllSize() {
		return (Integer) m_baseDao.queryForObject("openSection.queryAllSize", null);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllOpenSections() {
		return m_baseDao.queryForList("openSection.queryAllOpenSections");
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedOpenSections(int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("openSection.queryLimitedOpenSections", parameters);
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedOpenSectionsByTunnelId(int tunnelId, int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("start", start);
		parameters.put("size", size);
		parameters.put("tunnelId", tunnelId);
		return m_baseDao.queryForList("openSection.queryLimitedOpenSectionsByTunnelId", parameters);
	}

	public int querySizeByTunnelId(int tunnelId) {
		return (Integer) m_baseDao.queryForObject("openSection.querySizeByTunnelId", tunnelId);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateOpenSection(OpenSection openSection) {
		return m_baseDao.update("openSection.update", openSection);
	}

}
