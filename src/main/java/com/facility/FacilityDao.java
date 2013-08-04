package com.facility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class FacilityDao {

	private BaseDao m_baseDao;

	public int deleteFacility(int id) {
		return m_baseDao.delete("facility.delete", id);
	}

	public Facility findByName(String name) {
		return (Facility) m_baseDao.queryForObject("facility.findByName", name);
	}

	public Facility findByPK(int id) {
		return (Facility) m_baseDao.queryForObject("facility.findById", id);
	}

	public int insertFacility(Facility facility) {
		return (Integer) m_baseDao.insert("facility.insert", facility);
	}

	public int querySizeByTunnelAndSection(int tunnelId,int tunnelSectionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);

		return (Integer)m_baseDao.queryForObject("facility.querySizeByTunnelAndSection",parameters);
   }

	@SuppressWarnings("rawtypes")
	public List queryAllFacilitys() {
		return m_baseDao.queryForList("facility.queryAllFacilitys");
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedFacilitys(int tunnelId,int tunnelSectionId,int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("facility.queryLimitedFacilitys", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateFacility(Facility facility) {
		return m_baseDao.update("facility.update", facility);
	}

}
