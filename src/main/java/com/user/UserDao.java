package com.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class UserDao {

	private BaseDao m_baseDao;

	public int deleteUser(int id) {
		return m_baseDao.delete("user.delete", id);
	}

	public int deleteUserRoles(int userId) {
		return m_baseDao.delete("userRole.deleteByUserId", userId);
	}

	public User findByNamePassword(String userName, String password) {
		Map<String, String> pars = new HashMap<String, String>();

		pars.put("userName", userName);
		pars.put("password", password);
		return (User) m_baseDao.queryForObject("user.findByName", pars);
	}

	public User findByPK(int id) {
		return (User) m_baseDao.queryForObject("user.findById", id);
	}

	public int insertUser(User user) {
		return (Integer) m_baseDao.insert("user.insert", user);
	}

	public void insertUserRoles(UserRole userRole) {
		m_baseDao.insert("userRole.insert", userRole);
	}

	public int queryAllSize() {
		return (Integer) m_baseDao.queryForObject("user.queryAllSize", null);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllUsers() {
		return m_baseDao.queryForList("user.queryAllUsers");
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedUsers(int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("user.queryLimitedUsers", parameters);
	}

	@SuppressWarnings("rawtypes")
	public List queryUserRoles(int userId) {
		return m_baseDao.queryForList("userRole.queryUserRoles", userId);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateUser(User user) {
		return m_baseDao.update("user.update", user);
	}

}
