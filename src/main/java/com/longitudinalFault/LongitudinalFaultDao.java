package com.longitudinalFault;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class LongitudinalFaultDao {

	private BaseDao m_baseDao;

	public int deleteLongitudinalFault(int id) {
		return m_baseDao.delete("longitudinalFault.delete", id);
	}

	public LongitudinalFault findByName(String name) {
		return (LongitudinalFault) m_baseDao.queryForObject("longitudinalFault.findByName", name);
	}

	public LongitudinalFault findByPK(int id) {
		return (LongitudinalFault) m_baseDao.queryForObject("longitudinalFault.findById", id);
	}

	public int insertLongitudinalFault(LongitudinalFault longitudinalFault) {
		return (Integer) m_baseDao.insert("longitudinalFault.insert", longitudinalFault);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllLongitudinalFaults() {
		return m_baseDao.queryForList("longitudinalFault.queryAllLongitudinalFaults");
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedLongitudinalFaults(int tunnelId, int tunnelSectionId, int liningRingConstructionId,
	      int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("longitudinalFault.queryLimitedLongitudinalFaults", parameters);
	}

	@SuppressWarnings("rawtypes")
	public List queryLongitudinalFaultByDuration(int liningRingConstructionId, Date start, Date end) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("end", end);

		return m_baseDao.queryForList("longitudinalFault.queryLongitudinalFaultByDuration", parameters);
	}

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (Integer) m_baseDao.queryForObject("longitudinalFault.querySizeByTunnelAndSection", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateLongitudinalFault(LongitudinalFault longitudinalFault) {
		return m_baseDao.update("longitudinalFault.update", longitudinalFault);
	}

	public LongitudinalFault queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (LongitudinalFault) m_baseDao.queryForObject("longitudinalFault.queryLastestDeformation",
		      parameters);
	}

}
