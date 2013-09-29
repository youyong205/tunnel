package com.longitudinalOpen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.liningRingConstruction.LiningRingConstructionService;

public class LongitudinalOpenServiceImpl implements LongitudinalOpenService {

	private LongitudinalOpenDao m_longitudinalOpenDao;

	private LiningRingConstructionService m_liningRingConstructionService;
	
	private Logger m_logger = Logger.getLogger(LongitudinalOpenServiceImpl.class);

	@Override
	public int deleteLongitudinalOpen(int id) {
		try {
			int result = m_longitudinalOpenDao.deleteLongitudinalOpen(id);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public LongitudinalOpen findByName(String name) {
		try {
			return m_longitudinalOpenDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public LongitudinalOpen findByPK(int id) {
		try {
			return m_longitudinalOpenDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertLongitudinalOpen(LongitudinalOpen longitudinalOpen) {
		try {
			int result = m_longitudinalOpenDao.insertLongitudinalOpen(longitudinalOpen);

			m_liningRingConstructionService.updateLongitudinalOpenState(longitudinalOpen);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public LongitudinalOpen queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		try {
			return m_longitudinalOpenDao.queryLastestDeformation(tunnelId, tunnelSectionId, liningRingConstructionId);

		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LongitudinalOpen> queryLimitedLongitudinalOpens(int tunnelId, int tunnelSectionId,
	      int liningRingConstructionId, int start, int size) {
		try {
			return m_longitudinalOpenDao.queryLimitedLongitudinalOpens(tunnelId, tunnelSectionId,
			      liningRingConstructionId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<LongitudinalOpen>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LongitudinalOpen> queryLongitudinalOpenByDuration(int longitudinalOpenId, Date start,
	      Date end) {
		try {
			return m_longitudinalOpenDao.queryLongitudinalOpenByDuration(longitudinalOpenId, start, end);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<LongitudinalOpen>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		try {
			return m_longitudinalOpenDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId, liningRingConstructionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setLiningRingConstructionService(LiningRingConstructionService liningRingConstructionService) {
   	m_liningRingConstructionService = liningRingConstructionService;
   }

	public void setLongitudinalOpenDao(LongitudinalOpenDao longitudinalOpenDao) {
		m_longitudinalOpenDao = longitudinalOpenDao;
	}

	@Override
	public int updateLongitudinalOpen(LongitudinalOpen longitudinalOpen) {
		try {
			int result = m_longitudinalOpenDao.updateLongitudinalOpen(longitudinalOpen);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}
	
}
