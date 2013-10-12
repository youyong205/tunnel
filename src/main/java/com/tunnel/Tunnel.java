package com.tunnel;

import java.util.Date;

public class Tunnel {

	private int m_id;

	private String m_name;

	private String m_type;

	private String m_state;

	private String m_email;

	private String m_des;

	private Date m_creationDate;

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

	public String getState() {
		return m_state;
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

	public void setId(int id) {
		m_id = id;
	}

	public void setName(String name) {
		m_name = name;
	}

	public void setState(String state) {
		m_state = state;
	}

	public void setType(String type) {
		m_type = type;
	}

	public String getEmail() {
   	return m_email;
   }

	public void setEmail(String mail) {
   	m_email = mail;
   }

	@Override
	public String toString() {
		return "Tunnel [m_id=" + m_id + ", m_name=" + m_name + ", m_des=" + m_des + "]";
	}

}
