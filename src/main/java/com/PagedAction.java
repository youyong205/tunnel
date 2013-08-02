package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.log.Log;
import com.log.LogService;
import com.opensymphony.xwork2.ActionSupport;
import com.user.User;

public abstract class PagedAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -5988620023728972001L;

	protected int m_totalPages;

	protected int m_totalSize;

	protected int m_index = 1;

	protected static final int SIZE = 1;

	public static final int s_half_size = 3;

	@Autowired
	protected LogService m_logService;

	protected Map<String, Object> m_session;

	private List<String> m_modules = new ArrayList<String>(Arrays.asList(Constrants.s_user_model,
	      Constrants.s_role_model, Constrants.s_resource_model, Constrants.s_log_module, Constrants.s_document_model,
	      Constrants.s_tunnel_model, Constrants.s_constructionUnit_model, Constrants.s_liningRing_model,
	      Constrants.s_tunnelSection_model, 
	      
	      Constrants.s_contactChannel_model,Constrants.s_contactChannel_inspection_model,Constrants.s_contactChannel_curing_model, //
	      Constrants.s_buriedSection_model, Constrants.s_buriedSection_inspection_model,Constrants.s_buriedSection_curing_model, //
	      Constrants.s_openSection_model, Constrants.s_openSection_inspection_model,Constrants.s_openSection_curing_model, //
	      Constrants.s_workingWell_model, Constrants.s_workingWell_inspection_model,Constrants.s_workingWell_curing_model, //
	      Constrants.s_rectangleComponent_model,Constrants.s_rectangleComponent_inspection_model ));

	private List<String> m_documentModules = new ArrayList<String>(Arrays.asList(Constrants.s_contactChannel_model));

	public int computeTotalPages(int totalSize) {
		return (int) Math.ceil(totalSize * 1.0 / SIZE);
	}

	public int computeTotalPages(int totalSize, int pageSize) {
		return (int) Math.ceil(totalSize * 1.0 / pageSize);
	}

	public Log createLog(String module, String operation, Object content) {
		Log log = new Log();

		log.setModule(module);
		log.setOperation(operation);
		log.setDetail(content.toString());

		User user = findUser();
		if (user != null) {
			log.setUserId(user.getId());
		}
		return log;
	}

	public User findUser() {
		Object object = m_session.get("user");
		if (object != null) {
			return (User) object;
		} else {
			return null;
		}
	}

	public abstract String getActionModule();

	public List<String> getDocumentModules() {
		return m_documentModules;
	}

	public int getIndex() {
		return m_index;
	}

	public List<String> getModules() {
		return m_modules;
	}

	public List<Integer> getPageIndexs() {
		List<Integer> indexs = new ArrayList<Integer>();
		int start = m_index - s_half_size - 1;
		int end = m_index + s_half_size;

		if (start < 1) {
			start = 1;
			end = 2 + 2 * s_half_size;
		} else if (end > m_totalPages) {
			end = m_totalPages;
			start = m_totalPages - s_half_size * 2 - 1;
		}

		for (; start <= end; start++) {
			if (start >= 1 && start <= m_totalPages) {
				indexs.add(start);
			}
		}
		return indexs;
	}

	public int getTotalPages() {
		return m_totalPages;
	}

	public int getTotalSize() {
		return m_totalSize;
	}

	public void setIndex(int index) {
		m_index = index;
	}

	public void setLogService(LogService logService) {
		m_logService = logService;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		m_session = session;
	}

}
