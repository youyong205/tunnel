package com.facility;

import java.util.Date;

import com.document.Document;
import com.schedule.Schedule;
import com.tunnel.Tunnel;
import com.tunnelSection.TunnelSection;

public class Facility {

	private int m_id;

	private int m_tunnelId;

	private Tunnel m_tunnel;

	private int m_tunnelSectionId;

	private TunnelSection m_tunnelSection;

	private int m_documentId;

	private Document m_document;

	private int m_scheduleId;

	private Schedule m_schedule;

	private String m_name;

	private String m_type;

	private String m_lineType;
	
	private String m_stakeMileage;
	
	private String m_position;

	private String m_liningRingId;

	private String m_boxNumber;

	private double m_width;

	private double m_height;

	private int m_constructionUnitId;

	private String m_des;

	private Date m_creationDate;

	private Date m_modifyDate;

	public int getId() {
		return m_id;
	}

	public void setId(int id) {
		m_id = id;
	}

	public int getTunnelId() {
		return m_tunnelId;
	}

	public void setTunnelId(int tunnelId) {
		m_tunnelId = tunnelId;
	}

	public Tunnel getTunnel() {
		return m_tunnel;
	}

	public void setTunnel(Tunnel tunnel) {
		m_tunnel = tunnel;
	}

	public int getTunnelSectionId() {
		return m_tunnelSectionId;
	}

	public void setTunnelSectionId(int tunnelSectionId) {
		m_tunnelSectionId = tunnelSectionId;
	}

	public TunnelSection getTunnelSection() {
		return m_tunnelSection;
	}

	public void setTunnelSection(TunnelSection tunnelSection) {
		m_tunnelSection = tunnelSection;
	}

	public int getDocumentId() {
		return m_documentId;
	}

	public void setDocumentId(int documentId) {
		m_documentId = documentId;
	}

	public Document getDocument() {
		return m_document;
	}

	public void setDocument(Document document) {
		m_document = document;
	}

	public int getScheduleId() {
		return m_scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		m_scheduleId = scheduleId;
	}

	public Schedule getSchedule() {
		return m_schedule;
	}

	public void setSchedule(Schedule schedule) {
		m_schedule = schedule;
	}

	public String getName() {
		return m_name;
	}

	public void setName(String name) {
		m_name = name;
	}

	public String getType() {
		return m_type;
	}

	public void setType(String type) {
		m_type = type;
	}

	public String getLineType() {
		return m_lineType;
	}

	public void setLineType(String lineType) {
		m_lineType = lineType;
	}

	public String getDes() {
		return m_des;
	}

	public void setDes(String des) {
		m_des = des;
	}

	public Date getCreationDate() {
		return m_creationDate;
	}

	public void setCreationDate(Date creationDate) {
		m_creationDate = creationDate;
	}

	public Date getModifyDate() {
		return m_modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		m_modifyDate = modifyDate;
	}

	public String getLiningRingId() {
		return m_liningRingId;
	}

	public void setLiningRingId(String liningRingId) {
		m_liningRingId = liningRingId;
	}

	public String getBoxNumber() {
		return m_boxNumber;
	}

	public void setBoxNumber(String boxNumber) {
		m_boxNumber = boxNumber;
	}

	public double getWidth() {
		return m_width;
	}

	public void setWidth(double width) {
		m_width = width;
	}

	public double getHeight() {
		return m_height;
	}

	public void setHeight(double height) {
		m_height = height;
	}

	public int getConstructionUnitId() {
		return m_constructionUnitId;
	}

	public void setConstructionUnitId(int constructionUnitId) {
		m_constructionUnitId = constructionUnitId;
	}
	
	public String getStakeMileage() {
   	return m_stakeMileage;
   }

	public void setStakeMileage(String stakeMileage) {
   	m_stakeMileage = stakeMileage;
   }

	public String getPosition() {
   	return m_position;
   }

	public void setPosition(String position) {
   	m_position = position;
   }

	@Override
   public String toString() {
	   return "Facility [m_id=" + m_id + ", m_tunnel=" + m_tunnel + ", m_tunnelSectionId=" + m_tunnelSectionId
	         + ", m_documentId=" + m_documentId + ", m_scheduleId=" + m_scheduleId + ", m_name=" + m_name + ", m_type="
	         + m_type + ", m_lineType=" + m_lineType + ", m_stakeMileage=" + m_stakeMileage + ", m_position="
	         + m_position + ", m_liningRingId=" + m_liningRingId + ", m_boxNumber=" + m_boxNumber + ", m_width="
	         + m_width + ", m_height=" + m_height + ", m_constructionUnitId=" + m_constructionUnitId + "]";
   }

}
