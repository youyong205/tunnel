package com.escape;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class EscapeServiceImpl implements EscapeService {

	private EscapeDao m_escapeDao;

	private Logger m_logger = Logger.getLogger(EscapeServiceImpl.class);

	@Override
	public int deleteEscape(int id) {
		try {
			int result = m_escapeDao.deleteEscape(id);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public Escape findByName(String name) {
		try {
			return m_escapeDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public Escape findByPK(int id) {
		try {
			return m_escapeDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertEscape(Escape escape) {
		try {
			int result = m_escapeDao.insertEscape(escape);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Escape> queryLimitedEscapes(int tunnelId, int tunnelSectionId, int start, int size) {
		try {
			return m_escapeDao.queryLimitedEscapes(tunnelId, tunnelSectionId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Escape>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId) {
		try {
			return m_escapeDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setEscapeDao(EscapeDao escapeDao) {
		m_escapeDao = escapeDao;
	}

	@Override
	public int updateEscape(Escape escape) {
		try {
			int result = m_escapeDao.updateEscape(escape);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
