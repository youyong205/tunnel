package com.openSection;

import java.util.List;

import org.apache.log4j.Logger;

import com.Constrants;
import com.FileUploadAction;
import com.document.Document;
import com.log.Log;
import com.tunnel.Tunnel;
import com.tunnel.TunnelService;

public class OpenSectionAction extends FileUploadAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(OpenSectionAction.class);

	private List<OpenSection> m_openSections;

	private List<Tunnel> m_tunnels;

	private int m_openSectionId;

	private int m_tunnelId;

	private OpenSectionService m_openSectionService;

	private TunnelService m_tunnelService;

	private OpenSection m_openSection = new OpenSection();

	public OpenSection getOpenSection() {
		return m_openSection;
	}

	public List<OpenSection> getOpenSections() {
		return m_openSections;
	}

	public void setOpenSection(OpenSection openSection) {
		m_openSection = openSection;
	}

	public void setOpenSectionId(int openSectionId) {
		m_openSectionId = openSectionId;
	}

	public void setOpenSectionService(OpenSectionService openSectionService) {
		m_openSectionService = openSectionService;
	}

	public String openSectionAdd() {
		try {
			m_tunnels = m_tunnelService.queryAllTunnels();
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e);
			return ERROR;
		}
	}

	public String openSectionAddSubmit() {
		try {
			int documentId = 0;
			if (m_uploadFile.getFile() != null) {
				documentId = m_documentService.insertDocument(Constrants.s_openSection_model, m_uploadFile);
			}
			m_openSection.setDocumentId(documentId);
			int id = m_openSectionService.insertOpenSection(m_openSection);
			if (id > 0) {
				Log log = createLog(Constrants.s_openSection_model, Constrants.s_operation_add, m_openSection);

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

	public String openSectionDelete() {
		try {
			int count = m_openSectionService.deleteOpenSection(m_openSectionId);
			if (count > 0) {
				Log log = createLog(Constrants.s_openSection_model, Constrants.s_operation_delete, m_openSectionId);

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

	public String openSectionList() {
		try {
			if (m_tunnelId == 0) {
				m_tunnels = m_tunnelService.queryAllTunnels();
				m_totalSize = m_openSectionService.queryAllSize();
				m_totalPages = computeTotalPages(m_totalSize);
				int start = (m_index - 1) * SIZE;
				if (start < 0) {
					start = 0;
				}
				m_openSections = m_openSectionService.queryLimitedOpenSections(start, SIZE);
			} else {
				m_tunnels = m_tunnelService.queryAllTunnels();
				m_totalSize = m_openSectionService.querySizeByTunnelId(m_tunnelId);
				m_totalPages = computeTotalPages(m_totalSize);
				int start = (m_index - 1) * SIZE;
				if (start < 0) {
					start = 0;
				}
				m_openSections = m_openSectionService.queryLimitedOpenSectionsByTunnelId(m_tunnelId, start, SIZE);
			}
			for (OpenSection channel : m_openSections) {
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

	public String openSectionUpdate() {
		try {
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_openSection = m_openSectionService.findByPK(m_openSectionId);
			if (m_openSection != null) {
				m_openSection.setTunnel(m_tunnelService.findByPK(m_openSection.getTunnelId()));

				if (m_openSection.getDocumentId() > 0) {
					m_openSection.setDocument(m_documentService.findByPK(m_openSection.getDocumentId()));
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

	public String openSectionUpdateSubmit() {
		try {
			if (m_uploadFile.getFile() != null) {
				int documentId = m_openSection.getDocumentId();
				
				if(documentId>0){
					Document document = m_documentService.findByPK(documentId);
					m_documentService.updateDocument(Constrants.s_openSection_model, m_uploadFile, document);
				}else{
					documentId = m_documentService.insertDocument(Constrants.s_openSection_model, m_uploadFile);
					m_openSection.setDocumentId(documentId);
				}
			}
			int count = m_openSectionService.updateOpenSection(m_openSection);
			if (count > 0) {
				Log log = createLog(Constrants.s_openSection_model, Constrants.s_operation_update, m_openSection);

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
		return Constrants.s_openSection_model;
   }
}