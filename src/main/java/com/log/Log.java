package com.log;

import java.util.Date;

import com.user.User;

public class Log {

	private int m_id;

	private String m_module;

	private String m_operation;

	private int m_userId;

	private String m_detail;

	private Date m_creationDate;
	
	private User m_user;

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

	public String getOperation() {
   	return m_operation;
   }

	public void setOperation(String operation) {
   	m_operation = operation;
   }

	public int getUserId() {
   	return m_userId;
   }

	public void setUserId(int userId) {
   	m_userId = userId;
   }

	public String getDetail() {
   	return m_detail;
   }

	public void setDetail(String detail) {
   	m_detail = detail;
   }

	public Date getCreationDate() {
   	return m_creationDate;
   }

	public void setCreationDate(Date creationDate) {
   	m_creationDate = creationDate;
   }

	public User getUser() {
   	return m_user;
   }

	public void setUser(User user) {
   	m_user = user;
   }

	@Override
   public String toString() {
	   return "Log [m_id=" + m_id + ", m_module=" + m_module + ", m_operation=" + m_operation + ", m_userId=" + m_userId
	         + ", m_detail=" + m_detail + ", m_creationDate=" + m_creationDate + "]";
   }
	
}
