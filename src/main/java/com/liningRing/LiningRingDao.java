package com.liningRing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class LiningRingDao {

	private BaseDao m_baseDao;

	public int deleteLiningRing(int id) {
		return m_baseDao.delete("liningRing.delete", id);
	}

	public LiningRing findByName(String name) {
		return (LiningRing) m_baseDao.queryForObject("liningRing.findByName", name);
	}

	public LiningRing findByPK(int id) {
		return (LiningRing) m_baseDao.queryForObject("liningRing.findById", id);
	}

	public int insertLiningRing(LiningRing liningRing) {
		return (Integer) m_baseDao.insert("liningRing.insert", liningRing);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllLiningRings() {
		return m_baseDao.queryForList("liningRing.queryAllLiningRings");
	}

	public int queryAllSize() {
		return (Integer)m_baseDao.queryForObject("liningRing.queryAllSize",null);
   }

	@SuppressWarnings("rawtypes")
	public List queryLimitedLiningRings(int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("liningRing.queryLimitedLiningRings", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateLiningRing(LiningRing liningRing) {
		return m_baseDao.update("liningRing.update", liningRing);
	}

}
