package com.main;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.Authority;
import com.Modules;
import com.Operation;
import com.PagedAction;
import com.main.TunnelState.TunnelSectionState;
import com.tunnel.Tunnel;
import com.tunnel.TunnelService;
import com.tunnelSection.TunnelSection;
import com.tunnelSection.TunnelSectionService;

public class TunnelAction extends PagedAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(TunnelAction.class);

	private List<Tunnel> m_tunnels;

	private int m_tunnelId;

	private TunnelService m_tunnelService;

	private TunnelSectionService m_tunnelSectionService;

	private Tunnel m_tunnel = new Tunnel();

	private Map<String, String> m_svgs = new LinkedHashMap<String, String>();

	private Map<Tunnel, Map<String, String>> m_allSvgs = new LinkedHashMap<Tunnel, Map<String, String>>();

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

	public String buildSvgTitle(int index, int fixSize, int length) {
		String title = (fixSize * index + 1) + "-" + (fixSize * index + length);

		return title;
	}

	public String tunnelList() {
		Authority auth = checkAuthority(buildResource(Modules.s_tunnel_model, Operation.s_operation_detail));
		if (auth != null) {
			return auth.getName();
		}
		m_tunnels = m_tunnelService.queryAllTunnels();

		for (Tunnel tunnel : m_tunnels) {
			m_allSvgs.put(tunnel, buildTunnelSvg(tunnel.getId()));
		}
		return SUCCESS;
	}

	public String tunnelDetail() {
		Authority auth = checkAuthority(buildResource(Modules.s_tunnel_model, Operation.s_operation_detail));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_tunnel = m_tunnelService.findByPK(m_tunnelId);
			m_svgs = buildTunnelSvg(m_tunnelId);
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

	private Map<String, String> buildTunnelSvg(int tunnelId) {
		List<TunnelSection> sections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(tunnelId, 0,
		      Integer.MAX_VALUE);
		TunnelState state = new TunnelState(sections);
		TunnelStateBuilder builder = new TunnelStateBuilder();
		List<List<TunnelSectionState>> states = state.getStates();
		int size = state.getSize();
		int length = states.size();
		Map<String, String> svgs = new LinkedHashMap<String, String>();

		for (int i = 0; i < length; i++) {
			List<TunnelSectionState> s = states.get(i);
			svgs.put(buildSvgTitle(i, size, states.size()), builder.buildXml(s));
		}
		return svgs;
	}

	public void setTunnelSectionService(TunnelSectionService tunnelSectionService) {
		m_tunnelSectionService = tunnelSectionService;
	}

	public Map<String, String> getSvgs() {
		return m_svgs;
	}

	public Map<Tunnel, Map<String, String>> getAllSvgs() {
		return m_allSvgs;
	}

}