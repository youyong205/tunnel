package com.facility;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class FacilityServiceImpl implements FacilityService {

	private FacilityDao m_facilityDao;

	private Logger m_logger = Logger.getLogger(FacilityServiceImpl.class);

	@Override
	public int deleteFacility(int id) {
		try {
			int result = m_facilityDao.deleteFacility(id);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public Facility findByName(String name) {
		try {
			return m_facilityDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public Facility findByPK(int id) {
		try {
			return m_facilityDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertFacility(Facility facility) {
		try {
			int result = m_facilityDao.insertFacility(facility);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Facility> queryLimitedFacilitys(int tunnelId, int tunnelSectionId, int start, int size) {
		try {
			return m_facilityDao.queryLimitedFacilitys(tunnelId, tunnelSectionId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Facility>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId) {
		try {
			return m_facilityDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setFacilityDao(FacilityDao facilityDao) {
		m_facilityDao = facilityDao;
	}

	@Override
	public int updateFacility(Facility facility) {
		try {
			int result = m_facilityDao.updateFacility(facility);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
