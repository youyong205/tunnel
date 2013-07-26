package com.buriedSection;

import java.util.Date;

import com.document.Document;
import com.tunnel.Tunnel;


public class BuriedSection {
	private int m_id;

	private String m_name;

	private String m_type;

	private int m_tunnelId;

	private Tunnel m_tunnel;

	private int m_documentId;

	private Document m_document;

	private double m_startPosition;

	private double m_endPosition;

	private String m_envelope;

	private double m_startFloor;

	private double m_endFloor;

	private double m_linedWallThickness;

	private double m_floorThickness;

	private double m_roofThickness;

	private double m_startHeadroom;

	private double m_endHeadroom;

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

	public String getEnvelope() {
   	return m_envelope;
   }

	public void setEnvelope(String envelope) {
   	m_envelope = envelope;
   }

	public double getStartFloor() {
   	return m_startFloor;
   }

	public void setStartFloor(double startFloor) {
   	m_startFloor = startFloor;
   }

	public double getEndFloor() {
   	return m_endFloor;
   }

	public void setEndFloor(double endFloor) {
   	m_endFloor = endFloor;
   }

	public double getLinedWallThickness() {
   	return m_linedWallThickness;
   }

	public void setLinedWallThickness(double linedWallThickness) {
   	m_linedWallThickness = linedWallThickness;
   }

	public double getFloorThickness() {
   	return m_floorThickness;
   }

	public void setFloorThickness(double floorThickness) {
   	m_floorThickness = floorThickness;
   }

	public double getRoofThickness() {
   	return m_roofThickness;
   }

	public void setRoofThickness(double roofThickness) {
   	m_roofThickness = roofThickness;
   }

	public double getStartHeadroom() {
   	return m_startHeadroom;
   }

	public void setStartHeadroom(double startHeadroom) {
   	m_startHeadroom = startHeadroom;
   }

	public double getEndHeadroom() {
   	return m_endHeadroom;
   }

	public void setEndHeadroom(double endHeadroom) {
   	m_endHeadroom = endHeadroom;
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
	   return "BuriedSection [m_id=" + m_id + ", m_name=" + m_name + ", m_type=" + m_type + ", m_tunnelId=" + m_tunnelId
	         + ", m_documentId=" + m_documentId + ", m_startPosition=" + m_startPosition + ", m_endPosition="
	         + m_endPosition + ", m_envelope=" + m_envelope + ", m_startFloor=" + m_startFloor + ", m_endFloor="
	         + m_endFloor + ", m_linedWallThickness=" + m_linedWallThickness + ", m_floorThickness=" + m_floorThickness
	         + ", m_roofThickness=" + m_roofThickness + ", m_startHeadroom=" + m_startHeadroom + ", m_endHeadroom="
	         + m_endHeadroom + ", m_des=" + m_des + "]";
   }

}
