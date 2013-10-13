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

	public int updateLongitudinalDeformationState(String longitudinalDeformationState, int longitudinalDeformationId,
	      int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("longitudinalDeformationState", longitudinalDeformationState);
		parameters.put("longitudinalDeformationId", longitudinalDeformationId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		return m_baseDao.update("liningRingConstruction.updateLongitudinalDeformationState", parameters);
	}

	public int updateGirthFaultState(String girthFaultState, String girthFaultId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("girthFaultState", girthFaultState);
		parameters.put("girthFaultId", girthFaultId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		return m_baseDao.update("liningRingConstruction.updateGirthFaultState", parameters);
	}

	public int updateGirthOpenState(String girthOpenState, String girthOpenId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("girthOpenState", girthOpenState);
		parameters.put("girthOpenId", girthOpenId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		return m_baseDao.update("liningRingConstruction.updateGirthOpenState", parameters);
	}

	public int updateLongitudinalOpenState(String longitudinalOpenState, String longitudinalOpenId,
	      int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("longitudinalOpenState", longitudinalOpenState);
		parameters.put("longitudinalOpenId", longitudinalOpenId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		return m_baseDao.update("liningRingConstruction.updateLongitudinalOpenState", parameters);
	}

	public int updateLongitudinalFaultState(String longitudinalFaultState, String longitudinalFaultId,
	      int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("longitudinalFaultState", longitudinalFaultState);
		parameters.put("longitudinalFaultId", longitudinalFaultId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		return m_baseDao.update("liningRingConstruction.updateLongitudinalFaultState", parameters);
	}

	public int updateCoverLossState(String coverLossState, String coverLossId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("coverLossState", coverLossState);
		parameters.put("coverLossId", coverLossId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		return m_baseDao.update("liningRingConstruction.updateCoverLossState", parameters);
	}

	public int updateCracksState(String cracksState, String cracksId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("cracksState", cracksState);
		parameters.put("cracksId", cracksId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		return m_baseDao.update("liningRingConstruction.updateCracksState", parameters);
	}
	
	public int updateSettlementState(String settlementId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("settlementId", settlementId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		return m_baseDao.update("liningRingConstruction.updateSettlementState", parameters);
	}
	public int updateSeepageState(String seepageId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("seepageId", seepageId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		return m_baseDao.update("liningRingConstruction.updateSeepageState", parameters);
	}
	public int updateRustState(String rustId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("rustId", rustId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		return m_baseDao.update("liningRingConstruction.updateRustState", parameters);
	}


}
