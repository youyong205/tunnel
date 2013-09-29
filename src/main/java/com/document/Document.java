package com.document;

import java.util.Date;

public class Document {
	private int m_id;

	private String m_module;

	private String m_name;

	private String m_type;

	private String m_path;

	private String m_absolutePath;

	private String m_des;

	private Date m_creationDate;

	private Date m_modifyDate;

	public String getAbsolutePath() {
		return m_absolutePath;
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

	public String getModule() {
		return m_module;
	}

	public String getName() {
		return m_name;
	}

	public String getPath() {
		return m_path;
	}

	public String getType() {
		return m_type;
	}

	public void setAbsolutePath(String absolutePath) {
		m_absolutePath = absolutePath;
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

	public void setModule(String module) {
		m_module = module;
	}

	public void setName(String name) {
		m_name = name;
	}

	public void setPath(String path) {
		m_path = path;
	}

	public void setType(String type) {
		m_type = type;
	}

	@Override
	public String toString() {
		return "Document [m_id=" + m_id + ", m_module=" + m_module + ", m_name=" + m_name + ", m_type=" + m_type
		      + ", m_path=" + m_path + ", m_absolutePath=" + m_absolutePath + ", m_des=" + m_des + "]";
	}

}
