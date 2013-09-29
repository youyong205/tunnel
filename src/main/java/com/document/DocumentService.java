package com.document;

import java.util.List;

public interface DocumentService {

	public int deleteDocument(int id);

	public Document findByPK(int id);

	public int insertDocument(String module, UploadFile file);

	/***
	 * 
	 * @param module
	 *           null for all module
	 * @param name
	 *           null for all
	 * @return
	 */
	public List<Document> queryDocumentsByModuleName(String module, String name, int start, int size);

	/***
	 * 
	 * @param module
	 *           null for all module
	 * @param name
	 *           null for all
	 * @return
	 */
	public int querySizeByModuleName(String module, String name);

	public int updateDocument(String module, UploadFile file, Document document);

}
