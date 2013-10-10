package com;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.liningRingConstruction.LiningRingConstruction;

public abstract class LineChartAction extends FileUploadAction {

	private static final long serialVersionUID = 6612697338740069923L;

	protected LineChart m_lineChart;

	protected LineChart m_secondLineChart;

	protected LiningRingConstruction m_liningRingConstruction;

	protected Date m_start;

	protected Date m_end;

	public Date getEnd() {
		return m_end;
	}

	public LineChart getLineChart() {
		return m_lineChart;
	}

	public Date getStart() {
		return m_start;
	}

	public void setEnd(String end) {
		try {
			m_end = m_sdf.parse(end);
		} catch (ParseException e) {
		}
	}

	public void setStart(String start) {
		try {
			m_start = m_sdf.parse(start);
		} catch (ParseException e) {
		}
	}

	public LiningRingConstruction getLiningRingConstruction() {
		return m_liningRingConstruction;
	}

	public void setLiningRingConstruction(LiningRingConstruction liningRingConstruction) {
		m_liningRingConstruction = liningRingConstruction;
	}

	public LineChart getSecondLineChart() {
		return m_secondLineChart;
	}

	public void setSecondLineChart(LineChart secondLineChart) {
		m_secondLineChart = secondLineChart;
	}

	protected long formatTime(Date date) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(date);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

}
