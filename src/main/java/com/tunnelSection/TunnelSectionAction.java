package com.tunnelSection;

import java.util.List;

import org.apache.log4j.Logger;

import com.Constrants;
import com.PagedAction;
import com.log.Log;
import com.tunnel.Tunnel;
import com.tunnel.TunnelService;

public class TunnelSectionAction extends PagedAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(TunnelSectionAction.class);

	private List<TunnelSection> m_tunnelSections;

	private List<Tunnel> m_tunnels;

	private int m_tunnelSectionId;

	private int m_tunnelId;

	private TunnelSectionService m_tunnelSectionService;

	private TunnelService m_tunnelService;

	private TunnelSection m_tunnelSection = new TunnelSection();

	public TunnelSection getTunnelSection() {
		return m_tunnelSection;
	}

	public List<TunnelSection> getTunnelSections() {
		return m_tunnelSections;
	}

	public void setTunnelSection(TunnelSection tunnelSection) {
		m_tunnelSection = tunnelSection;
	}

	public void setTunnelSectionId(int tunnelSectionId) {
		m_tunnelSectionId = tunnelSectionId;
	}

	public void setTunnelSectionService(TunnelSectionService tunnelSectionService) {
		m_tunnelSectionService = tunnelSectionService;
	}

	public String tunnelSectionAdd() {
		try {
			m_tunnels = m_tunnelService.queryAllTunnels();
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e);
			return ERROR;
		}
	}

	public String tunnelSectionAddSubmit() {
		try {
			int id = m_tunnelSectionService.insertTunnelSection(m_tunnelSection);
			if (id > 0) {
				Log log = createLog(Constrants.s_tunnelSection_model, Constrants.s_operation_add, m_tunnelSection);

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

	public String tunnelSectionDelete() {
		try {
			int count = m_tunnelSectionService.deleteTunnelSection(m_tunnelSectionId);
			if (count > 0) {
				Log log = createLog(Constrants.s_tunnelSection_model, Constrants.s_operation_delete, m_tunnelSectionId);

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

	public String tunnelSectionList() {
		try {
			if (m_tunnelId == 0) {
				m_tunnels = m_tunnelService.queryAllTunnels();
				m_totalSize = m_tunnelSectionService.queryAllSize();
				m_totalPages = computeTotalPages(m_totalSize);
				int start = (m_index - 1) * SIZE;
				if (start < 0) {
					start = 0;
				}
				m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSections(start, SIZE);
			} else {
				m_tunnels = m_tunnelService.queryAllTunnels();
				m_totalSize = m_tunnelSectionService.querySizeByTunnelId(m_tunnelId);
				m_totalPages = computeTotalPages(m_totalSize);
				int start = (m_index - 1) * SIZE;
				if (start < 0) {
					start = 0;
				}
				m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, start, SIZE);
			}
			for (TunnelSection section : m_tunnelSections) {
				section.setTunnel(m_tunnelService.findByPK(section.getTunnelId()));
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String tunnelSectionUpdate() {
		try {
			m_tunnelSection = m_tunnelSectionService.findByPK(m_tunnelSectionId);
			if (m_tunnelSection != null) {
				m_tunnelSection.setTunnel(m_tunnelService.findByPK(m_tunnelSection.getTunnelId()));
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String tunnelSectionUpdateSubmit() {
		try {
			int count = m_tunnelSectionService.updateTunnelSection(m_tunnelSection);
			if (count > 0) {
				Log log = createLog(Constrants.s_tunnelSection_model, Constrants.s_operation_update, m_tunnelSection);

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

	public int getTunnelId() {
   	return m_tunnelId;
   }

	public void setTunnelId(int tunnelId) {
   	m_tunnelId = tunnelId;
   }
	
}