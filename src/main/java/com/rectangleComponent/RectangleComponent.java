package com.rectangleComponent;

import java.util.Date;

import com.document.Document;
import com.schedule.Schedule;
import com.tunnel.Tunnel;
import com.tunnelSection.TunnelSection;

public class RectangleComponent {

	private int m_id;

	private int m_tunnelId;

	private Tunnel m_tunnel;

	private int m_tunnelSectionId;

	private TunnelSection m_tunnelSection;

	private int documentId;

	private Document m_document;

	private int m_scheduleId;

	private Schedule m_schedule;

	private String m_name;

	private String m_type;

	private String m_lineType;

	private double m_startPosition;

	private double m_endPosition;

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
		return documentId;
	}

	public double getEndPosition() {
		return m_endPosition;
	}

	public int getId() {
		return m_id;
	}

	public String getLineType() {
		return m_lineType;
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

	public TunnelSection getTunnelSection() {
		return m_tunnelSection;
	}

	public int getTunnelSectionId() {
		return m_tunnelSectionId;
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
		this.documentId = documentId;
	}

	public void setEndPosition(double endPosition) {
		m_endPosition = endPosition;
	}

	public void setId(int id) {
		m_id = id;
	}

	public void setLineType(String lineType) {
		m_lineType = lineType;
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

	public void setTunnelSection(TunnelSection tunnelSection) {
		m_tunnelSection = tunnelSection;
	}

	public void setTunnelSectionId(int tunnelSectionId) {
		m_tunnelSectionId = tunnelSectionId;
	}

	public void setType(String type) {
		m_type = type;
	}

	@Override
	public String toString() {
		return "RectangleComponent [m_id=" + m_id + ", m_tunnelId=" + m_tunnelId + ", m_tunnelSectionId="
		      + m_tunnelSectionId + ", documentId=" + documentId + ", m_scheduleId=" + m_scheduleId + ", m_name="
		      + m_name + ", m_type=" + m_type + ", m_lineType=" + m_lineType + ", m_startPosition=" + m_startPosition
		      + ", m_endPosition=" + m_endPosition + "]";
	}

}
