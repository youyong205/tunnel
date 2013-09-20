package com.main;

import java.util.List;

import org.apache.log4j.Logger;

import com.liningRingConstruction.LiningRingConstruction;
import com.liningRingConstruction.LiningRingConstructionService;
import com.opensymphony.xwork2.ActionSupport;
import com.tunnel.Tunnel;
import com.tunnel.TunnelService;
import com.tunnelSection.TunnelSection;
import com.tunnelSection.TunnelSectionService;

public class TunnelSectionAction extends ActionSupport {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(TunnelSectionAction.class);

	private List<TunnelSection> m_tunnelSections;

	private List<Tunnel> m_tunnels;

	private int m_tunnelSectionId;

	private int m_tunnelId;

	private TunnelSectionService m_tunnelSectionService;

	private List<LiningRingConstruction> m_liningRingConstructions;

	private LiningRingConstructionService m_LiningRingConstructionService;

	private TunnelService m_tunnelService;

	private TunnelSection m_tunnelSection;

	@Override
	public String execute() throws Exception {
		try {
			if (m_tunnelId == 0) {
				m_tunnelId = m_tunnelService.queryDefaultTunnelId();
			}
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0,
			      Integer.MAX_VALUE);
			if (m_tunnelSectionId == 0 && m_tunnelSections.size() > 0) {
				m_tunnelSectionId = m_tunnelSections.get(0).getId();
			}
			m_tunnelSection = m_tunnelSectionService.findByPK(m_tunnelSectionId);
			m_liningRingConstructions = m_LiningRingConstructionService.queryLimitedLiningRingConstructions(m_tunnelId,
			      m_tunnelSectionId, 0, Integer.MAX_VALUE);
		} catch (Exception e) {
			m_logger.error(e);
		}
		return SUCCESS;
	}

	public int getTunnelSectionId() {
		return m_tunnelSectionId;
	}

	public void setTunnelSectionId(int tunnelSectionId) {
		m_tunnelSectionId = tunnelSectionId;
	}

	public int getTunnelId() {
		return m_tunnelId;
	}

	public void setTunnelId(int tunnelId) {
		m_tunnelId = tunnelId;
	}

	public List<TunnelSection> getTunnelSections() {
		return m_tunnelSections;
	}

	public List<Tunnel> getTunnels() {
		return m_tunnels;
	}

	public List<LiningRingConstruction> getLiningRingConstructions() {
		return m_liningRingConstructions;
	}

	public TunnelSection getTunnelSection() {
		return m_tunnelSection;
	}

	public void setTunnelSectionService(TunnelSectionService tunnelSectionService) {
		m_tunnelSectionService = tunnelSectionService;
	}

	public void setLiningRingConstructionService(LiningRingConstructionService liningRingConstructionService) {
		m_LiningRingConstructionService = liningRingConstructionService;
	}

	public void setTunnelService(TunnelService tunnelService) {
		m_tunnelService = tunnelService;
	}

}