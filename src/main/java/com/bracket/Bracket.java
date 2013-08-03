package com.bracket;

import java.util.Date;

import com.document.Document;
import com.schedule.Schedule;
import com.tunnel.Tunnel;
import com.tunnelSection.TunnelSection;

public class Bracket {

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

	private double m_startPosition;

	private double m_endPosition;

	private String m_concreteStrength;

	private String m_reinforcementStrength;

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

	public double getStartPosition() {
		return m_startPosition;
	}

	public void setStartPosition(double startPosition) {
		m_startPosition = startPosition;
	}

	public double getEndPosition() {
		return m_endPosition;
	}

	public void setEndPosition(double endPosition) {
		m_endPosition = endPosition;
	}

	public String getConcreteStrength() {
		return m_concreteStrength;
	}

	public void setConcreteStrength(String concreteStrength) {
		m_concreteStrength = concreteStrength;
	}

	public String getReinforcementStrength() {
		return m_reinforcementStrength;
	}

	public void setReinforcementStrength(String reinforcementStrength) {
		m_reinforcementStrength = reinforcementStrength;
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

	@Override
	public String toString() {
		return "Plank [m_id=" + m_id + ", m_tunnelId=" + m_tunnelId + ", m_tunnelSectionId=" + m_tunnelSectionId
		      + ", m_documentId=" + m_documentId + ", m_scheduleId=" + m_scheduleId + ", m_name=" + m_name + ", m_type="
		      + m_type + ", m_lineType=" + m_lineType + ", m_startPosition=" + m_startPosition + ", m_endPosition="
		      + m_endPosition + ", m_concreteStrength=" + m_concreteStrength + ", m_reinforcementStrength="
		      + m_reinforcementStrength + ", m_des=" + m_des + "]";
	}

}
