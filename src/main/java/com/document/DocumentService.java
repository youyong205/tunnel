package com.document;

import java.util.List;

public interface DocumentService {

	public Document findByPK(int id);

	public int insertDocument(String module, UploadFile file);

	public int updateDocument(String module, UploadFile file,Document document);

	/***
	 * 
	 * @param module
	 *           null for all module
	 * @param name
	 *           null for all
	 * @return
	 */
	public int querySizeByModuleName(String module, String name);

	/***
	 * 
	 * @param module
	 *           null for all module
	 * @param name
	 *           null for all
	 * @return
	 */
	public List<Document> queryDocumentsByModuleName(String module, String name, int start, int size);

}
