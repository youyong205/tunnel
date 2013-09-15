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

	protected static final int SIZE = 20;

	public static final int s_half_size = 3;

	@Autowired
	protected LogService m_logService;

	protected Map<String, Object> m_session;

	private List<String> m_modules = new ArrayList<String>(Arrays.asList(Modules.s_user_model, Modules.s_role_model,
	      Modules.s_resource_model, Modules.s_log_module, Modules.s_document_model, Modules.s_tunnel_model,
	      
	      Modules.s_constructionUnit_model, Modules.s_liningRing_model, Modules.s_tunnelSection_model,

	      Modules.s_contactChannel_model, Modules.s_contactChannel_inspection_model, Modules.s_contactChannel_curing_model, //
	      
	      Modules.s_buriedSection_model, Modules.s_buriedSection_inspection_model,Modules.s_buriedSection_curing_model, //
	     
	      Modules.s_openSection_model, Modules.s_openSection_inspection_model, Modules.s_openSection_curing_model, //
	      
	      Modules.s_workingWell_model, Modules.s_workingWell_inspection_model, Modules.s_workingWell_curing_model, //
	     
	      Modules.s_rectangleComponent_model, Modules.s_rectangleComponent_inspection_model, Modules.s_rectangleComponent_curing_model, //
	      
	      Modules.s_plank_model, Modules.s_plank_inspection_model, Modules.s_plank_curing_model, //
	      
	      Modules.s_bracket_model, Modules.s_bracket_inspection_model, Modules.s_bracket_curing_model, //
	      
	      Modules.s_saddleWeight_model, Modules.s_saddleWeight_inspection_model, Modules.s_saddleWeight_curing_model, //
	      
	      Modules.s_flueSheet_model, Modules.s_flueSheet_inspection_model, Modules.s_flueSheet_curing_model,//
	      
	      Modules.s_pumpingStation_model, Modules.s_pumpingStation_inspection_model, Modules.s_pumpingStation_curing_model, //
	      
	      Modules.s_escape_model, Modules.s_escape_inspection_model, Modules.s_escape_curing_model, //
	      
	      Modules.s_linePipe_model, Modules.s_linePipe_inspection_model, Modules.s_linePipe_curing_model, //
	      
	      Modules.s_facility_model, Modules.s_facility_inspection_model, Modules.s_facility_curing_model,//
	      
			Modules.s_liningRingConstruction_model , Modules.s_liningRingDeformation_model));

	private List<String> m_documentModules = new ArrayList<String>(Arrays.asList(
	      Modules.s_contactChannel_model,  Modules.s_contactChannel_curing_model, //
	      
	      Modules.s_buriedSection_model,Modules.s_buriedSection_curing_model, //
	      
	      Modules.s_openSection_model, Modules.s_openSection_curing_model, //
	      
	      Modules.s_workingWell_model,  Modules.s_workingWell_curing_model, //
	     
	      Modules.s_rectangleComponent_model,  Modules.s_rectangleComponent_curing_model, //
	      
	      Modules.s_plank_model, Modules.s_plank_curing_model, //
	      
	      Modules.s_bracket_model,  Modules.s_bracket_curing_model, //
	      
	      Modules.s_saddleWeight_model,Modules.s_saddleWeight_curing_model, //
	      
	      Modules.s_flueSheet_model,Modules.s_flueSheet_curing_model,//
	      
	      Modules.s_pumpingStation_model,  Modules.s_pumpingStation_curing_model, //
	      
	      Modules.s_escape_model, Modules.s_escape_curing_model, //
	      
	      Modules.s_linePipe_model, Modules.s_linePipe_curing_model, //
	      
	      Modules.s_facility_model, Modules.s_facility_curing_model));

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

		User user = queryUserInfo();
		if (user != null) {
			log.setUserId(user.getId());
		}
		return log;
	}

	public User queryUserInfo() {
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

	public String buildResource(String module, String oper) {
		return module + ":" + oper;
	}

	public Authority checkAuthority(String resources) {
		User user = queryUserInfo();

		if (user == null) {
			return Authority.Login;
		} else {
			if (!user.getResources().containsKey(resources)) {
				return Authority.NoAuth;
			} else {
				return null;
			}
		}
	}

}
