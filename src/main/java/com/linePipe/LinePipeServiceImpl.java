package com.linePipe;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class LinePipeServiceImpl implements LinePipeService {

	private LinePipeDao m_linePipeDao;

	private Logger m_logger = Logger.getLogger(LinePipeServiceImpl.class);

	@Override
	public int deleteLinePipe(int id) {
		try {
			int result = m_linePipeDao.deleteLinePipe(id);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public LinePipe findByName(String name) {
		try {
			return m_linePipeDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public LinePipe findByPK(int id) {
		try {
			return m_linePipeDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertLinePipe(LinePipe linePipe) {
		try {
			int result = m_linePipeDao.insertLinePipe(linePipe);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LinePipe> queryLimitedLinePipes(int tunnelId, int tunnelSectionId, int start, int size) {
		try {
			return m_linePipeDao.queryLimitedLinePipes(tunnelId, tunnelSectionId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<LinePipe>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId) {
		try {
			return m_linePipeDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setLinePipeDao(LinePipeDao linePipeDao) {
		m_linePipeDao = linePipeDao;
	}

	@Override
	public int updateLinePipe(LinePipe linePipe) {
		try {
			int result = m_linePipeDao.updateLinePipe(linePipe);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
