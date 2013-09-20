package com.openSection;

import java.util.Date;

import com.document.Document;
import com.schedule.Schedule;
import com.tunnel.Tunnel;

public class OpenSection {
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

	private String m_envelope;

	private double m_startFloor;

	private double m_endFloor;

	private double m_startRoadDome;

	private double m_endRoadDome;

	private double m_crestPileBottom;

	private double m_platformPileBottom;

	private String m_des;

	private Date m_creationDate;

	private Date m_modifyDate;

	public Date getCreationDate() {
		return m_creationDate;
	}

	public double getCrestPileBottom() {
		return m_crestPileBottom;
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

	public double getEndFloor() {
		return m_endFloor;
	}

	public double getEndPosition() {
		return m_endPosition;
	}

	public double getEndRoadDome() {
		return m_endRoadDome;
	}

	public String getEnvelope() {
		return m_envelope;
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

	public double getPlatformPileBottom() {
		return m_platformPileBottom;
	}

	public Schedule getSchedule() {
		return m_schedule;
	}

	public int getScheduleId() {
		return m_scheduleId;
	}

	public double getStartFloor() {
		return m_startFloor;
	}

	public double getStartPosition() {
		return m_startPosition;
	}

	public double getStartRoadDome() {
		return m_startRoadDome;
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

	public void setCrestPileBottom(double crestPileBottom) {
		m_crestPileBottom = crestPileBottom;
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

	public void setEndFloor(double endFloor) {
		m_endFloor = endFloor;
	}

	public void setEndPosition(double endPosition) {
		m_endPosition = endPosition;
	}

	public void setEndRoadDome(double endRoadDome) {
		m_endRoadDome = endRoadDome;
	}

	public void setEnvelope(String envelope) {
		m_envelope = envelope;
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

	public void setPlatformPileBottom(double platformPileBottom) {
		m_platformPileBottom = platformPileBottom;
	}

	public void setSchedule(Schedule schedule) {
		m_schedule = schedule;
	}

	public void setScheduleId(int scheduleId) {
		m_scheduleId = scheduleId;
	}

	public void setStartFloor(double startFloor) {
		m_startFloor = startFloor;
	}

	public void setStartPosition(double startPosition) {
		m_startPosition = startPosition;
	}

	public void setStartRoadDome(double startRoadDome) {
		m_startRoadDome = startRoadDome;
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
		return "OpenSection [m_id=" + m_id + ", m_name=" + m_name + ", m_type=" + m_type + ", m_tunnelId=" + m_tunnelId
		      + ", m_documentId=" + m_documentId + ", m_startPosition=" + m_startPosition + ", m_endPosition="
		      + m_endPosition + ", m_envelope=" + m_envelope + ", m_startFloor=" + m_startFloor + ", m_endFloor="
		      + m_endFloor + ", m_startRoadDome=" + m_startRoadDome + ", m_endRoadDome=" + m_endRoadDome
		      + ", m_crestPileBottom=" + m_crestPileBottom + ", m_platformPileBottom=" + m_platformPileBottom
		      + ", m_des=" + m_des + "]";
	}

}
