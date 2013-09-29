package com.schedule;

import java.util.List;

public interface ScheduleService {

	public int deleteSchedule(int id);

	public Schedule findByPK(int id);

	public int insertSchedule(Schedule schedule);

	public List<Schedule> queryAllSchedules();

	public int queryAllSize();

	public List<Schedule> queryLimitedSchedules(int start, int size);

	public int updateSchedule(Schedule schedule);

}
