package com.pumpingStation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class PumpingStationDao {

	private BaseDao m_baseDao;

	public int deletePumpingStation(int id) {
		return m_baseDao.delete("pumpingStation.delete", id);
	}

	public PumpingStation findByName(String name) {
		return (PumpingStation) m_baseDao.queryForObject("pumpingStation.findByName", name);
	}

	public PumpingStation findByPK(int id) {
		return (PumpingStation) m_baseDao.queryForObject("pumpingStation.findById", id);
	}

	public int insertPumpingStation(PumpingStation pumpingStation) {
		return (Integer) m_baseDao.insert("pumpingStation.insert", pumpingStation);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllPumpingStations() {
		return m_baseDao.queryForList("pumpingStation.queryAllPumpingStations");
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedPumpingStations(int tunnelId,int tunnelSectionId,int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("pumpingStation.queryLimitedPumpingStations", parameters);
	}

	public int querySizeByTunnelAndSection(int tunnelId,int tunnelSectionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);

		return (Integer)m_baseDao.queryForObject("pumpingStation.querySizeByTunnelAndSection",parameters);
   }

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updatePumpingStation(PumpingStation pumpingStation) {
		return m_baseDao.update("pumpingStation.update", pumpingStation);
	}

}
