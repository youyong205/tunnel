package com;

import java.io.File;

import com.document.DocumentService;
import com.document.UploadFile;

public abstract class FileUploadAction extends PagedAction {

	private static final long serialVersionUID = -628529076216846486L;

	protected UploadFile m_uploadFile = new UploadFile();

	protected DocumentService m_documentService;

	public void setUpload(File file) {
		m_uploadFile.setFile(file);
	}

	public void setUploadContentType(String contentType) {
		m_uploadFile.setContentType(contentType);
	}

	public void setUploadFileName(String filename) {
		m_uploadFile.setFilename(filename);
	}

	public void setDocumentService(DocumentService documentService) {
		m_documentService = documentService;
	}
}
