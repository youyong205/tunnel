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

	public String getType() {
		return m_type;
	}

	public void setType(String type) {
		m_type = type;
	}

	public String getPath() {
		return m_path;
	}

	public void setPath(String path) {
		m_path = path;
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

	public String getDes() {
   	return m_des;
   }

	public void setDes(String des) {
   	m_des = des;
   }

	public String getAbsolutePath() {
   	return m_absolutePath;
   }

	public void setAbsolutePath(String absolutePath) {
   	m_absolutePath = absolutePath;
   }

	@Override
   public String toString() {
	   return "Document [m_id=" + m_id + ", m_module=" + m_module + ", m_name=" + m_name + ", m_type=" + m_type
	         + ", m_path=" + m_path + ", m_absolutePath=" + m_absolutePath + ", m_des=" + m_des + "]";
   }

}
