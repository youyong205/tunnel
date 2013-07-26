package com.workingWell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class WorkingWellDao {

	private BaseDao m_baseDao;

	public int deleteWorkingWell(int id) {
		return m_baseDao.delete("workingWell.delete", id);
	}

	public WorkingWell findByName(String name) {
		return (WorkingWell) m_baseDao.queryForObject("workingWell.findByName", name);
	}

	public WorkingWell findByPK(int id) {
		return (WorkingWell) m_baseDao.queryForObject("workingWell.findById", id);
	}

	public int insertWorkingWell(WorkingWell workingWell) {
		return (Integer) m_baseDao.insert("workingWell.insert", workingWell);
	}

	public int queryAllSize() {
		return (Integer) m_baseDao.queryForObject("workingWell.queryAllSize", null);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllWorkingWells() {
		return m_baseDao.queryForList("workingWell.queryAllWorkingWells");
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedWorkingWells(int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("workingWell.queryLimitedWorkingWells", parameters);
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedWorkingWellsByTunnelId(int tunnelId, int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("start", start);
		parameters.put("size", size);
		parameters.put("tunnelId", tunnelId);
		return m_baseDao.queryForList("workingWell.queryLimitedWorkingWellsByTunnelId", parameters);
	}

	public int querySizeByTunnelId(int tunnelId) {
		return (Integer) m_baseDao.queryForObject("workingWell.querySizeByTunnelId", tunnelId);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateWorkingWell(WorkingWell workingWell) {
		return m_baseDao.update("workingWell.update", workingWell);
	}

}
