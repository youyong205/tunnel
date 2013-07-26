package com.role;

import java.util.List;

public interface RoleService {

	public int queryAllSize();

	public List<Role> queryLimitedRoles(int start, int size);

	public List<RoleResource> queryRoleResources(int roleId);
	
	public void insertRoleResources(RoleResource roleResource);
	
	public int deleteRoleResources(int roleId);

	public Role findByPK(int id);

	public int insertRole(Role role);

	public int updateRole(Role role);

	public int deleteRole(int id);

}
