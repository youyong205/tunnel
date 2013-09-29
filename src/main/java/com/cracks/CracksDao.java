package com.cracks;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class CracksDao {

	private BaseDao m_baseDao;

	public int deleteCracks(int id) {
		return m_baseDao.delete("cracks.delete", id);
	}

	public Cracks findByName(String name) {
		return (Cracks) m_baseDao.queryForObject("cracks.findByName", name);
	}

	public Cracks findByPK(int id) {
		return (Cracks) m_baseDao.queryForObject("cracks.findById", id);
	}

	public int insertCracks(Cracks cracks) {
		return (Integer) m_baseDao.insert("cracks.insert", cracks);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllCrackss() {
		return m_baseDao.queryForList("cracks.queryAllCrackss");
	}

	@SuppressWarnings("rawtypes")
	public List queryCracksByDuration(int liningRingConstructionId, Date start, Date end) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("end", end);

		return m_baseDao.queryForList("cracks.queryCracksByDuration", parameters);
	}

	public Cracks queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (Cracks) m_baseDao.queryForObject("cracks.queryLastestDeformation", parameters);
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedCrackss(int tunnelId, int tunnelSectionId, int liningRingConstructionId, int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("cracks.queryLimitedCrackss", parameters);
	}

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (Integer) m_baseDao.queryForObject("cracks.querySizeByTunnelAndSection", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateCracks(Cracks cracks) {
		return m_baseDao.update("cracks.update", cracks);
	}

}
