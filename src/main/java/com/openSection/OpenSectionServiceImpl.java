package com.openSection;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class OpenSectionServiceImpl implements OpenSectionService {

	private OpenSectionDao m_openSectionDao;

	private Logger m_logger = Logger.getLogger(OpenSectionServiceImpl.class);

	@Override
	public int deleteOpenSection(int id) {
		try {
			int result = m_openSectionDao.deleteOpenSection(id);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public OpenSection findByName(String name) {
		try {
			return m_openSectionDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public OpenSection findByPK(int id) {
		try {
			return m_openSectionDao.findByPK(id);

		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public int insertOpenSection(OpenSection openSection) {
		try {
			return m_openSectionDao.insertOpenSection(openSection);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public int queryAllSize() {
		try {
			return m_openSectionDao.queryAllSize();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	public List<OpenSection> queryAllOpenSections() {
		try {
			return m_openSectionDao.queryAllOpenSections();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<OpenSection>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OpenSection> queryLimitedOpenSections(int start, int size) {
		try {
			return m_openSectionDao.queryLimitedOpenSections(start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<OpenSection>();
		}
	}

	public void setOpenSectionDao(OpenSectionDao openSectionDao) {
		m_openSectionDao = openSectionDao;
	}

	@Override
	public int updateOpenSection(OpenSection openSection) {
		try {
			return m_openSectionDao.updateOpenSection(openSection);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OpenSection> queryLimitedOpenSectionsByTunnelId(int tunnelId, int start, int size) {
		try {
			return m_openSectionDao.queryLimitedOpenSectionsByTunnelId(tunnelId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<OpenSection>();
		}
	}

	@Override
	public int querySizeByTunnelId(int tunnelId) {
		try {
			return m_openSectionDao.querySizeByTunnelId(tunnelId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
