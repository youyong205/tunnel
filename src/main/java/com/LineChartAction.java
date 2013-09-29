package com;

import java.text.ParseException;
import java.util.Date;

public abstract class LineChartAction extends FileUploadAction {

	private static final long serialVersionUID = 6612697338740069923L;

	protected LineChart m_lineChart;

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
}
