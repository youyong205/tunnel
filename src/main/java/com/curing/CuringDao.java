package com.curing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.BaseDao;

public class CuringDao {

	@Autowired
	private BaseDao m_baseDao;

	public int deleteCuring(int id) {
		return m_baseDao.delete("curing.delete", id);
	}

	public Curing findByPK(int id) {
		return (Curing) m_baseDao.queryForObject("curing.findById", id);
	}

	public int insertCuring(Curing curing) {
		return (Integer) m_baseDao.insert("curing.insert", curing);
	}

	public int queryCuringSizeByType(int tunnelId,String type) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("type", type);
		parameters.put("tunnelId",tunnelId);
		
		return (Integer)m_baseDao.queryForObject("curing.queryCuringSizeByType",parameters);
   }

	@SuppressWarnings("rawtypes")
   public List queryLimitedCuringsByType(int tunnelId,String type, int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("start", start);
		parameters.put("size", size);
		parameters.put("type", type);
		parameters.put("tunnelId",tunnelId);

		return m_baseDao.queryForList("curing.queryLimitedCuringsByType", parameters);
   }

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateCuring(Curing curing) {
		return m_baseDao.update("curing.update", curing);
	}

}
