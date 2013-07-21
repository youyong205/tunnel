package com.tunnelSection;

import java.util.Date;

import com.tunnel.Tunnel;

public class TunnelSection {

	private int m_id;

	private String m_name;
	
	private String m_type;
	
	private int m_tunnelId;
	
	private Tunnel m_tunnel;
	
	private String m_des;
	
	private Date m_creationDate;
	
	private Date m_modifyDate;

	public Date getCreationDate() {
   	return m_creationDate;
   }

	public String getDes() {
   	return m_des;
   }

	public int getId() {
   	return m_id;
   }

	public String getName() {
   	return m_name;
   }

	public void setCreationDate(Date creationDate) {
   	m_creationDate = creationDate;
   }

	public void setDes(String des) {
   	m_des = des;
   }

	public void setId(int id) {
   	m_id = id;
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

	public Date getModifyDate() {
   	return m_modifyDate;
   }

	public void setModifyDate(Date modifyDate) {
   	m_modifyDate = modifyDate;
   }

	public Tunnel getTunnel() {
   	return m_tunnel;
   }

	public void setTunnel(Tunnel tunnel) {
   	m_tunnel = tunnel;
   }

	@Override
   public String toString() {
	   return "TunnelSection [m_id=" + m_id + ", m_name=" + m_name + ", m_type=" + m_type + ", m_tunnelId=" + m_tunnelId
	         + ", m_des=" + m_des + "]";
   }

}
