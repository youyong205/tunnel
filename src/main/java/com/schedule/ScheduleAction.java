package com.schedule;

import java.util.List;

import org.apache.log4j.Logger;

import com.Constrants;
import com.PagedAction;
import com.log.Log;

public class ScheduleAction extends PagedAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(ScheduleAction.class);

	private List<Schedule> m_schedules;

	private int m_scheduleId;

	private ScheduleService m_scheduleService;

	private Schedule m_schedule = new Schedule();

	@Override
   public String getActionModule() {
		return Constrants.s_schedule_model;
   }

	public Schedule getSchedule() {
		return m_schedule;
	}

	public List<Schedule> getSchedules() {
		return m_schedules;
	}

	public String scheduleAdd(){
		return SUCCESS;
	}

	public String scheduleAddSubmit() {
		try {
			int id = m_scheduleService.insertSchedule(m_schedule);
			if (id > 0) {
				Log log = createLog(Constrants.s_schedule_model, Constrants.s_operation_add, m_schedule);

				m_logService.insertLog(log);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String scheduleDelete() {
		try {
			int count = m_scheduleService.deleteSchedule(m_scheduleId);
			if (count > 0) {
				Log log = createLog(Constrants.s_schedule_model, Constrants.s_operation_delete, m_scheduleId);

				m_logService.insertLog(log);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}
	
	public String scheduleList() {
		try {
			m_totalSize = m_scheduleService.queryAllSize();
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_schedules = m_scheduleService.queryLimitedSchedules(start, SIZE);
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String scheduleUpdate() {
		try {
			m_schedule = m_scheduleService.findByPK(m_scheduleId);
			if (m_schedule != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String scheduleUpdateSubmit() {
		try {
			int count = m_scheduleService.updateSchedule(m_schedule);
			if (count > 0) {
				Log log = createLog(Constrants.s_schedule_model, Constrants.s_operation_update, m_schedule);

				m_logService.insertLog(log);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
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