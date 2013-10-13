package com.girthFault;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class GirthFaultDao {

	private BaseDao m_baseDao;

	public int deleteGirthFault(int id) {
		return m_baseDao.delete("girthFault.delete", id);
	}

	public GirthFault findByName(String name) {
		return (GirthFault) m_baseDao.queryForObject("girthFault.findByName", name);
	}

	public GirthFault findByPK(int id) {
		return (GirthFault) m_baseDao.queryForObject("girthFault.findById", id);
	}

	public int insertGirthFault(GirthFault girthFault) {
		return (Integer) m_baseDao.insert("girthFault.insert", girthFault);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllGirthFaults() {
		return m_baseDao.queryForList("girthFault.queryAllGirthFaults");
	}

	@SuppressWarnings("rawtypes")
	public List queryGirthFaultByDuration(int liningRingConstructionId, Date start, Date end) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("end", end);

		return m_baseDao.queryForList("girthFault.queryGirthFaultByDuration", parameters);
	}

	public GirthFault queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (GirthFault) m_baseDao.queryForObject("girthFault.queryLastestDeformation", parameters);
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedGirthFaults(int tunnelId, int tunnelSectionId, int liningRingConstructionId, int start,
	      int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("girthFault.queryLimitedGirthFaults", parameters);
	}

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (Integer) m_baseDao.queryForObject("girthFault.querySizeByTunnelAndSection", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateGirthFault(GirthFault girthFault) {
		return m_baseDao.update("girthFault.update", girthFault);
	}
	
	@SuppressWarnings("rawtypes")
   public List queryByIds(List<Integer> ids) {
		if(ids.size()>0){
			return m_baseDao.queryForList("girthFault.queryByIds", ids);
		}else{
			return new ArrayList();
		}
   }

}
