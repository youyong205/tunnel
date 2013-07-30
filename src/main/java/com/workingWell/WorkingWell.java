package com.workingWell;

import java.util.Date;

import com.document.Document;
import com.schedule.Schedule;
import com.tunnel.Tunnel;

public class WorkingWell {
	private int m_id;

	private String m_name;

	private String m_type;

	private int m_tunnelId;

	private Tunnel m_tunnel;

	private int m_documentId;

	private Document m_document;
	
	private int m_scheduleId;
	
	private Schedule m_schedule;

	private double m_startPosition;

	private double m_endPosition;

	private double m_eleationOne;

	private double m_eleationTwo;

	private double m_eleationThree;

	private double m_eleationFour;

	private double m_eleationMezzanine;

	private String m_des;

	private Date m_creationDate;

	private Date m_modifyDate;

	public Date getCreationDate() {
		return m_creationDate;
	}

	public String getDes() {
		return m_des;
	}

	public Document getDocument() {
		return m_document;
	}

	public int getDocumentId() {
		return m_documentId;
	}

	public double getEleationFour() {
		return m_eleationFour;
	}

	public double getEleationMezzanine() {
		return m_eleationMezzanine;
	}

	public double getEleationOne() {
		return m_eleationOne;
	}

	public double getEleationThree() {
		return m_eleationThree;
	}

	public double getEleationTwo() {
		return m_eleationTwo;
	}

	public double getEndPosition() {
   	return m_endPosition;
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

	public Schedule getSchedule() {
   	return m_schedule;
   }

	public int getScheduleId() {
   	return m_scheduleId;
   }

	public double getStartPosition() {
   	return m_startPosition;
   }

	public Tunnel getTunnel() {
		return m_tunnel;
	}

	public int getTunnelId() {
		return m_tunnelId;
	}


	public String getType() {
		return m_type;
	}

	public void setCreationDate(Date creationDate) {
		m_creationDate = creationDate;
	}

	public void setDes(String des) {
		m_des = des;
	}

	public void setDocument(Document document) {
		m_document = document;
	}

	public void setDocumentId(int documentId) {
		m_documentId = documentId;
	}

	public void setEleationFour(double eleationFour) {
		m_eleationFour = eleationFour;
	}

	public void setEleationMezzanine(double eleationMezzanine) {
		m_eleationMezzanine = eleationMezzanine;
	}

	public void setEleationOne(double eleationOne) {
		m_eleationOne = eleationOne;
	}

	public void setEleationThree(double eleationThree) {
		m_eleationThree = eleationThree;
	}

	public void setEleationTwo(double eleationTwo) {
		m_eleationTwo = eleationTwo;
	}

	public void setEndPosition(double endPosition) {
   	m_endPosition = endPosition;
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

	public void setSchedule(Schedule schedule) {
   	m_schedule = schedule;
   }

	public void setScheduleId(int scheduleId) {
   	m_scheduleId = scheduleId;
   }

	public void setStartPosition(double startPosition) {
   	m_startPosition = startPosition;
   }

	public void setTunnel(Tunnel tunnel) {
		m_tunnel = tunnel;
	}

	public void setTunnelId(int tunnelId) {
		m_tunnelId = tunnelId;
	}

	public void setType(String type) {
		m_type = type;
	}

	@Override
   public String toString() {
	   return "WorkingWell [m_id=" + m_id + ", m_name=" + m_name + ", m_type=" + m_type + ", m_tunnelId=" + m_tunnelId
	         + ", m_documentId=" + m_documentId + ", m_startPosition=" + m_startPosition + ", m_endPosition="
	         + m_endPosition + ", m_eleationOne=" + m_eleationOne + ", m_eleationTwo=" + m_eleationTwo
	         + ", m_eleationThree=" + m_eleationThree + ", m_eleationFour=" + m_eleationFour + ", m_eleationMezzanine="
	         + m_eleationMezzanine + ", m_des=" + m_des + "]";
   }

}
