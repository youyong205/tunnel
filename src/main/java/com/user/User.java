package com.user;

import java.util.Date;

public class User {

	private int m_id;

	private String m_userName;

	private String m_password;

	private String m_realName;

	private int m_role;

	private Date m_creationDate;

	private Date m_modifyDate;

	public static final int ADMIN = 1;

	public static final int OPERATOR = 2;

	public static final int ORDINARY = 3;

	public int getId() {
		return m_id;
	}

	public void setId(int id) {
		m_id = id;
	}

	public String getUserName() {
		return m_userName;
	}

	public void setUserName(String userName) {
		m_userName = userName;
	}

	public String getPassword() {
		return m_password;
	}

	public void setPassword(String password) {
		m_password = password;
	}

	public String getRealName() {
		return m_realName;
	}

	public void setRealName(String realName) {
		m_realName = realName;
	}

	public int getRole() {
		return m_role;
	}

	public void setRole(int role) {
		m_role = role;
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
