package com.liningRingLongitudinalDeformation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.liningRingConstruction.LiningRingConstructionService;

public class LiningRingLongitudinalDeformationServiceImpl implements LiningRingLongitudinalDeformationService {

	private LiningRingLongitudinalDeformationDao m_liningRingLongitudinalDeformationDao;

	private LiningRingConstructionService m_liningRingConstructionService;

	private Logger m_logger = Logger.getLogger(LiningRingLongitudinalDeformationServiceImpl.class);

	@Override
	public int deleteLiningRingLongitudinalDeformation(int id) {
		try {
			int result = m_liningRingLongitudinalDeformationDao.deleteLiningRingLongitudinalDeformation(id);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public LiningRingLongitudinalDeformation findByName(String name) {
		try {
			return m_liningRingLongitudinalDeformationDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public LiningRingLongitudinalDeformation findByPK(int id) {
		try {
			return m_liningRingLongitudinalDeformationDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertLiningRingLongitudinalDeformation(
	      LiningRingLongitudinalDeformation liningRingLongitudinalDeformation) {
		try {
			int result = m_liningRingLongitudinalDeformationDao
			      .insertLiningRingLongitudinalDeformation(liningRingLongitudinalDeformation);

			m_liningRingConstructionService.updateLongitudinalDeformationState(liningRingLongitudinalDeformation);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LiningRingLongitudinalDeformation> queryLimitedLiningRingLongitudinalDeformations(int tunnelId,
	      int tunnelSectionId, int liningRingConstructionId, int start, int size) {
		try {
			return m_liningRingLongitudinalDeformationDao.queryLimitedLiningRingLongitudinalDeformations(tunnelId,
			      tunnelSectionId, liningRingConstructionId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<LiningRingLongitudinalDeformation>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LiningRingLongitudinalDeformation> queryLiningRingLongitudinalDeformationByDuration(
	      int liningRingLongitudinalDeformationId, Date start, Date end) {
		try {
			return m_liningRingLongitudinalDeformationDao.queryLiningRingLongitudinalDeformationByDuration(
			      liningRingLongitudinalDeformationId, start, end);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<LiningRingLongitudinalDeformation>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		try {
			return m_liningRingLongitudinalDeformationDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId,
			      liningRingConstructionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setLiningRingLongitudinalDeformationDao(
	      LiningRingLongitudinalDeformationDao liningRingLongitudinalDeformationDao) {
		m_liningRingLongitudinalDeformationDao = liningRingLongitudinalDeformationDao;
	}

	@Override
	public int updateLiningRingLongitudinalDeformation(
	      LiningRingLongitudinalDeformation liningRingLongitudinalDeformation) {
		try {
			int result = m_liningRingLongitudinalDeformationDao
			      .updateLiningRingLongitudinalDeformation(liningRingLongitudinalDeformation);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public LiningRingLongitudinalDeformation queryLastestLongitudinalDeformation(int tunnelId, int tunnelSectionId,
	      int liningRingConstructionId) {
		try {
			return m_liningRingLongitudinalDeformationDao.queryLastestLongitudinalDeformation(tunnelId, tunnelSectionId,
			      liningRingConstructionId);

		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	public void setLiningRingConstructionService(LiningRingConstructionService liningRingConstructionService) {
   	m_liningRingConstructionService = liningRingConstructionService;
   }
	
}
