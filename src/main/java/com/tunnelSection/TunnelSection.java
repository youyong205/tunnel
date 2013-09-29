package com.tunnelSection;

import java.util.Date;

import com.tunnel.Tunnel;

public class TunnelSection {

	private int m_id;

	private String m_name;

	private String m_type;

	private int m_tunnelId;

	private Tunnel m_tunnel;

	private String m_environment;

	private double m_externalDiameter;

	private String m_state;

	private String m_des;

	private Date m_creationDate;

	private Date m_modifyDate;

	public Date getCreationDate() {
		return m_creationDate;
	}

	public String getDes() {
		return m_des;
	}

	public String getEnvironment() {
		return m_environment;
	}

	public double getExternalDiameter() {
		return m_externalDiameter;
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

	public String getState() {
		return m_state;
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

	public void setEnvironment(String environment) {
		m_environment = environment;
	}

	public void setExternalDiameter(double externalDiameter) {
		m_externalDiameter = externalDiameter;
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

	public void setState(String state) {
		m_state = state;
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
		return "TunnelSection [m_id=" + m_id + ", m_name=" + m_name + ", m_type=" + m_type + ", m_tunnelId=" + m_tunnelId
		      + ", m_environment=" + m_environment + ", m_externalDiameter=" + m_externalDiameter + ", m_des=" + m_des
		      + ", m_creationDate=" + m_creationDate + ", m_modifyDate=" + m_modifyDate + "]";
	}

}
