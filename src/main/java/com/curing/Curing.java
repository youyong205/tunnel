package com.curing;

import java.util.Date;

import com.document.Document;
import com.tunnel.Tunnel;
import com.tunnelSection.TunnelSection;

public class Curing {

	private int m_id;

	private String m_type;

	private int m_componentId;

	private String m_componentName;

	private int m_constructionUnitId;

	private int m_tunnelId;

	private Tunnel m_tunnel;

	private int m_tunnelSectionId;

	private TunnelSection m_tunnelSection;

	private int m_documentId;

	private Document m_document;

	private Date m_time;

	private String m_position;

	private String m_action;

	private String m_des;

	private Date m_creationDate;

	private Date m_modifyDate;

	public String getAction() {
		return m_action;
	}

	public int getComponentId() {
		return m_componentId;
	}

	public String getComponentName() {
		return m_componentName;
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

	public Document getDocument() {
		return m_document;
	}

	public int getDocumentId() {
		return m_documentId;
	}

	public int getId() {
		return m_id;
	}

	public Date getModifyDate() {
		return m_modifyDate;
	}

	public String getPosition() {
		return m_position;
	}

	public Date getTime() {
		return m_time;
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

	public void setAction(String action) {
		m_action = action;
	}

	public void setComponentId(int componentId) {
		m_componentId = componentId;
	}

	public void setComponentName(String componentName) {
		m_componentName = componentName;
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

	public void setDocument(Document document) {
		m_document = document;
	}

	public void setDocumentId(int documentId) {
		m_documentId = documentId;
	}

	public void setId(int id) {
		m_id = id;
	}

	public void setModifyDate(Date modifyDate) {
		m_modifyDate = modifyDate;
	}

	public void setPosition(String position) {
		m_position = position;
	}

	public void setTime(Date time) {
		m_time = time;
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
		return "Curing [m_id=" + m_id + ", m_type=" + m_type + ", m_componentId=" + m_componentId + ", m_componentName="
		      + m_componentName + ", m_constructionUnitId=" + m_constructionUnitId + ", m_tunnelId=" + m_tunnelId
		      + ", m_documentId=" + m_documentId + ", m_time=" + m_time + ", m_position=" + m_position + ", m_action="
		      + m_action + ", m_des=" + m_des + "]";
	}

}
