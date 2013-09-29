package com.longitudinalOpen;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class LongitudinalOpenDao {

	private BaseDao m_baseDao;

	public int deleteLongitudinalOpen(int id) {
		return m_baseDao.delete("longitudinalOpen.delete", id);
	}

	public LongitudinalOpen findByName(String name) {
		return (LongitudinalOpen) m_baseDao.queryForObject("longitudinalOpen.findByName", name);
	}

	public LongitudinalOpen findByPK(int id) {
		return (LongitudinalOpen) m_baseDao.queryForObject("longitudinalOpen.findById", id);
	}

	public int insertLongitudinalOpen(LongitudinalOpen longitudinalOpen) {
		return (Integer) m_baseDao.insert("longitudinalOpen.insert", longitudinalOpen);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllLongitudinalOpens() {
		return m_baseDao.queryForList("longitudinalOpen.queryAllLongitudinalOpens");
	}

	public LongitudinalOpen queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (LongitudinalOpen) m_baseDao.queryForObject("longitudinalOpen.queryLastestDeformation",
		      parameters);
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedLongitudinalOpens(int tunnelId, int tunnelSectionId, int liningRingConstructionId,
	      int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("longitudinalOpen.queryLimitedLongitudinalOpens", parameters);
	}

	@SuppressWarnings("rawtypes")
	public List queryLongitudinalOpenByDuration(int liningRingConstructionId, Date start, Date end) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("end", end);

		return m_baseDao.queryForList("longitudinalOpen.queryLongitudinalOpenByDuration", parameters);
	}

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (Integer) m_baseDao.queryForObject("longitudinalOpen.querySizeByTunnelAndSection", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateLongitudinalOpen(LongitudinalOpen longitudinalOpen) {
		return m_baseDao.update("longitudinalOpen.update", longitudinalOpen);
	}

}
