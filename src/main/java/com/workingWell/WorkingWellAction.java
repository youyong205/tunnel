package com.workingWell;

import java.util.List;

import org.apache.log4j.Logger;

import com.Constrants;
import com.FileUploadAction;
import com.document.Document;
import com.log.Log;
import com.tunnel.Tunnel;
import com.tunnel.TunnelService;

public class WorkingWellAction extends FileUploadAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(WorkingWellAction.class);

	private List<WorkingWell> m_workingWells;

	private List<Tunnel> m_tunnels;

	private int m_workingWellId;

	private int m_tunnelId;

	private WorkingWellService m_workingWellService;

	private TunnelService m_tunnelService;

	private WorkingWell m_workingWell = new WorkingWell();

	public WorkingWell getWorkingWell() {
		return m_workingWell;
	}

	public List<WorkingWell> getWorkingWells() {
		return m_workingWells;
	}

	public void setWorkingWell(WorkingWell workingWell) {
		m_workingWell = workingWell;
	}

	public void setWorkingWellId(int workingWellId) {
		m_workingWellId = workingWellId;
	}

	public void setWorkingWellService(WorkingWellService workingWellService) {
		m_workingWellService = workingWellService;
	}

	public String workingWellAdd() {
		try {
			m_tunnels = m_tunnelService.queryAllTunnels();
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e);
			return ERROR;
		}
	}

	public String workingWellAddSubmit() {
		try {
			int documentId = 0;
			if (m_uploadFile.getFile() != null) {
				documentId = m_documentService.insertDocument(Constrants.s_workingWell_model, m_uploadFile);
			}
			m_workingWell.setDocumentId(documentId);
			int id = m_workingWellService.insertWorkingWell(m_workingWell);
			if (id > 0) {
				Log log = createLog(Constrants.s_workingWell_model, Constrants.s_operation_add, m_workingWell);

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

	public String workingWellDelete() {
		try {
			int count = m_workingWellService.deleteWorkingWell(m_workingWellId);
			if (count > 0) {
				Log log = createLog(Constrants.s_workingWell_model, Constrants.s_operation_delete, m_workingWellId);

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

	public String workingWellList() {
		try {
			if (m_tunnelId == 0) {
				m_tunnels = m_tunnelService.queryAllTunnels();
				m_totalSize = m_workingWellService.queryAllSize();
				m_totalPages = computeTotalPages(m_totalSize);
				int start = (m_index - 1) * SIZE;
				if (start < 0) {
					start = 0;
				}
				m_workingWells = m_workingWellService.queryLimitedWorkingWells(start, SIZE);
			} else {
				m_tunnels = m_tunnelService.queryAllTunnels();
				m_totalSize = m_workingWellService.querySizeByTunnelId(m_tunnelId);
				m_totalPages = computeTotalPages(m_totalSize);
				int start = (m_index - 1) * SIZE;
				if (start < 0) {
					start = 0;
				}
				m_workingWells = m_workingWellService.queryLimitedWorkingWellsByTunnelId(m_tunnelId, start, SIZE);
			}
			for (WorkingWell channel : m_workingWells) {
				channel.setTunnel(m_tunnelService.findByPK(channel.getTunnelId()));

				if (channel.getDocumentId() > 0) {
					channel.setDocument(m_documentService.findByPK(channel.getDocumentId()));
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String workingWellUpdate() {
		try {
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_workingWell = m_workingWellService.findByPK(m_workingWellId);
			if (m_workingWell != null) {
				m_workingWell.setTunnel(m_tunnelService.findByPK(m_workingWell.getTunnelId()));

				if (m_workingWell.getDocumentId() > 0) {
					m_workingWell.setDocument(m_documentService.findByPK(m_workingWell.getDocumentId()));
				}
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String workingWellUpdateSubmit() {
		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_workingWell.getDocumentId();
				if(documentId>0){
					Document document = m_documentService.findByPK(documentId);
					m_documentService.updateDocument(Constrants.s_contactChannel_model, m_uploadFile, document);
				}else{
					documentId = m_documentService.insertDocument(Constrants.s_contactChannel_model, m_uploadFile);
					m_workingWell.setDocumentId(documentId);
				}
			}
			int count = m_workingWellService.updateWorkingWell(m_workingWell);
			if (count > 0) {
				Log log = createLog(Constrants.s_workingWell_model, Constrants.s_operation_update, m_workingWell);

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

	public void setTunnelService(TunnelService tunnelService) {
		m_tunnelService = tunnelService;
	}

	public List<Tunnel> getTunnels() {
		return m_tunnels;
	}

	public int getTunnelIndexId() {
		return m_tunnelId;
	}

	public int getTunnelId() {
		return m_tunnelId;
	}

	public void setTunnelId(int tunnelId) {
		m_tunnelId = tunnelId;
	}
	
	@Override
   public String getActionModule() {
		return Constrants.s_workingWell_model;
   }

}