package com.constructionUnit;

import java.util.Date;

public class ConstructionUnit {

	private int m_id;

	private String m_name;

	private String m_type;

	private String m_address;

	private String m_workers;

	private String m_phone;

	private String m_des;

	private Date m_creationDate;

	private Date m_modifyDate;

	public String getAddress() {
		return m_address;
	}

	public Date getCreationDate() {
		return m_creationDate;
	}

	public String getDes() {
		return m_des;
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

	public String getPhone() {
		return m_phone;
	}

	public String getType() {
		return m_type;
	}

	public String getWorkers() {
		return m_workers;
	}

	public void setAddress(String address) {
		m_address = address;
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

	public void setModifyDate(Date modifyDate) {
		m_modifyDate = modifyDate;
	}

	public void setName(String name) {
		m_name = name;
	}

	public void setPhone(String phone) {
		m_phone = phone;
	}

	public void setType(String type) {
		m_type = type;
	}

	public void setWorkers(String workers) {
		m_workers = workers;
	}

	@Override
	public String toString() {
		return "ConstructionUnit [m_id=" + m_id + ", m_name=" + m_name + ", m_type=" + m_type + ", m_address="
		      + m_address + ", m_workers=" + m_workers + ", m_phone=" + m_phone + ", m_des=" + m_des + "]";
	}

}
