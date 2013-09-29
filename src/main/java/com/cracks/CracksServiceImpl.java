package com.cracks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.liningRingConstruction.LiningRingConstructionService;

public class CracksServiceImpl implements CracksService {

	private CracksDao m_cracksDao;

	private LiningRingConstructionService m_liningRingConstructionService;

	private Logger m_logger = Logger.getLogger(CracksServiceImpl.class);

	@Override
	public int deleteCracks(int id) {
		try {
			int result = m_cracksDao.deleteCracks(id);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public Cracks findByName(String name) {
		try {
			return m_cracksDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public Cracks findByPK(int id) {
		try {
			return m_cracksDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertCracks(Cracks cracks) {
		try {
			int result = m_cracksDao.insertCracks(cracks);

			m_liningRingConstructionService.updateCracksState(cracks);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cracks> queryCracksByDuration(int cracksId, Date start, Date end) {
		try {
			return m_cracksDao.queryCracksByDuration(cracksId, start, end);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Cracks>();
		}
	}

	@Override
	public Cracks queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		try {
			return m_cracksDao.queryLastestDeformation(tunnelId, tunnelSectionId, liningRingConstructionId);

		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cracks> queryLimitedCrackss(int tunnelId, int tunnelSectionId, int liningRingConstructionId, int start,
	      int size) {
		try {
			return m_cracksDao.queryLimitedCrackss(tunnelId, tunnelSectionId, liningRingConstructionId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Cracks>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		try {
			return m_cracksDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId, liningRingConstructionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setCracksDao(CracksDao cracksDao) {
		m_cracksDao = cracksDao;
	}

	public void setLiningRingConstructionService(LiningRingConstructionService liningRingConstructionService) {
		m_liningRingConstructionService = liningRingConstructionService;
	}

	@Override
	public int updateCracks(Cracks cracks) {
		try {
			int result = m_cracksDao.updateCracks(cracks);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
