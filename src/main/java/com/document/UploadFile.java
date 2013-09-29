package com.document;

import java.io.File;
import java.io.Serializable;

public class UploadFile implements Serializable {

	private static final long serialVersionUID = -1555299968770401677L;

	private String m_filename;

	private String m_path;

	private String m_contentType;

	public File m_file;

	public String getContentType() {
		return m_contentType;
	}

	public File getFile() {
		return m_file;
	}

	public String getFilename() {
		return m_filename;
	}

	public String getPath() {
		return m_path;
	}

	public void setContentType(String contentType) {
		this.m_contentType = contentType;
	}

	public void setFile(File file) {
		m_file = file;
	}

	public void setFilename(String filename) {
		this.m_filename = filename;
	}

	public void setPath(String path) {
		m_path = path;
	}

}
