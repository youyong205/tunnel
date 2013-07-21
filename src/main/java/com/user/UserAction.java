package com.user;

import java.util.List;

import org.apache.log4j.Logger;

import com.Constrants;
import com.PagedAction;
import com.log.Log;

public class UserAction extends PagedAction {

   private static final long serialVersionUID = 4901652667413788534L;

	private Logger m_logger = Logger.getLogger(UserAction.class);

	private List<User> m_users;

	private int m_userId;

	private UserService m_userService;
	
	private User m_user = new User();

	public User getUser() {
		return m_user;
	}

	public List<User> getUsers() {
		return m_users;
	}

	public void setUser(User user) {
		m_user = user;
	}

	public void setUserId(int userId) {
		m_userId = userId;
	}

	public void setUserService(UserService userService) {
		m_userService = userService;
	}
	
	public String userAdd(){
		return SUCCESS;
	}

	public String userAddSubmit() {
		try {
			int id = m_userService.insertUser(m_user);
			if (id > 0) {
				Log log = createLog(Constrants.s_user_model, Constrants.s_operation_add, m_user);
				
				m_logService.insertLog(log);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String userDelete() {
		try {
			int count = m_userService.deleteUser(m_userId);
			if (count > 0) {
				Log log = createLog(Constrants.s_user_model, Constrants.s_operation_delete, m_userId);
				
				m_logService.insertLog(log);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String userList() {
		try {
			m_totalSize = m_userService.queryAllSize();
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_users = m_userService.queryLimitedUsers(start, SIZE);
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String userUpdate() {
		try {
			m_user = m_userService.findByPK(m_userId);
			if (m_user != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String userUpdateSubmit() {
		try {
			int count = m_userService.updateUser(m_user);
			if (count > 0) {
				Log log = createLog(Constrants.s_user_model, Constrants.s_operation_update, m_user);
				
				m_logService.insertLog(log);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

}