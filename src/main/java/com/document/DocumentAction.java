package com.document;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.Modules;
import com.PagedAction;

public class DocumentAction extends PagedAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(DocumentAction.class);

	private List<Document> m_documents;

	private int m_documentId;

	private DocumentService m_documentService;

	private Document m_document = new Document();

	private String m_module;

	private String m_name;

	private InputStream m_inputStream;

	public static String s_file = "默认文件名";

	public static String s_context_type = "text/plain";


	public String documentDownload() {
		m_document = m_documentService.findByPK(m_documentId);
		if (m_document != null) {
			try {
				String path = m_document.getPath();
				String absolutePath = m_document.getAbsolutePath();
				File file = new File(absolutePath);

				if (!file.exists()) {
					m_inputStream = ServletActionContext.getServletContext().getResourceAsStream(path);
				} else {
					m_inputStream = new FileInputStream(file);
				}
			} catch (Exception e) {
				m_inputStream = new ByteArrayInputStream("Struts 2 下载示例".getBytes());
			}
		}
		return SUCCESS;
	}

	public String documentList() {
		try {
			m_totalSize = m_documentService.querySizeByModuleName(m_module, m_name);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_documents = m_documentService.queryDocumentsByModuleName(m_module, m_name, start, SIZE);
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String documentUpdate() {
		try {
			m_document = m_documentService.findByPK(m_documentId);
			if (m_document != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	@Override
   public String getActionModule() {
		return Modules.s_document_model;
   }

	public String getContextType() {
		if (m_document != null) {
			return m_document.getType();
		}
		return s_context_type;
	}

	public Document getDocument() {
		return m_document;
	}

	public List<Document> getDocuments() {
		return m_documents;
	}

	public String getDownloadFileName() {
		if (m_document != null) {
			String name = "";
			try {
				name = URLEncoder.encode(m_document.getName(), "UTF-8");
				name = StringUtils.replace(name, "+", "%20");
				if (name.length() > 150) {
					name = new String(m_document.getName().getBytes("GB2312"), "ISO8859-1");
					name = StringUtils.replace(name, " ", "%20");
				}
			} catch (UnsupportedEncodingException e) {
				m_logger.error(e.getMessage(), e);
			}
			return name;
		}
		return s_file;
	}

	public InputStream getInputStream() throws Exception {
		return m_inputStream;
	}

	public String getModule() {
		return m_module;
	}

	public String getName() {
		return m_name;
	}

	public void setDocument(Document document) {
		m_document = document;
	}

	public void setDocumentId(int documentId) {
		m_documentId = documentId;
	}

	public void setDocumentService(DocumentService documentService) {
		m_documentService = documentService;
	}

	public void setModule(String module) {
		m_module = module;
	}
	
	public void setName(String name) {
		m_name = name;
	}
}