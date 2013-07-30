package com.constructionUnit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class ConstructionUnitDao {

	private BaseDao m_baseDao;

	public int deleteConstructionUnit(int id) {
		return m_baseDao.delete("constructionUnit.delete", id);
	}

	public ConstructionUnit findByName(String name) {
		return (ConstructionUnit) m_baseDao.queryForObject("constructionUnit.findByName", name);
	}

	public ConstructionUnit findByPK(int id) {
		return (ConstructionUnit) m_baseDao.queryForObject("constructionUnit.findById", id);
	}

	public int insertConstructionUnit(ConstructionUnit constructionUnit) {
		return (Integer) m_baseDao.insert("constructionUnit.insert", constructionUnit);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllConstructionUnits() {
		return m_baseDao.queryForList("constructionUnit.queryAllConstructionUnits");
	}

	public int queryAllSize() {
		return (Integer)m_baseDao.queryForObject("constructionUnit.queryAllSize",null);
   }

	@SuppressWarnings("rawtypes")
	public List queryLimitedConstructionUnits(int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("constructionUnit.queryLimitedConstructionUnits", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateConstructionUnit(ConstructionUnit constructionUnit) {
		return m_baseDao.update("constructionUnit.update", constructionUnit);
	}

}
