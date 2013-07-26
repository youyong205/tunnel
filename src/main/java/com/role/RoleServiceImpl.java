package com.role;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class RoleServiceImpl implements RoleService {

	private RoleDao m_roleDao;

	private Logger m_logger = Logger.getLogger(RoleServiceImpl.class);

	@SuppressWarnings("unchecked")
	public List<Role> queryAllRoles() {
		try {
			return m_roleDao.queryAllRoles();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Role>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> queryLimitedRoles(int start, int size) {
		try {
			return m_roleDao.queryLimitedRoles(start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Role>();
		}
	}

	@Override
	public Role findByPK(int id) {
		try {
			return m_roleDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public int insertRole(Role role) {
		try {
			return m_roleDao.insertRole(role);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public int updateRole(Role role) {
		try {
			return m_roleDao.updateRole(role);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public int deleteRole(int id) {
		try {
			return m_roleDao.deleteRole(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setRoleDao(RoleDao roleDao) {
		m_roleDao = roleDao;
	}

	@Override
	public int queryAllSize() {
		try {
			return m_roleDao.queryAllSize();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
   @Override
	public List<RoleResource> queryRoleResources(int roleId) {
		try {
			return m_roleDao.queryRoleResources(roleId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<RoleResource>();
		}
	}

	@Override
	public void insertRoleResources(RoleResource roleResource) {
		try {
			m_roleDao.insertRoleResources(roleResource);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
	}

	@Override
	public int deleteRoleResources(int roleId) {
		try {
			return m_roleDao.deleteRoleResources(roleId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
