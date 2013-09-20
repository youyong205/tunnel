package com.liningRingDeformation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class LiningRingDeformationDao {

	private BaseDao m_baseDao;

	public int deleteLiningRingDeformation(int id) {
		return m_baseDao.delete("liningRingDeformation.delete", id);
	}

	public LiningRingDeformation findByName(String name) {
		return (LiningRingDeformation) m_baseDao.queryForObject("liningRingDeformation.findByName", name);
	}

	public LiningRingDeformation findByPK(int id) {
		return (LiningRingDeformation) m_baseDao.queryForObject("liningRingDeformation.findById", id);
	}

	public int insertLiningRingDeformation(LiningRingDeformation liningRingDeformation) {
		return (Integer) m_baseDao.insert("liningRingDeformation.insert", liningRingDeformation);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllLiningRingDeformations() {
		return m_baseDao.queryForList("liningRingDeformation.queryAllLiningRingDeformations");
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedLiningRingDeformations(int tunnelId, int tunnelSectionId, int liningRingConstructionId,
	      int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("liningRingDeformation.queryLimitedLiningRingDeformations", parameters);
	}

	@SuppressWarnings("rawtypes")
	public List queryLiningRingDeformationByDuration(int liningRingConstructionId, Date start, Date end) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("end", end);

		return m_baseDao.queryForList("liningRingDeformation.queryLiningRingDeformationByDuration", parameters);
	}

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (Integer) m_baseDao.queryForObject("liningRingDeformation.querySizeByTunnelAndSection", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateLiningRingDeformation(LiningRingDeformation liningRingDeformation) {
		return m_baseDao.update("liningRingDeformation.update", liningRingDeformation);
	}

	public LiningRingDeformation queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (LiningRingDeformation) m_baseDao.queryForObject("liningRingDeformation.queryLastestDeformation",
		      parameters);
	}

}
