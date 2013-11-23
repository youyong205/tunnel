package com.tunnelGraph;

import java.util.Date;

import com.tunnel.Tunnel;

public class TunnelGraph {
	private int m_id;

	private int m_tunnelId;

	private Tunnel m_tunnel;

	private String m_lineType;

	private double m_indexNumber;

	private int m_componentType;

	private int m_componentId;

	private Date m_creationDate;
	
	private String m_url;

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

	public String getLineType() {
		return m_lineType;
	}

	public void setLineType(String lineType) {
		m_lineType = lineType;
	}

	public double getIndexNumber() {
		return m_indexNumber;
	}

	public void setIndexNumber(double indexNumber) {
		m_indexNumber = indexNumber;
	}

	public int getComponentType() {
		return m_componentType;
	}

	public void setComponentType(int componentType) {
		m_componentType = componentType;
	}

	public int getComponentId() {
		return m_componentId;
	}

	public void setComponentId(int componentId) {
		m_componentId = componentId;
	}

	public Date getCreationDate() {
		return m_creationDate;
	}

	public void setCreationDate(Date creationDate) {
		m_creationDate = creationDate;
	}

	public String getUrl() {
   	return m_url;
   }

	public void setUrl(String url) {
   	m_url = url;
   }
	
}
