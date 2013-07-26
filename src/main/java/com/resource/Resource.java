package com.resource;

import java.util.Date;

public class Resource {

	private int m_id;

	private String m_module;
	
	private String m_name;
	
	private String m_des;
	
	private Date m_creationDate;
	
	private Date m_modifyDate;

	public int getId() {
   	return m_id;
   }

	public void setId(int id) {
   	m_id = id;
   }
	
	public String getModule() {
   	return m_module;
   }

	public void setModule(String module) {
   	m_module = module;
   }

	public String getName() {
   	return m_name;
   }

	public void setName(String name) {
   	m_name = name;
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
	
}
