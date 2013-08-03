package com.saddleWeight;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class SaddleWeightServiceImpl implements SaddleWeightService {

	private SaddleWeightDao m_saddleWeightDao;

	private Logger m_logger = Logger.getLogger(SaddleWeightServiceImpl.class);

	@Override
	public int deleteSaddleWeight(int id) {
		try {
			int result = m_saddleWeightDao.deleteSaddleWeight(id);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public SaddleWeight findByName(String name) {
		try {
			return m_saddleWeightDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public SaddleWeight findByPK(int id) {
		try {
			return m_saddleWeightDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertSaddleWeight(SaddleWeight saddleWeight) {
		try {
			int result = m_saddleWeightDao.insertSaddleWeight(saddleWeight);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SaddleWeight> queryLimitedSaddleWeights(int tunnelId, int tunnelSectionId, int start,
	      int size) {
		try {
			return m_saddleWeightDao.queryLimitedSaddleWeights(tunnelId, tunnelSectionId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<SaddleWeight>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId) {
		try {
			return m_saddleWeightDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setSaddleWeightDao(SaddleWeightDao saddleWeightDao) {
		m_saddleWeightDao = saddleWeightDao;
	}

	@Override
	public int updateSaddleWeight(SaddleWeight saddleWeight) {
		try {
			int result = m_saddleWeightDao.updateSaddleWeight(saddleWeight);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
