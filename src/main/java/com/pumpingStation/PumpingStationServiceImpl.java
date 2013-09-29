package com.pumpingStation;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class PumpingStationServiceImpl implements PumpingStationService {

	private PumpingStationDao m_pumpingStationDao;

	private Logger m_logger = Logger.getLogger(PumpingStationServiceImpl.class);

	@Override
	public int deletePumpingStation(int id) {
		try {
			int result = m_pumpingStationDao.deletePumpingStation(id);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public PumpingStation findByName(String name) {
		try {
			return m_pumpingStationDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public PumpingStation findByPK(int id) {
		try {
			return m_pumpingStationDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertPumpingStation(PumpingStation pumpingStation) {
		try {
			int result = m_pumpingStationDao.insertPumpingStation(pumpingStation);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PumpingStation> queryLimitedPumpingStations(int tunnelId, int tunnelSectionId, int start, int size) {
		try {
			return m_pumpingStationDao.queryLimitedPumpingStations(tunnelId, tunnelSectionId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<PumpingStation>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId) {
		try {
			return m_pumpingStationDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setPumpingStationDao(PumpingStationDao pumpingStationDao) {
		m_pumpingStationDao = pumpingStationDao;
	}

	@Override
	public int updatePumpingStation(PumpingStation pumpingStation) {
		try {
			int result = m_pumpingStationDao.updatePumpingStation(pumpingStation);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
