package com.escape;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class EscapeDao {

	private BaseDao m_baseDao;

	public int deleteEscape(int id) {
		return m_baseDao.delete("escape.delete", id);
	}

	public Escape findByName(String name) {
		return (Escape) m_baseDao.queryForObject("escape.findByName", name);
	}

	public Escape findByPK(int id) {
		return (Escape) m_baseDao.queryForObject("escape.findById", id);
	}

	public int insertEscape(Escape escape) {
		return (Integer) m_baseDao.insert("escape.insert", escape);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllEscapes() {
		return m_baseDao.queryForList("escape.queryAllEscapes");
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedEscapes(int tunnelId,int tunnelSectionId,int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("escape.queryLimitedEscapes", parameters);
	}

	public int querySizeByTunnelAndSection(int tunnelId,int tunnelSectionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);

		return (Integer)m_baseDao.queryForObject("escape.querySizeByTunnelAndSection",parameters);
   }

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateEscape(Escape escape) {
		return m_baseDao.update("escape.update", escape);
	}

}
