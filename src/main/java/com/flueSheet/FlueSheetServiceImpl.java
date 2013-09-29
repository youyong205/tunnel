package com.flueSheet;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class FlueSheetServiceImpl implements FlueSheetService {

	private FlueSheetDao m_flueSheetDao;

	private Logger m_logger = Logger.getLogger(FlueSheetServiceImpl.class);

	@Override
	public int deleteFlueSheet(int id) {
		try {
			int result = m_flueSheetDao.deleteFlueSheet(id);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public FlueSheet findByName(String name) {
		try {
			return m_flueSheetDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public FlueSheet findByPK(int id) {
		try {
			return m_flueSheetDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertFlueSheet(FlueSheet flueSheet) {
		try {
			int result = m_flueSheetDao.insertFlueSheet(flueSheet);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FlueSheet> queryLimitedFlueSheets(int tunnelId, int tunnelSectionId, int start, int size) {
		try {
			return m_flueSheetDao.queryLimitedFlueSheets(tunnelId, tunnelSectionId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<FlueSheet>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId) {
		try {
			return m_flueSheetDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setFlueSheetDao(FlueSheetDao flueSheetDao) {
		m_flueSheetDao = flueSheetDao;
	}

	@Override
	public int updateFlueSheet(FlueSheet flueSheet) {
		try {
			int result = m_flueSheetDao.updateFlueSheet(flueSheet);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
