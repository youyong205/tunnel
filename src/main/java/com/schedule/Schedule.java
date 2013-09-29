package com.schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.constructionUnit.ConstructionUnit;

public class Schedule {

	private int m_id;

	private int m_constructionUnitId;

	private ConstructionUnit m_constructionUnit;

	private String m_type;

	private Date m_startTime;

	private Date m_endTime;

	private String m_name;

	private String m_des;

	private Date m_creationDate;

	private Date m_modifyDate;

	public static int CONTACT_CHANNEL = 1;

	private SimpleDateFormat m_simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public ConstructionUnit getConstructionUnit() {
		return m_constructionUnit;
	}

	public int getConstructionUnitId() {
		return m_constructionUnitId;
	}

	public Date getCreationDate() {
		return m_creationDate;
	}

	public String getDes() {
		return m_des;
	}

	public Date getEndTime() {
		return m_endTime;
	}

	public String getEndTimeStr() {
		return m_simpleDateFormat.format(m_endTime);

	}

	public int getId() {
		return m_id;
	}

	public Date getModifyDate() {
		return m_modifyDate;
	}

	public String getName() {
		return m_name;
	}

	public Date getStartTime() {
		return m_startTime;
	}

	public String getStartTimeStr() {
		return m_simpleDateFormat.format(m_startTime);
	}

	public String getType() {
		return m_type;
	}

	public void setConstructionUnit(ConstructionUnit constructionUnit) {
		m_constructionUnit = constructionUnit;
	}

	public void setConstructionUnitId(int constructionUnitId) {
		m_constructionUnitId = constructionUnitId;
	}

	public void setCreationDate(Date creationDate) {
		m_creationDate = creationDate;
	}

	public void setDes(String des) {
		m_des = des;
	}

	public void setEndTime(Date endTime) {
		m_endTime = endTime;
	}

	public void setEndTimeStr(String end) throws ParseException {
		m_endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(end);
	}

	public void setId(int id) {
		m_id = id;
	}

	public void setModifyDate(Date modifyDate) {
		m_modifyDate = modifyDate;
	}

	public void setName(String name) {
		m_name = name;
	}

	public void setStartTime(Date startTime) {
		m_startTime = startTime;
	}

	public void setStartTimeStr(String start) throws ParseException {
		m_startTime = m_simpleDateFormat.parse(start);
	}

	public void setType(String type) {
		m_type = type;
	}

	@Override
	public String toString() {
		return "Schedule [m_id=" + m_id + ", m_constructionUnitID=" + m_constructionUnitId + ", m_constructionUnit="
		      + m_constructionUnit + ", m_type=" + m_type + ", m_startTime=" + m_startTime + ", m_endTime=" + m_endTime
		      + ", m_name=" + m_name + ", m_des=" + m_des + "]";
	}

}
