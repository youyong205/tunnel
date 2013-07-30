package com.tunnelSection;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class TunnelSectionServiceImpl implements TunnelSectionService {

	private TunnelSectionDao m_tunnelSectionDao;

	private Logger m_logger = Logger.getLogger(TunnelSectionServiceImpl.class);

	@Override
	public int deleteTunnelSection(int id) {
		try {
			int result = m_tunnelSectionDao.deleteTunnelSection(id);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public TunnelSection findByName(String name) {
		try {
			return m_tunnelSectionDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public TunnelSection findByPK(int id) {
		try {
			return m_tunnelSectionDao.findByPK(id);

		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public int insertTunnelSection(TunnelSection tunnelSection) {
		try {
			return m_tunnelSectionDao.insertTunnelSection(tunnelSection);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public int queryAllSize() {
		try {
			return m_tunnelSectionDao.queryAllSize();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TunnelSection> queryAllTunnelSections() {
		try {
			return m_tunnelSectionDao.queryAllTunnelSections();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<TunnelSection>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TunnelSection> queryLimitedTunnelSections(int start, int size) {
		try {
			return m_tunnelSectionDao.queryLimitedTunnelSections(start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<TunnelSection>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TunnelSection> queryLimitedTunnelSectionsByTunnelId(int tunnelId, int start, int size) {
		try {
			return m_tunnelSectionDao.queryLimitedTunnelSectionsByTunnelId(tunnelId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<TunnelSection>();
		}
	}

	@Override
	public int querySizeByTunnelId(int tunnelId) {
		try {
			return m_tunnelSectionDao.querySizeByTunnelId(tunnelId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setTunnelSectionDao(TunnelSectionDao tunnelSectionDao) {
		m_tunnelSectionDao = tunnelSectionDao;
	}

	@Override
	public int updateTunnelSection(TunnelSection tunnelSection) {
		try {
			return m_tunnelSectionDao.updateTunnelSection(tunnelSection);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
