package com.role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.Constrants;
import com.PagedAction;
import com.log.Log;
import com.resource.Resource;
import com.resource.ResourceService;

public class RoleAction extends PagedAction {

	private static final long serialVersionUID = 4901652667413788534L;

	private Logger m_logger = Logger.getLogger(RoleAction.class);

	private List<Role> m_roles;

	private int m_roleId;

	private RoleService m_roleService;

	private ResourceService m_resourceService;

	private Role m_role = new Role();

	private Integer[] m_resourceIdSelect;

	private Map<String, ModuleResources> m_moduleResources = new HashMap<String, ModuleResources>();

	public Role getRole() {
		return m_role;
	}

	public List<Role> getRoles() {
		return m_roles;
	}

	public void setRole(Role role) {
		m_role = role;
	}

	public void setRoleId(int roleId) {
		m_roleId = roleId;
	}

	public void setRoleService(RoleService roleService) {
		m_roleService = roleService;
	}

	private void buildModuleResources(List<Resource> resources) {
		for (Resource res : resources) {
			String module = res.getModule();
			ModuleResources moduleResources = findOrCreateModuleResources(module);

			List<Resource> moduleResource = moduleResources.getResources();

			if (moduleResource == null) {
				moduleResource = new ArrayList<Resource>();
				moduleResources.setResources(moduleResource);
			}
			moduleResource.add(res);
		}
	}

	private ModuleResources findOrCreateModuleResources(String module) {
		ModuleResources moduleResources = m_moduleResources.get(module);

		if (moduleResources == null) {
			moduleResources = new ModuleResources();
			moduleResources.setModule(module);
			m_moduleResources.put(module, moduleResources);
		}
		return moduleResources;
	}

	public String roleAdd() {
		List<Resource> resources = m_resourceService.queryLimitedResources(0, Integer.MAX_VALUE);

		buildModuleResources(resources);
		return SUCCESS;
	}

	public String roleAddSubmit() {
		try {
			int id = m_roleService.insertRole(m_role);
			for (Integer temp : m_resourceIdSelect) {
				if (temp > 0) {
					RoleResource roleResource = new RoleResource();

					roleResource.setRoleId(m_role.getId());
					roleResource.setResourceId(temp);
					m_roleService.insertRoleResources(roleResource);
				}
			}
			if (id > 0) {
				Log log = createLog(Constrants.s_role_model, Constrants.s_operation_add, m_role);

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

	public String roleDelete() {
		try {
			int count = m_roleService.deleteRole(m_roleId);
			if (count > 0) {
				Log log = createLog(Constrants.s_role_model, Constrants.s_operation_delete, m_roleId);

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

	public String roleList() {
		try {
			m_totalSize = m_roleService.queryAllSize();
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_roles = m_roleService.queryLimitedRoles(start, SIZE);
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String roleUpdate() {
		try {
			List<Resource> resources = m_resourceService.queryLimitedResources(0, Integer.MAX_VALUE);
			List<RoleResource> selectedResources = m_roleService.queryRoleResources(m_roleId);

			buildModuleResources(resources);
			buildModuleSelectedResources(selectedResources);
			m_role = m_roleService.findByPK(m_roleId);
			if (m_role != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	private void buildModuleSelectedResources(List<RoleResource> selectedResources) {
		m_resourceIdSelect = new Integer[selectedResources.size()];
		for (int i = 0; i < selectedResources.size(); i++) {
			m_resourceIdSelect[i] = selectedResources.get(i).getResourceId();

		}
	}

	public String roleUpdateSubmit() {
		try {
			int count = m_roleService.updateRole(m_role);
			m_roleService.deleteRoleResources(m_role.getId());
			for (Integer temp : m_resourceIdSelect) {
				if (temp > 0) {
					RoleResource roleResource = new RoleResource();

					roleResource.setRoleId(m_role.getId());
					roleResource.setResourceId(temp);
					m_roleService.insertRoleResources(roleResource);
				}
			}
			if (count > 0) {
				Log log = createLog(Constrants.s_role_model, Constrants.s_operation_update, m_role);

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

	public Integer[] getResourceIdSelect() {
		return m_resourceIdSelect;
	}

	public void setResourceIdSelect(Integer[] resourceIdSelect) {
		m_resourceIdSelect = resourceIdSelect;
	}

	public ResourceService getResourceService() {
		return m_resourceService;
	}

	public Map<String, ModuleResources> getModuleResources() {
		return m_moduleResources;
	}

	public void setResourceService(ResourceService resourceService) {
		m_resourceService = resourceService;
	}

	public static class ModuleResources {

		private List<Resource> m_resources;

		private String m_module;

		public List<Resource> getResources() {
			return m_resources;
		}

		public void setResources(List<Resource> resources) {
			m_resources = resources;
		}

		public String getModule() {
			return m_module;
		}

		public void setModule(String module) {
			m_module = module;
		}
	}

}