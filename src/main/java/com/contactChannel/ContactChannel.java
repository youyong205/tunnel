package com.contactChannel;

import java.util.Date;

import com.document.Document;
import com.tunnel.Tunnel;

public class ContactChannel {
	private int m_id;

	private String m_name;

	private String m_type;

	private int m_tunnelId;

	private Tunnel m_tunnel;

	private int m_documentId;

	private Document m_document;

	private double m_upLine;

	private double m_downLine;

	private double m_upCenter;

	private double m_downCenter;

	private int m_soilOrder;

	private String m_des;

	private Date m_creationDate;

	private Date m_modifyDate;

	public int getId() {
		return m_id;
	}

	public void setId(int id) {
		m_id = id;
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

	public double getUpLine() {
		return m_upLine;
	}

	public void setUpLine(double upLine) {
		m_upLine = upLine;
	}

	public double getDownLine() {
		return m_downLine;
	}

	public void setDownLine(double downLine) {
		m_downLine = downLine;
	}

	public double getUpCenter() {
		return m_upCenter;
	}

	public void setUpCenter(double upCenter) {
		m_upCenter = upCenter;
	}

	public double getDownCenter() {
		return m_downCenter;
	}

	public void setDownCenter(double downCenter) {
		m_downCenter = downCenter;
	}

	public int getSoilOrder() {
		return m_soilOrder;
	}

	public void setSoilOrder(int soilOrder) {
		m_soilOrder = soilOrder;
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

	@Override
   public String toString() {
	   return "ContactChannel [m_id=" + m_id + ", m_name=" + m_name + ", m_type=" + m_type + ", m_tunnelId="
	         + m_tunnelId + ", m_documentId=" + m_documentId + ", m_upLine=" + m_upLine + ", m_downLine=" + m_downLine
	         + ", m_upCenter=" + m_upCenter + ", m_downCenter=" + m_downCenter + ", m_soilOrder=" + m_soilOrder
	         + ", m_des=" + m_des + "]";
   }
	
}
