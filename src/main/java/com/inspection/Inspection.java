package com.inspection;

import java.util.Date;

import com.tunnel.Tunnel;

public class Inspection {

	private int m_id;

	private String m_type;
	
	private int m_componentId;
	
	private String m_componentName;
	
	private int m_constructionUnitId;
	
	private int m_tunnelId;
	
	private Tunnel m_tunnel;
	
	private Date m_time;
	
	private String m_description;
	
	private String m_action;
	
	private Date m_actionTime;

	private String m_des;
	
	private Date m_creationDate;
	
	private Date m_modifyDate;

	public String getAction() {
   	return m_action;
   }

	public Date getActionTime() {
   	return m_actionTime;
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

	public String getDescription() {
   	return m_description;
   }

	public int getId() {
   	return m_id;
   }

	public Date getModifyDate() {
   	return m_modifyDate;
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

	public String getType() {
   	return m_type;
   }

	public void setAction(String action) {
   	m_action = action;
   }

	public void setActionTime(Date actionTime) {
   	m_actionTime = actionTime;
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

	public void setDescription(String description) {
   	m_description = description;
   }
	
	public void setId(int id) {
   	m_id = id;
   }

	public void setModifyDate(Date modifyDate) {
   	m_modifyDate = modifyDate;
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

	public void setType(String type) {
   	m_type = type;
   }

	@Override
   public String toString() {
	   return "Inspection [m_id=" + m_id + ", m_type=" + m_type + ", m_componentId=" + m_componentId
	         + ", m_constructionUnitId=" + m_constructionUnitId + ", m_time=" + m_time + ", m_description="
	         + m_description + ", m_action=" + m_action + ", m_actionTime=" + m_actionTime + ", m_des=" + m_des + "]";
   }
	
}
