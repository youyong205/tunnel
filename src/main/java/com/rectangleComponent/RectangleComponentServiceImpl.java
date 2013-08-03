package com.rectangleComponent;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class RectangleComponentServiceImpl implements RectangleComponentService {

	private RectangleComponentDao m_rectangleComponentDao;

	private Logger m_logger = Logger.getLogger(RectangleComponentServiceImpl.class);

	@Override
	public int deleteRectangleComponent(int id) {
		try {
			int result = m_rectangleComponentDao.deleteRectangleComponent(id);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public RectangleComponent findByName(String name) {
		try {
			return m_rectangleComponentDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public RectangleComponent findByPK(int id) {
		try {
			return m_rectangleComponentDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertRectangleComponent(RectangleComponent rectangleComponent) {
		try {
			int result = m_rectangleComponentDao.insertRectangleComponent(rectangleComponent);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RectangleComponent> queryLimitedRectangleComponents(int tunnelId, int tunnelSectionId, int start,
	      int size) {
		try {
			return m_rectangleComponentDao.queryLimitedRectangleComponents(tunnelId, tunnelSectionId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<RectangleComponent>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId) {
		try {
			return m_rectangleComponentDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setRectangleComponentDao(RectangleComponentDao rectangleComponentDao) {
		m_rectangleComponentDao = rectangleComponentDao;
	}

	@Override
	public int updateRectangleComponent(RectangleComponent rectangleComponent) {
		try {
			int result = m_rectangleComponentDao.updateRectangleComponent(rectangleComponent);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
