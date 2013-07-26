package com.user;

import java.util.List;

public interface UserService {

	public int queryAllSize();

	public List<User> queryLimitedUsers(int start, int size);
	
	public List<UserRole> queryUserRoles(int userId);
	
	public void insertUserRoles(UserRole userRole);
	
	public int deleteUserRoles(int userId);

	public User findByNamePassword(String name,String password);

	public User findByPK(int id);

	public int insertUser(User user);

	public int updateUser(User user);

	public int deleteUser(int id);

}
