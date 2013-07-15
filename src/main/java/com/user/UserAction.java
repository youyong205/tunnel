package com.user;

import java.util.List;

import org.apache.log4j.Logger;

import com.PagedAction;

public class UserAction extends PagedAction {

	private static final long serialVersionUID = 2801256599554299998L;

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

	public String userAddSubmit() {
		try {
			int id = m_userService.insertUser(m_user);
			if (id > 0) {
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
			int totalSize = m_userService.queryAllSize();
			if (totalSize > 0) {
				m_totalPages = (int) Math.floor(totalSize * 1.0 / SIZE);
			}
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