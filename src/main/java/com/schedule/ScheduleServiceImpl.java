package com.schedule;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ScheduleServiceImpl implements ScheduleService {

	private ScheduleDao m_scheduleDao;

	private Logger m_logger = Logger.getLogger(ScheduleServiceImpl.class);

	@Override
	public int deleteSchedule(int id) {
		try {
			return m_scheduleDao.deleteSchedule(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public Schedule findByPK(int id) {
		try {
			return m_scheduleDao.findByPK(id);

		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertSchedule(Schedule schedule) {
		try {
			int result = m_scheduleDao.insertSchedule(schedule);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Schedule> queryAllSchedules() {
		try {
			return m_scheduleDao.queryAllSchedules();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Schedule>();
		}
	}

	@Override
	public int queryAllSize() {
		try {
			return m_scheduleDao.queryAllSize();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Schedule> queryLimitedSchedules(int start, int size) {
		try {
			return m_scheduleDao.queryLimitedSchedules(start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Schedule>();
		}
	}

	public void setScheduleDao(ScheduleDao scheduleDao) {
		m_scheduleDao = scheduleDao;
	}

	@Override
	public int updateSchedule(Schedule schedule) {
		try {
			int result = m_scheduleDao.updateSchedule(schedule);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
