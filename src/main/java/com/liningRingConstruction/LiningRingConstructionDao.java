package com.liningRingConstruction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class LiningRingConstructionDao {

	private BaseDao m_baseDao;

	public int deleteLiningRingConstruction(int id) {
		return m_baseDao.delete("liningRingConstruction.delete", id);
	}

	public LiningRingConstruction findByName(String name) {
		return (LiningRingConstruction) m_baseDao.queryForObject("liningRingConstruction.findByName", name);
	}

	public LiningRingConstruction findByPK(int id) {
		return (LiningRingConstruction) m_baseDao.queryForObject("liningRingConstruction.findById", id);
	}

	public int insertLiningRingConstruction(LiningRingConstruction liningRingConstruction) {
		return (Integer) m_baseDao.insert("liningRingConstruction.insert", liningRingConstruction);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllLiningRingConstructions() {
		return m_baseDao.queryForList("liningRingConstruction.queryAllLiningRingConstructions");
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedLiningRingConstructions(int tunnelId, int tunnelSectionId, int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("liningRingConstruction.queryLimitedLiningRingConstructions", parameters);
	}

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);

		return (Integer) m_baseDao.queryForObject("liningRingConstruction.querySizeByTunnelAndSection", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateLiningRingConstruction(LiningRingConstruction liningRingConstruction) {
		return m_baseDao.update("liningRingConstruction.update", liningRingConstruction);
	}

	public int updateDeformationState(String deformationState, int deformationId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("deformationState", deformationState);
		parameters.put("deformationId", deformationId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		return m_baseDao.update("liningRingConstruction.updateDeformationState", parameters);
	}
	
	public int updateLongitudinalDeformationState(String longitudinalDeformationState, int longitudinalDeformationId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("longitudinalDeformationState", longitudinalDeformationState);
		parameters.put("longitudinalDeformationId", longitudinalDeformationId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		return m_baseDao.update("liningRingConstruction.updateLongitudinalDeformationState", parameters);
	}
	

}
