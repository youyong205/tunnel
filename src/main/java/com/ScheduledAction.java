package com;

import java.util.List;

import com.constructionUnit.ConstructionUnit;
import com.constructionUnit.ConstructionUnitService;
import com.schedule.Schedule;
import com.schedule.ScheduleService;

public abstract class ScheduledAction extends FileUploadAction {

	private static final long serialVersionUID = 1L;

	protected Schedule m_schedule;

	protected int m_scheduleId;

	protected ScheduleService m_scheduleService;

	protected ConstructionUnitService m_constructionUnitService;

	protected List<Schedule> m_schedules;

	protected List<ConstructionUnit> m_constructionUnits;

	public List<ConstructionUnit> getConstructionUnits() {
		return m_constructionUnits;
	}

	public Schedule getSchedule() {
		return m_schedule;
	}

	public int getScheduleId() {
		return m_scheduleId;
	}

	public List<Schedule> getSchedules() {
		return m_schedules;
	}

	public void setConstructionUnitService(ConstructionUnitService constructionUnitService) {
		m_constructionUnitService = constructionUnitService;
	}

	public void setSchedule(Schedule schedule) {
		m_schedule = schedule;
	}

	public void setScheduleId(int scheduleId) {
		m_scheduleId = scheduleId;
	}

	public void setScheduleService(ScheduleService scheduleService) {
		m_scheduleService = scheduleService;
	}

}
