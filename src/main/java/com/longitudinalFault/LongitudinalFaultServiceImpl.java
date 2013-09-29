package com.longitudinalFault;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.liningRingConstruction.LiningRingConstructionService;

public class LongitudinalFaultServiceImpl implements LongitudinalFaultService {

	private LongitudinalFaultDao m_longitudinalFaultDao;

	private LiningRingConstructionService m_liningRingConstructionService;
	
	private Logger m_logger = Logger.getLogger(LongitudinalFaultServiceImpl.class);

	@Override
	public int deleteLongitudinalFault(int id) {
		try {
			int result = m_longitudinalFaultDao.deleteLongitudinalFault(id);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public LongitudinalFault findByName(String name) {
		try {
			return m_longitudinalFaultDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public LongitudinalFault findByPK(int id) {
		try {
			return m_longitudinalFaultDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertLongitudinalFault(LongitudinalFault longitudinalFault) {
		try {
			int result = m_longitudinalFaultDao.insertLongitudinalFault(longitudinalFault);

			m_liningRingConstructionService.updateLongitudinalFaultState(longitudinalFault);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public LongitudinalFault queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		try {
			return m_longitudinalFaultDao.queryLastestDeformation(tunnelId, tunnelSectionId, liningRingConstructionId);

		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LongitudinalFault> queryLimitedLongitudinalFaults(int tunnelId, int tunnelSectionId,
	      int liningRingConstructionId, int start, int size) {
		try {
			return m_longitudinalFaultDao.queryLimitedLongitudinalFaults(tunnelId, tunnelSectionId,
			      liningRingConstructionId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<LongitudinalFault>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LongitudinalFault> queryLongitudinalFaultByDuration(int longitudinalFaultId, Date start,
	      Date end) {
		try {
			return m_longitudinalFaultDao.queryLongitudinalFaultByDuration(longitudinalFaultId, start, end);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<LongitudinalFault>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		try {
			return m_longitudinalFaultDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId, liningRingConstructionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setLiningRingConstructionService(LiningRingConstructionService liningRingConstructionService) {
   	m_liningRingConstructionService = liningRingConstructionService;
   }

	public void setLongitudinalFaultDao(LongitudinalFaultDao longitudinalFaultDao) {
		m_longitudinalFaultDao = longitudinalFaultDao;
	}

	@Override
	public int updateLongitudinalFault(LongitudinalFault longitudinalFault) {
		try {
			int result = m_longitudinalFaultDao.updateLongitudinalFault(longitudinalFault);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}
	
}
