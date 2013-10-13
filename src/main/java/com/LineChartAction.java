package com;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.liningRingConstruction.LiningRingConstruction;

public abstract class LineChartAction extends FileUploadAction {

	private static final long serialVersionUID = 6612697338740069923L;

	protected TimeLineChart m_lineChart;

	protected TimeLineChart m_secondLineChart;

	protected LineChart m_generalChart;

	protected LineChart m_generalChart2;

	protected LiningRingConstruction m_liningRingConstruction;

	protected Date m_start;

	protected Date m_end;

	public Date getEnd() {
		return m_end;
	}

	public TimeLineChart getLineChart() {
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

	public TimeLineChart getSecondLineChart() {
		return m_secondLineChart;
	}

	public void setSecondLineChart(TimeLineChart secondLineChart) {
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

	protected void findOrCreateSum(Map<Integer, Double> map, int key, double value) {
		Double old = map.get(key);
		if (old == null) {
			map.put(key, value);
		} else {
			map.put(key, value + old);
		}
	}

	protected void findOrCreateMax(Map<Integer, Double> map, int key, double value) {
		Double old = map.get(key);
		if (old == null) {
			map.put(key, value);
		} else {
			if (value > old) {
				map.put(key, value);
			}
		}
	}

	protected List<Integer> convertToString(List<String> ids) {
		List<Integer> result = new ArrayList<Integer>();
		for (String str : ids) {
			if (str != null && str.length() > 0) {
				String[] s = str.split(",");

				for (String temp : s) {
					if (!"0".equals(temp) && !"-".equals(temp)) {
						try {
							result.add(Integer.parseInt(temp));
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return result;
	}

	public LineChart getGeneralChart() {
		return m_generalChart;
	}

	public LineChart getGeneralChart2() {
		return m_generalChart2;
	}

}
