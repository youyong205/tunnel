package com.inspection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.BaseDao;

public class InspectionDao {

	@Autowired
	private BaseDao m_baseDao;

	public int deleteInspection(int id) {
		return m_baseDao.delete("inspection.delete", id);
	}

	public Inspection findByPK(int id) {
		return (Inspection) m_baseDao.queryForObject("inspection.findById", id);
	}

	public int insertInspection(Inspection inspection) {
		return (Integer) m_baseDao.insert("inspection.insert", inspection);
	}

	public int queryInspectionSizeByType(int tunnelId, int tunnelSectionId, String type) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("type", type);
		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId",tunnelSectionId);

		return (Integer) m_baseDao.queryForObject("inspection.queryInspectionSizeByType", parameters);
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedInspectionsByType(int tunnelId, int tunnelSectionId, String type, int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("start", start);
		parameters.put("size", size);
		parameters.put("type", type);
		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId",tunnelSectionId);

		return m_baseDao.queryForList("inspection.queryLimitedInspectionsByType", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateInspection(Inspection inspection) {
		return m_baseDao.update("inspection.update", inspection);
	}

}
