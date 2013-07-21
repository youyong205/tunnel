package com.login;

import org.apache.log4j.Logger;

import com.Constrants;
import com.PagedAction;
import com.user.User;
import com.user.UserService;

public class LoginAction extends PagedAction {

	private static final long serialVersionUID = 362224844795605039L;

	private Logger m_logger = Logger.getLogger(LoginAction.class);

	private String m_userName;

	private String m_password;

	private UserService m_userService;

	private static final String USER = "user";

	public String login() {
		User user = m_userService.findByNamePassword(m_userName, m_password);

		if (user != null) {
			m_session.put(USER, user);
			m_logger.info(String.format("User %s login", user.getRealName()));
			return SUCCESS;
		} else {
			this.addActionError(Constrants.s_login_error);
			return ERROR;
		}
	}

	public String logout() {
		User user = (User) m_session.remove(USER);

		if (user != null) {
			m_logger.info(String.format("User %s login out", user.getRealName()));
		}
		return SUCCESS;
	}

	public void setUserName(String userName) {
		m_userName = userName;
	}

	public void setPassword(String password) {
		m_password = password;
	}

	public void setUserService(UserService userService) {
		m_userService = userService;
	}

}