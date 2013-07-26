package com.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.Constrants;
import com.PagedAction;
import com.resource.Resource;
import com.resource.ResourceService;
import com.role.RoleResource;
import com.role.RoleService;
import com.user.User;
import com.user.UserRole;
import com.user.UserService;

public class LoginAction extends PagedAction {

	private static final long serialVersionUID = 362224844795605039L;

	private Logger m_logger = Logger.getLogger(LoginAction.class);

	private String m_userName;

	private String m_password;

	private UserService m_userService;

	private RoleService m_roleService;

	private ResourceService m_resourceService;

	private static final String USER = "user";

	public String login() {
		User user = m_userService.findByNamePassword(m_userName, m_password);

		if (user != null) {
			Map<String, Resource> resources = new HashMap<String, Resource>();
			int userId = user.getId();
			List<UserRole> userRoles = m_userService.queryUserRoles(userId);
			
			for (UserRole userRole : userRoles) {
				int roleId = userRole.getRoleId();
				List<RoleResource> res = m_roleService.queryRoleResources(roleId);

				for (RoleResource temp : res) {
					Resource resource = m_resourceService.findByPK(temp.getResourceId());
					String key = resource.getModule() + ":" + resource.getName();
					
					resources.put(key, resource);
				}
			}
			user.setResources(resources);
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

	@Override
   public String getActionModule() {
		return Constrants.s_user_model;
   }

	public void setRoleService(RoleService roleService) {
   	m_roleService = roleService;
   }

	public void setResourceService(ResourceService resourceService) {
   	m_resourceService = resourceService;
   }
	
}