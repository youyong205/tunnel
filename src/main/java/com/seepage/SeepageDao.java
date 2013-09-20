package com.seepage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class SeepageDao {

	private BaseDao m_baseDao;

	public int deleteSeepage(int id) {
		return m_baseDao.delete("seepage.delete", id);
	}

	public Seepage findByName(String name) {
		return (Seepage) m_baseDao.queryForObject("seepage.findByName", name);
	}

	public Seepage findByPK(int id) {
		return (Seepage) m_baseDao.queryForObject("seepage.findById", id);
	}

	public int insertSeepage(Seepage seepage) {
		return (Integer) m_baseDao.insert("seepage.insert", seepage);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllSeepages() {
		return m_baseDao.queryForList("seepage.queryAllSeepages");
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedSeepages(int tunnelId, int tunnelSectionId, int liningRingConstructionId,
	      int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("seepage.queryLimitedSeepages", parameters);
	}

	@SuppressWarnings("rawtypes")
	public List querySeepageByDuration(int liningRingConstructionId, Date start, Date end) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("end", end);

		return m_baseDao.queryForList("seepage.querySeepageByDuration", parameters);
	}

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (Integer) m_baseDao.queryForObject("seepage.querySizeByTunnelAndSection", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateSeepage(Seepage seepage) {
		return m_baseDao.update("seepage.update", seepage);
	}

	public Seepage queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (Seepage) m_baseDao.queryForObject("seepage.queryLastestDeformation",
		      parameters);
	}

}
