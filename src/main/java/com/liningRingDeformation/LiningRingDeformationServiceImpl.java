package com.liningRingDeformation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.liningRingConstruction.LiningRingConstructionService;

public class LiningRingDeformationServiceImpl implements LiningRingDeformationService {

	private LiningRingDeformationDao m_liningRingDeformationDao;

	private LiningRingConstructionService m_liningRingConstructionService;
	
	private Logger m_logger = Logger.getLogger(LiningRingDeformationServiceImpl.class);

	@Override
	public int deleteLiningRingDeformation(int id) {
		try {
			int result = m_liningRingDeformationDao.deleteLiningRingDeformation(id);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public LiningRingDeformation findByName(String name) {
		try {
			return m_liningRingDeformationDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public LiningRingDeformation findByPK(int id) {
		try {
			return m_liningRingDeformationDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertLiningRingDeformation(LiningRingDeformation liningRingDeformation) {
		try {
			int result = m_liningRingDeformationDao.insertLiningRingDeformation(liningRingDeformation);

			m_liningRingConstructionService.updateDeformationState(liningRingDeformation);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LiningRingDeformation> queryLimitedLiningRingDeformations(int tunnelId, int tunnelSectionId,
	      int liningRingConstructionId, int start, int size) {
		try {
			return m_liningRingDeformationDao.queryLimitedLiningRingDeformations(tunnelId, tunnelSectionId,
			      liningRingConstructionId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<LiningRingDeformation>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LiningRingDeformation> queryLiningRingDeformationByDuration(int liningRingDeformationId, Date start,
	      Date end) {
		try {
			return m_liningRingDeformationDao.queryLiningRingDeformationByDuration(liningRingDeformationId, start, end);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<LiningRingDeformation>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		try {
			return m_liningRingDeformationDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId, liningRingConstructionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setLiningRingDeformationDao(LiningRingDeformationDao liningRingDeformationDao) {
		m_liningRingDeformationDao = liningRingDeformationDao;
	}

	@Override
	public int updateLiningRingDeformation(LiningRingDeformation liningRingDeformation) {
		try {
			int result = m_liningRingDeformationDao.updateLiningRingDeformation(liningRingDeformation);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public LiningRingDeformation queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		try {
			return m_liningRingDeformationDao.queryLastestDeformation(tunnelId, tunnelSectionId, liningRingConstructionId);

		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	public void setLiningRingConstructionService(LiningRingConstructionService liningRingConstructionService) {
   	m_liningRingConstructionService = liningRingConstructionService;
   }
	
}
