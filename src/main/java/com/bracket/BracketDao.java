package com.bracket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class BracketDao {

	private BaseDao m_baseDao;

	public int deleteBracket(int id) {
		return m_baseDao.delete("bracket.delete", id);
	}

	public Bracket findByName(String name) {
		return (Bracket) m_baseDao.queryForObject("bracket.findByName", name);
	}

	public Bracket findByPK(int id) {
		return (Bracket) m_baseDao.queryForObject("bracket.findById", id);
	}

	public int insertBracket(Bracket bracket) {
		return (Integer) m_baseDao.insert("bracket.insert", bracket);
	}

	public int querySizeByTunnelAndSection(int tunnelId,int tunnelSectionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);

		return (Integer)m_baseDao.queryForObject("bracket.querySizeByTunnelAndSection",parameters);
   }

	@SuppressWarnings("rawtypes")
	public List queryAllBrackets() {
		return m_baseDao.queryForList("bracket.queryAllBrackets");
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedBrackets(int tunnelId,int tunnelSectionId,int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("bracket.queryLimitedBrackets", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateBracket(Bracket bracket) {
		return m_baseDao.update("bracket.update", bracket);
	}

}
