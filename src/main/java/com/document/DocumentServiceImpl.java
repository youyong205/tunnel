package com.document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.log4j.Logger;

import com.Files;

public class DocumentServiceImpl implements DocumentService {

	private String m_baseDocumentPath;

	private DocumentDao m_documentDao;

	private Logger m_logger = Logger.getLogger(DocumentServiceImpl.class);

	private boolean copyFile(String outFileName, File file) {
		try {
			File out = new File(outFileName);
			if (!out.getParentFile().exists()) {
				out.getParentFile().mkdirs();
			}
			out.createNewFile();
			FileOutputStream os = new FileOutputStream(out);
			FileInputStream is = new FileInputStream(file);
			Files.forIO().copy(is, os);

			return true;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public Document findByPK(int id) {
		try {
			return m_documentDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	public String getBaseDocumentPath() {
		return m_baseDocumentPath;
	}

	private int insertDocument(Document document) {
		try {
			return m_documentDao.insertDocument(document);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public int insertDocument(String module, UploadFile uploadFile) {
		try {
			long time = System.currentTimeMillis();
			String outPath = m_baseDocumentPath + module + '/' + time + '_' + uploadFile.getFilename();
			boolean copyResult = copyFile(outPath, uploadFile.getFile());

			if (copyResult) {
				Document document = new Document();

				document.setModule(module);
				document.setPath(outPath);
				document.setAbsolutePath(outPath);
				document.setName(uploadFile.getFilename());
				document.setType(uploadFile.getContentType());
				return insertDocument(document);
			} else {
				return 0;
			}
		} catch (Exception e) {
			m_logger.error(e);
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Document> queryDocumentsByModuleName(String module, String name, int start, int size) {
		return m_documentDao.queryLimitedDocumentsByModuleName(module, name, start, size);
	}

	@Override
	public int querySizeByModuleName(String module, String name) {
		return m_documentDao.querySizeByModuleOperationName(module, name);
	}

	public void setBaseDocumentPath(String baseDocumentPath) {
		m_baseDocumentPath = baseDocumentPath;
	}

	public void setDocumentDao(DocumentDao documentDao) {
		m_documentDao = documentDao;
	}

	private int updateDocument(Document document) {
		try {
			return m_documentDao.updateDocument(document);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
   public int updateDocument(String module, UploadFile uploadFile, Document document) {
		try {
			long time = System.currentTimeMillis();
			String outPath = m_baseDocumentPath + module + '/' + time + '_' + uploadFile.getFilename();
			boolean copyResult = copyFile(outPath, uploadFile.getFile());

			if (copyResult) {
				document.setModule(module);
				document.setPath(outPath);
				document.setAbsolutePath(outPath);
				document.setName(uploadFile.getFilename());
				document.setType(uploadFile.getContentType());
				return updateDocument(document);
			} else {
				return 0;
			}
		} catch (Exception e) {
			m_logger.error(e);
		}
		return 0;
   }
}
