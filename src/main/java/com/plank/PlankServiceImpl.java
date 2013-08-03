package com.plank;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class PlankServiceImpl implements PlankService {

	private PlankDao m_plankDao;

	private Logger m_logger = Logger.getLogger(PlankServiceImpl.class);

	@Override
	public int deletePlank(int id) {
		try {
			int result = m_plankDao.deletePlank(id);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public Plank findByName(String name) {
		try {
			return m_plankDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public Plank findByPK(int id) {
		try {
			return m_plankDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertPlank(Plank plank) {
		try {
			int result = m_plankDao.insertPlank(plank);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Plank> queryLimitedPlanks(int tunnelId, int tunnelSectionId, int start,
	      int size) {
		try {
			return m_plankDao.queryLimitedPlanks(tunnelId, tunnelSectionId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Plank>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId) {
		try {
			return m_plankDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setPlankDao(PlankDao plankDao) {
		m_plankDao = plankDao;
	}

	@Override
	public int updatePlank(Plank plank) {
		try {
			int result = m_plankDao.updatePlank(plank);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
