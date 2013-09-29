package com.seepage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.liningRingConstruction.LiningRingConstructionService;

public class SeepageServiceImpl implements SeepageService {

	private SeepageDao m_seepageDao;

	private LiningRingConstructionService m_liningRingConstructionService;
	
	private Logger m_logger = Logger.getLogger(SeepageServiceImpl.class);

	@Override
	public int deleteSeepage(int id) {
		try {
			int result = m_seepageDao.deleteSeepage(id);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public Seepage findByName(String name) {
		try {
			return m_seepageDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public Seepage findByPK(int id) {
		try {
			return m_seepageDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertSeepage(Seepage seepage) {
		try {
			int result = m_seepageDao.insertSeepage(seepage);

			m_liningRingConstructionService.updateSeepageState(seepage);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public Seepage queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		try {
			return m_seepageDao.queryLastestDeformation(tunnelId, tunnelSectionId, liningRingConstructionId);

		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Seepage> queryLimitedSeepages(int tunnelId, int tunnelSectionId,
	      int liningRingConstructionId, int start, int size) {
		try {
			return m_seepageDao.queryLimitedSeepages(tunnelId, tunnelSectionId,
			      liningRingConstructionId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Seepage>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Seepage> querySeepageByDuration(int seepageId, Date start,
	      Date end) {
		try {
			return m_seepageDao.querySeepageByDuration(seepageId, start, end);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Seepage>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		try {
			return m_seepageDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId, liningRingConstructionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setLiningRingConstructionService(LiningRingConstructionService liningRingConstructionService) {
   	m_liningRingConstructionService = liningRingConstructionService;
   }

	public void setSeepageDao(SeepageDao seepageDao) {
		m_seepageDao = seepageDao;
	}

	@Override
	public int updateSeepage(Seepage seepage) {
		try {
			int result = m_seepageDao.updateSeepage(seepage);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}
	
}
