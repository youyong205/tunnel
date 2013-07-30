package com.schedule;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

public class ScheduleServiceImpl implements ScheduleService {

	private ScheduleDao m_scheduleDao;

	private Logger m_logger = Logger.getLogger(ScheduleServiceImpl.class);

	private static final int SIZE = 100;

	private Map<Integer, Schedule> m_schedules = new LinkedHashMap<Integer, Schedule>(SIZE) {

		private static final long serialVersionUID = 1L;

		@Override
		protected boolean removeEldestEntry(Entry<Integer, Schedule> eldest) {
			return size() > SIZE;
		}
	};

	@Override
	public int deleteSchedule(int id) {
		try {
			int result = m_scheduleDao.deleteSchedule(id);
		
			m_schedules.remove(id);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public Schedule findByPK(int id) {
		Schedule schedule = m_schedules.get(id);
		if (schedule == null) {
			try {
				schedule = m_scheduleDao.findByPK(id);

				if (schedule != null) {
					m_schedules.put(id, schedule);
				}
			} catch (Exception e) {
				m_logger.error(e.getMessage(), e);
			}
		}
		return schedule;
	}

	@Override
	public int insertSchedule(Schedule schedule) {
		try {
			int result = m_scheduleDao.insertSchedule(schedule);
			
			m_schedules.put(schedule.getId(), schedule);
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
			
			m_schedules.put(schedule.getId(), schedule);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
