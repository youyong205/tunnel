package com.workingWell;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class WorkingWellServiceImpl implements WorkingWellService {

	private WorkingWellDao m_workingWellDao;

	private Logger m_logger = Logger.getLogger(WorkingWellServiceImpl.class);

	@Override
	public int deleteWorkingWell(int id) {
		try {
			int result = m_workingWellDao.deleteWorkingWell(id);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public WorkingWell findByName(String name) {
		try {
			return m_workingWellDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public WorkingWell findByPK(int id) {
		try {
			return m_workingWellDao.findByPK(id);

		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public int insertWorkingWell(WorkingWell workingWell) {
		try {
			return m_workingWellDao.insertWorkingWell(workingWell);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public int queryAllSize() {
		try {
			return m_workingWellDao.queryAllSize();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	public List<WorkingWell> queryAllWorkingWells() {
		try {
			return m_workingWellDao.queryAllWorkingWells();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<WorkingWell>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkingWell> queryLimitedWorkingWells(int start, int size) {
		try {
			return m_workingWellDao.queryLimitedWorkingWells(start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<WorkingWell>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkingWell> queryLimitedWorkingWellsByTunnelId(int tunnelId, int start, int size) {
		try {
			return m_workingWellDao.queryLimitedWorkingWellsByTunnelId(tunnelId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<WorkingWell>();
		}
	}

	@Override
	public int querySizeByTunnelId(int tunnelId) {
		try {
			return m_workingWellDao.querySizeByTunnelId(tunnelId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setWorkingWellDao(WorkingWellDao workingWellDao) {
		m_workingWellDao = workingWellDao;
	}

	@Override
	public int updateWorkingWell(WorkingWell workingWell) {
		try {
			return m_workingWellDao.updateWorkingWell(workingWell);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
