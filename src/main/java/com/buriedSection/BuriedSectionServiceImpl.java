package com.buriedSection;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class BuriedSectionServiceImpl implements BuriedSectionService {

	private BuriedSectionDao m_buriedSectionDao;

	private Logger m_logger = Logger.getLogger(BuriedSectionServiceImpl.class);

	@Override
	public int deleteBuriedSection(int id) {
		try {
			int result = m_buriedSectionDao.deleteBuriedSection(id);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public BuriedSection findByName(String name) {
		try {
			return m_buriedSectionDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public BuriedSection findByPK(int id) {
		try {
			return m_buriedSectionDao.findByPK(id);

		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public int insertBuriedSection(BuriedSection buriedSection) {
		try {
			return m_buriedSectionDao.insertBuriedSection(buriedSection);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public int queryAllSize() {
		try {
			return m_buriedSectionDao.queryAllSize();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	public List<BuriedSection> queryAllBuriedSections() {
		try {
			return m_buriedSectionDao.queryAllBuriedSections();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<BuriedSection>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BuriedSection> queryLimitedBuriedSections(int start, int size) {
		try {
			return m_buriedSectionDao.queryLimitedBuriedSections(start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<BuriedSection>();
		}
	}

	public void setBuriedSectionDao(BuriedSectionDao buriedSectionDao) {
		m_buriedSectionDao = buriedSectionDao;
	}

	@Override
	public int updateBuriedSection(BuriedSection buriedSection) {
		try {
			return m_buriedSectionDao.updateBuriedSection(buriedSection);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BuriedSection> queryLimitedBuriedSectionsByTunnelId(int tunnelId, int start, int size) {
		try {
			return m_buriedSectionDao.queryLimitedBuriedSectionsByTunnelId(tunnelId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<BuriedSection>();
		}
	}

	@Override
	public int querySizeByTunnelId(int tunnelId) {
		try {
			return m_buriedSectionDao.querySizeByTunnelId(tunnelId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
