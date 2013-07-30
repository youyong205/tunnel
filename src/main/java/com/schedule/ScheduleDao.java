package com.schedule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class ScheduleDao {

	private BaseDao m_baseDao;

	public int deleteSchedule(int id) {
		return m_baseDao.delete("schedule.delete", id);
	}

	public Schedule findByPK(int id) {
		return (Schedule) m_baseDao.queryForObject("schedule.findById", id);
	}

	public int insertSchedule(Schedule schedule) {
		return (Integer) m_baseDao.insert("schedule.insert", schedule);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllSchedules() {
		return m_baseDao.queryForList("schedule.queryAllSchedules");
	}

	public int queryAllSize() {
		return (Integer)m_baseDao.queryForObject("schedule.queryAllSize",null);
   }

	@SuppressWarnings("rawtypes")
	public List queryLimitedSchedules(int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("schedule.queryLimitedSchedules", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateSchedule(Schedule schedule) {
		return m_baseDao.update("schedule.update", schedule);
	}

}
