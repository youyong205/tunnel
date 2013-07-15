package com.user;

import java.util.List;

public interface UserService {

	public int queryAllSize();

	public List<User> queryLimitedUsers(int start, int size);

	public User findByName(String name);

	public User findByPK(int id);

	public int insertUser(User user);

	public int updateUser(User user);

	public int deleteUser(int id);

}
