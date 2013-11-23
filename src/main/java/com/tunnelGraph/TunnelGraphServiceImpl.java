package com.tunnelGraph;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class TunnelGraphServiceImpl implements TunnelGraphService {

	private TunnelGraphDao m_tunnelGraphDao;

	private Logger m_logger = Logger.getLogger(TunnelGraphServiceImpl.class);

	@Override
	public int deleteTunnelGraph(int id) {
		try {
			int result = m_tunnelGraphDao.deleteTunnelGraph(id);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public TunnelGraph findByPK(int id) {
		try {
			return m_tunnelGraphDao.findByPK(id);

		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public int insertTunnelGraph(TunnelGraph tunnelGraph) {
		try {
			return m_tunnelGraphDao.insertTunnelGraph(tunnelGraph);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TunnelGraph> queryLimitedTunnelGraphsByTunnelIdAndLineType(int tunnelId, String lineType, int start,
	      int size) {
		try {
			return m_tunnelGraphDao.queryLimitedTunnelGraphsByTunnelIdAndLineType(tunnelId, lineType, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<TunnelGraph>();
		}
	}

	@Override
	public int querySizeByTunnelIdAndLineType(int tunnelId, String lineType) {
		try {
			return m_tunnelGraphDao.querySizeByTunnelIdAndLineType(tunnelId, lineType);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setTunnelGraphDao(TunnelGraphDao tunnelGraphDao) {
		m_tunnelGraphDao = tunnelGraphDao;
	}

	@Override
	public int updateTunnelGraph(TunnelGraph tunnelGraph) {
		try {
			return m_tunnelGraphDao.updateTunnelGraph(tunnelGraph);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
