package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.log.Log;
import com.log.LogService;
import com.opensymphony.xwork2.ActionSupport;
import com.user.User;

public class PagedAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -5988620023728972001L;

	protected int m_totalPages;
	
	protected int m_totalSize;

	protected int m_index = 1;

	protected static final int SIZE = Constrants.s_page_size;

	protected LogService m_logService;

	protected Map<String, Object> m_session;
	
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

	public void setLogService(LogService logService) {
		m_logService = logService;
	}

	public int getIndex() {
		return m_index;
	}

	public List<Integer> getPageIndexs() {
		List<Integer> indexs = new ArrayList<Integer>();
		int start = m_index - Constrants.s_half_size - 1;
		int end = m_index + Constrants.s_half_size;

		if (start < 1) {
			start = 1;
			end = 2 + 2 * Constrants.s_half_size;
		} else if (end > m_totalPages) {
			end = m_totalPages;
			start = m_totalPages - Constrants.s_half_size * 2 - 1;
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

	public void setIndex(int index) {
		m_index = index;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		m_session = session;
	}

	public int getTotalSize() {
   	return m_totalSize;
   }
	
}
