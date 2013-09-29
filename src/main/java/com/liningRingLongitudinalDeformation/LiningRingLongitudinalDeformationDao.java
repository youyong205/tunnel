package com.liningRingLongitudinalDeformation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class LiningRingLongitudinalDeformationDao {

	private BaseDao m_baseDao;

	public int deleteLiningRingLongitudinalDeformation(int id) {
		return m_baseDao.delete("liningRingLongitudinalDeformation.delete", id);
	}

	public LiningRingLongitudinalDeformation findByName(String name) {
		return (LiningRingLongitudinalDeformation) m_baseDao.queryForObject("liningRingLongitudinalDeformation.findByName", name);
	}

	public LiningRingLongitudinalDeformation findByPK(int id) {
		return (LiningRingLongitudinalDeformation) m_baseDao.queryForObject("liningRingLongitudinalDeformation.findById", id);
	}

	public int insertLiningRingLongitudinalDeformation(LiningRingLongitudinalDeformation liningRingLongitudinalDeformation) {
		return (Integer) m_baseDao.insert("liningRingLongitudinalDeformation.insert", liningRingLongitudinalDeformation);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllLiningRingLongitudinalDeformations() {
		return m_baseDao.queryForList("liningRingLongitudinalDeformation.queryAllLiningRingLongitudinalDeformations");
	}

	public LiningRingLongitudinalDeformation queryLastestLongitudinalDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (LiningRingLongitudinalDeformation) m_baseDao.queryForObject("liningRingLongitudinalDeformation.queryLastestLongitudinalDeformation",
		      parameters);
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedLiningRingLongitudinalDeformations(int tunnelId, int tunnelSectionId, int liningRingConstructionId,
	      int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("liningRingLongitudinalDeformation.queryLimitedLiningRingLongitudinalDeformations", parameters);
	}

	@SuppressWarnings("rawtypes")
	public List queryLiningRingLongitudinalDeformationByDuration(int liningRingConstructionId, Date start, Date end) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("end", end);

		return m_baseDao.queryForList("liningRingLongitudinalDeformation.queryLiningRingLongitudinalDeformationByDuration", parameters);
	}

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (Integer) m_baseDao.queryForObject("liningRingLongitudinalDeformation.querySizeByTunnelAndSection", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateLiningRingLongitudinalDeformation(LiningRingLongitudinalDeformation liningRingLongitudinalDeformation) {
		return m_baseDao.update("liningRingLongitudinalDeformation.update", liningRingLongitudinalDeformation);
	}

}
