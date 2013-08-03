package com.curing;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class CuringServiceImpl implements CuringService {

	@Autowired
	private CuringDao m_curingDao;

	private Logger m_logger = Logger.getLogger(CuringServiceImpl.class);

	@Override
	public int deleteCuring(int id) {
		try {
			int result = m_curingDao.deleteCuring(id);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public Curing findByPK(int id) {
		try {
			return m_curingDao.findByPK(id);

		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertCuring(Curing curing) {
		try {
			int result = m_curingDao.insertCuring(curing);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public int queryCuringSizeByType(int tunnelId, int tunnelSectionId,String type) {
		try {
			return m_curingDao.queryCuringSizeByType(tunnelId,tunnelSectionId, type);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Curing> queryLimitedCuringsByType(int tunnelId, int tunnelSectionId, String type, int start, int size) {
		try {
			return m_curingDao.queryLimitedCuringsByType(tunnelId,tunnelSectionId, type, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Curing>();
		}
	}

	public void setCuringDao(CuringDao curingDao) {
		m_curingDao = curingDao;
	}

	@Override
	public int updateCuring(Curing curing) {
		try {
			int result = m_curingDao.updateCuring(curing);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
