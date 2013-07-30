package com.resource;

import java.util.List;

import org.apache.log4j.Logger;

import com.Constrants;
import com.PagedAction;
import com.log.Log;

public class ResourceAction extends PagedAction {

   private static final long serialVersionUID = 4901652667413788534L;

	private Logger m_logger = Logger.getLogger(ResourceAction.class);

	private List<Resource> m_resources;

	private int m_resourceId;

	private ResourceService m_resourceService;
	
	private Resource m_resource = new Resource();
	
	@Override
   public String getActionModule() {
		return Constrants.s_resource_model;
   }

	public Resource getResource() {
		return m_resource;
	}

	public List<Resource> getResources() {
		return m_resources;
	}

	public String resourceAdd(){
		return SUCCESS;
	}

	public String resourceAddSubmit() {
		try {
			int id = m_resourceService.insertResource(m_resource);
			if (id > 0) {
				Log log = createLog(Constrants.s_resource_model, Constrants.s_operation_add, m_resource);
				
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
	
	public String resourceDelete() {
		try {
			int count = m_resourceService.deleteResource(m_resourceId);
			if (count > 0) {
				Log log = createLog(Constrants.s_resource_model, Constrants.s_operation_delete, m_resourceId);
				
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

	public String resourceList() {
		try {
			m_totalSize = m_resourceService.queryAllSize();
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_resources = m_resourceService.queryLimitedResources(start, SIZE);
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String resourceUpdate() {
		try {
			m_resource = m_resourceService.findByPK(m_resourceId);
			if (m_resource != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String resourceUpdateSubmit() {
		try {
			int count = m_resourceService.updateResource(m_resource);
			if (count > 0) {
				Log log = createLog(Constrants.s_resource_model, Constrants.s_operation_update, m_resource);
				
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

	public void setResource(Resource resource) {
		m_resource = resource;
	}

	public void setResourceId(int resourceId) {
		m_resourceId = resourceId;
	}


	public void setResourceService(ResourceService resourceService) {
		m_resourceService = resourceService;
	}
}