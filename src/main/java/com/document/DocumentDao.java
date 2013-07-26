package com.document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.BaseDao;

public class DocumentDao {

	private BaseDao m_baseDao;

	public int deleteDocument(int id) {
		return m_baseDao.delete("document.delete", id);
	}

	public Document findByPK(int id) {
		return (Document) m_baseDao.queryForObject("document.findById", id);
	}

	public int insertDocument(Document document) {
		return (Integer) m_baseDao.insert("document.insert", document);
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedDocumentsByModuleName(String module, String name, int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("start", start);
		parameters.put("size", size);

		if (StringUtils.isNotEmpty(module)) {
			parameters.put("module", module);
		}
		if (StringUtils.isNotEmpty(name)) {
			parameters.put("name", name);
		}
		return m_baseDao.queryForList("document.queryLimitedDocumentsByModuleName", parameters);
	}

	public int querySizeByModuleOperationName(String module, String name) {
		Map<String, String> parameters = new HashMap<String, String>();

		if (StringUtils.isNotEmpty(module)) {
			parameters.put("module", module);
		}
		if (StringUtils.isNotEmpty(name)) {
			parameters.put("name", name);
		}
		return (Integer) m_baseDao.queryForObject("document.querySizeByModuleName", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateDocument(Document document) {
		return m_baseDao.update("document.update", document);
	}
}
