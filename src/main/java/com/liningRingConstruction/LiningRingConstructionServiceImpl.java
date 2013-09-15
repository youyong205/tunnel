package com.liningRingConstruction;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class LiningRingConstructionServiceImpl implements LiningRingConstructionService {

	private LiningRingConstructionDao m_liningRingConstructionDao;

	private Logger m_logger = Logger.getLogger(LiningRingConstructionServiceImpl.class);

	@Override
	public int deleteLiningRingConstruction(int id) {
		try {
			int result = m_liningRingConstructionDao.deleteLiningRingConstruction(id);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public LiningRingConstruction findByName(String name) {
		try {
			return m_liningRingConstructionDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public LiningRingConstruction findByPK(int id) {
		try {
			return m_liningRingConstructionDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertLiningRingConstruction(LiningRingConstruction liningRingConstruction) {
		try {
			int result = m_liningRingConstructionDao.insertLiningRingConstruction(liningRingConstruction);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LiningRingConstruction> queryLimitedLiningRingConstructions(int tunnelId, int tunnelSectionId, int start,
	      int size) {
		try {
			return m_liningRingConstructionDao.queryLimitedLiningRingConstructions(tunnelId, tunnelSectionId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<LiningRingConstruction>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId) {
		try {
			return m_liningRingConstructionDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setLiningRingConstructionDao(LiningRingConstructionDao liningRingConstructionDao) {
		m_liningRingConstructionDao = liningRingConstructionDao;
	}

	@Override
	public int updateLiningRingConstruction(LiningRingConstruction liningRingConstruction) {
		try {
			int result = m_liningRingConstructionDao.updateLiningRingConstruction(liningRingConstruction);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
