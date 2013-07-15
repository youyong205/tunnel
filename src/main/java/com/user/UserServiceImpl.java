package com.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class UserServiceImpl implements UserService {

	private UserDao m_userDao;

	private Logger m_logger = Logger.getLogger(UserServiceImpl.class);

	@SuppressWarnings("unchecked")
	public List<User> queryAllUsers() {
		try {
			return m_userDao.queryAllUsers();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<User>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryLimitedUsers(int start, int size) {
		try {
			return m_userDao.queryLimitedUsers(start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<User>();
		}
	}

	@Override
	public User findByName(String name) {
		try {
			return m_userDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public User findByPK(int id) {
		try {
			return m_userDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public int insertUser(User user) {
		try {
			return m_userDao.insertUser(user);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public int updateUser(User user) {
		try {
			return m_userDao.updateUser(user);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public int deleteUser(int id) {
		try {
			return m_userDao.deleteUser(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setUserDao(UserDao userDao) {
		m_userDao = userDao;
	}

	@Override
   public int queryAllSize() {
		try {
			return m_userDao.queryAllSize();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
   }

}
