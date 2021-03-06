package com.tunnel;

import java.util.List;

import org.apache.log4j.Logger;

import com.Authority;
import com.Modules;
import com.Operation;
import com.PagedAction;
import com.log.Log;

public class TunnelAction extends PagedAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(TunnelAction.class);

	private List<Tunnel> m_tunnels;

	private int m_tunnelId;

	private TunnelService m_tunnelService;

	private Tunnel m_tunnel = new Tunnel();

	@Override
	public String getActionModule() {
		return Modules.s_tunnel_model;
	}

	public Tunnel getTunnel() {
		return m_tunnel;
	}

	public List<Tunnel> getTunnels() {
		return m_tunnels;
	}

	public void setTunnel(Tunnel tunnel) {
		m_tunnel = tunnel;
	}

	public void setTunnelId(int tunnelId) {
		m_tunnelId = tunnelId;
	}

	public void setTunnelService(TunnelService tunnelService) {
		m_tunnelService = tunnelService;
	}

	public String tunnelAdd() {
		return SUCCESS;
	}

	public String tunnelAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_tunnel_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int id = m_tunnelService.insertTunnel(m_tunnel);
			if (id > 0) {
				Log log = createLog(Modules.s_tunnel_model, Operation.s_operation_add, m_tunnel);

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

	public String tunnelDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_tunnel_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int count = m_tunnelService.deleteTunnel(m_tunnelId);
			if (count > 0) {
				Log log = createLog(Modules.s_tunnel_model, Operation.s_operation_delete, m_tunnelId);

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

	public String tunnelList() {
		Authority auth = checkAuthority(buildResource(Modules.s_tunnel_model, Operation.s_operation_detail));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_totalSize = m_tunnelService.queryAllSize();
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_tunnels = m_tunnelService.queryLimitedTunnels(start, SIZE);
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String tunnelUpdate() {
		try {
			m_tunnel = m_tunnelService.findByPK(m_tunnelId);
			if (m_tunnel != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String tunnelUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_tunnel_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int count = m_tunnelService.updateTunnel(m_tunnel);
			if (count > 0) {
				Log log = createLog(Modules.s_tunnel_model, Operation.s_operation_update, m_tunnel);

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

}