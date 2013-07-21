package com.tunnelSection;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

public class TunnelSectionServiceImpl implements TunnelSectionService {

	private TunnelSectionDao m_tunnelSectionDao;

	private Logger m_logger = Logger.getLogger(TunnelSectionServiceImpl.class);

	private static final int SIZE = 100;

	private Map<Integer, TunnelSection> m_tunnelSections = new LinkedHashMap<Integer, TunnelSection>(SIZE) {

		private static final long serialVersionUID = 1L;

		@Override
		protected boolean removeEldestEntry(Entry<Integer, TunnelSection> eldest) {
			return size() > SIZE;
		}
	};

	@Override
	public int deleteTunnelSection(int id) {
		try {
			int result = m_tunnelSectionDao.deleteTunnelSection(id);

			m_tunnelSections.remove(id);
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
		TunnelSection tunnelSection = m_tunnelSections.get(id);
		if (tunnelSection == null) {
			try {
				tunnelSection = m_tunnelSectionDao.findByPK(id);

				if (tunnelSection != null) {
					m_tunnelSections.put(id, tunnelSection);
				}
			} catch (Exception e) {
				m_logger.error(e.getMessage(), e);
			}
		}
		return tunnelSection;
	}

	@Override
	public int insertTunnelSection(TunnelSection tunnelSection) {
		try {
			int result = m_tunnelSectionDao.insertTunnelSection(tunnelSection);

			m_tunnelSections.put(tunnelSection.getId(), tunnelSection);
			return result;
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

	public void setTunnelSectionDao(TunnelSectionDao tunnelSectionDao) {
		m_tunnelSectionDao = tunnelSectionDao;
	}

	@Override
	public int updateTunnelSection(TunnelSection tunnelSection) {
		try {
			int result = m_tunnelSectionDao.updateTunnelSection(tunnelSection);

			m_tunnelSections.put(tunnelSection.getId(), tunnelSection);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
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

}
