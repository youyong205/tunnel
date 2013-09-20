package com.girthOpen;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class GirthOpenDao {

	private BaseDao m_baseDao;

	public int deleteGirthOpen(int id) {
		return m_baseDao.delete("girthOpen.delete", id);
	}

	public GirthOpen findByName(String name) {
		return (GirthOpen) m_baseDao.queryForObject("girthOpen.findByName", name);
	}

	public GirthOpen findByPK(int id) {
		return (GirthOpen) m_baseDao.queryForObject("girthOpen.findById", id);
	}

	public int insertGirthOpen(GirthOpen girthOpen) {
		return (Integer) m_baseDao.insert("girthOpen.insert", girthOpen);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllGirthOpens() {
		return m_baseDao.queryForList("girthOpen.queryAllGirthOpens");
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedGirthOpens(int tunnelId, int tunnelSectionId, int liningRingConstructionId,
	      int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("girthOpen.queryLimitedGirthOpens", parameters);
	}

	@SuppressWarnings("rawtypes")
	public List queryGirthOpenByDuration(int liningRingConstructionId, Date start, Date end) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("end", end);

		return m_baseDao.queryForList("girthOpen.queryGirthOpenByDuration", parameters);
	}

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (Integer) m_baseDao.queryForObject("girthOpen.querySizeByTunnelAndSection", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateGirthOpen(GirthOpen girthOpen) {
		return m_baseDao.update("girthOpen.update", girthOpen);
	}

	public GirthOpen queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (GirthOpen) m_baseDao.queryForObject("girthOpen.queryLastestDeformation",
		      parameters);
	}

}
