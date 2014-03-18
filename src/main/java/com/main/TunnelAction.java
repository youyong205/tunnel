package com.main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.Authority;
import com.Constrants;
import com.Modules;
import com.Operation;
import com.PagedAction;
import com.buriedSection.BuriedSection;
import com.buriedSection.BuriedSectionService;
import com.openSection.OpenSection;
import com.openSection.OpenSectionService;
import com.tunnel.Tunnel;
import com.tunnel.TunnelService;
import com.tunnelGraph.TunnelGraph;
import com.tunnelGraph.TunnelGraphService;
import com.tunnelSection.TunnelSection;
import com.tunnelSection.TunnelSectionService;
import com.workingWell.WorkingWell;
import com.workingWell.WorkingWellService;

public class TunnelAction extends PagedAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(TunnelAction.class);

	private List<Tunnel> m_tunnels;

	private int m_tunnelId;

	private TunnelService m_tunnelService;

	private TunnelSectionService m_tunnelSectionService;

	private WorkingWellService m_workingWellService;

	private OpenSectionService m_openSectionService;

	private BuriedSectionService m_buriedSectionService;
	
	private TunnelGraphService m_tunnelGraphService;

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

	private List<Object> queryGraphItem(List<TunnelGraph> graphs) {
		List<Object> objs = new ArrayList<Object>();

		for (TunnelGraph graph : graphs) {
			int type = graph.getComponentType();
			int componmentId = graph.getComponentId();
			if (type == 1) {
				TunnelSection tunnelSection = m_tunnelSectionService.findByPK(componmentId);

				if (tunnelSection != null) {
					objs.add(tunnelSection);
				}
			} else if (type == 2) {
				WorkingWell workingWell = m_workingWellService.findByPK(componmentId);

				if (workingWell != null) {
					objs.add(workingWell);
				}
			} else if (type == 3) {
				OpenSection openSection = m_openSectionService.findByPK(componmentId);

				if (openSection != null) {
					objs.add(openSection);
				}
			}else if (type == 4) {
				BuriedSection buriedSection = m_buriedSectionService.findByPK(componmentId);

				if (buriedSection != null) {
					objs.add(buriedSection);
				}
			}
		}
		return objs;
	}

	private Map<String, String> buildTunnelSvg(int tunnelId) {
		List<TunnelGraph> upGraphs = m_tunnelGraphService.queryLimitedTunnelGraphsByTunnelIdAndLineType(tunnelId,
		      Constrants.UP, 0, Integer.MAX_VALUE);
		List<TunnelGraph> downGraphs = m_tunnelGraphService.queryLimitedTunnelGraphsByTunnelIdAndLineType(tunnelId,
		      Constrants.DOWN, 0, Integer.MAX_VALUE);
		List<Object> upItems = queryGraphItem(upGraphs);
		List<Object> downItems = queryGraphItem(downGraphs);

		Map<String, String> svgs = new LinkedHashMap<String, String>();
		TunnelStateBuilder builder = new TunnelStateBuilder();

		svgs.put(Constrants.UP, builder.buildXml(upItems));
		svgs.put(Constrants.DOWN, builder.buildXml(downItems));
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

	public void setWorkingWellService(WorkingWellService workingWellService) {
		m_workingWellService = workingWellService;
	}

	public void setOpenSectionService(OpenSectionService openSectionService) {
		m_openSectionService = openSectionService;
	}

	public void setTunnelGraphService(TunnelGraphService tunnelGraphService) {
		m_tunnelGraphService = tunnelGraphService;
	}

	public void setBuriedSectionService(BuriedSectionService buriedSectionService) {
   	m_buriedSectionService = buriedSectionService;
   }

}