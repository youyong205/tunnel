package com.tunnelGraph;

import java.util.List;

import org.apache.log4j.Logger;

import com.Authority;
import com.Constrants;
import com.Modules;
import com.Operation;
import com.PagedAction;
import com.log.Log;
import com.openSection.OpenSection;
import com.openSection.OpenSectionService;
import com.tunnel.Tunnel;
import com.tunnel.TunnelService;
import com.tunnelSection.TunnelSection;
import com.tunnelSection.TunnelSectionService;
import com.workingWell.WorkingWell;
import com.workingWell.WorkingWellService;

public class TunnelGraphAction extends PagedAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(TunnelGraphAction.class);

	private List<TunnelGraph> m_tunnelGraphs;

	private List<Tunnel> m_tunnels;
	
	private Tunnel m_tunnel;

	private int m_tunnelGraphId;

	private int m_tunnelId;

	private String m_lineType = Constrants.UP;

	private TunnelGraphService m_tunnelGraphService;

	private TunnelService m_tunnelService;

	private TunnelGraph m_tunnelGraph = new TunnelGraph();

	private TunnelSectionService m_tunnelSectionService;

	private WorkingWellService m_workingWellService;

	private OpenSectionService m_openSectionService;

	private List<TunnelSection> m_tunnelSections;

	private List<WorkingWell> m_workingWells;

	private List<OpenSection> m_openSections;

	@Override
	public String getActionModule() {
		return Modules.s_tunnelGraph_model;
	}

	public int getTunnelId() {
		return m_tunnelId;
	}

	public int getTunnelIndexId() {
		return m_tunnelId;
	}

	public List<Tunnel> getTunnels() {
		return m_tunnels;
	}

	public TunnelGraph getTunnelGraph() {
		return m_tunnelGraph;
	}

	public List<TunnelGraph> getTunnelGraphs() {
		return m_tunnelGraphs;
	}

	public void setTunnelId(int tunnelId) {
		m_tunnelId = tunnelId;
	}

	public void setTunnelGraph(TunnelGraph tunnelGraph) {
		m_tunnelGraph = tunnelGraph;
	}

	public void setTunnelGraphId(int tunnelGraphId) {
		m_tunnelGraphId = tunnelGraphId;
	}

	public void setTunnelGraphService(TunnelGraphService tunnelGraphService) {
		m_tunnelGraphService = tunnelGraphService;
	}

	public void setTunnelService(TunnelService tunnelService) {
		m_tunnelService = tunnelService;
	}

	public String tunnelGraphAdd() {
		try {
			if(m_tunnelId==0){
				m_tunnelId= m_tunnelService.queryDefaultTunnelId();
			}
			m_tunnel = m_tunnelService.findByPK(m_tunnelId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0,
			      Integer.MAX_VALUE);
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e);
			return ERROR;
		}
	}

	public String tunnelGraphAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_tunnelGraph_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int id = m_tunnelGraphService.insertTunnelGraph(m_tunnelGraph);
			if (id > 0) {
				Log log = createLog(Modules.s_tunnelGraph_model, Operation.s_operation_add, m_tunnelGraph);

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

	public String tunnelGraphDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_tunnelGraph_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int count = m_tunnelGraphService.deleteTunnelGraph(m_tunnelGraphId);
			if (count > 0) {
				Log log = createLog(Modules.s_tunnelGraph_model, Operation.s_operation_delete, m_tunnelGraphId);

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

	public String tunnelGraphList() {
		Authority auth = checkAuthority(buildResource(Modules.s_tunnelGraph_model, Operation.s_operation_detail));
		if (auth != null) {
			return auth.getName();
		}
		try {
			if (m_tunnelId == 0) {
				m_tunnelId = m_tunnelService.queryDefaultTunnelId();
			}
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_totalSize = m_tunnelGraphService.querySizeByTunnelIdAndLineType(m_tunnelId, m_lineType);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_tunnelGraphs = m_tunnelGraphService.queryLimitedTunnelGraphsByTunnelIdAndLineType(m_tunnelId, m_lineType,
			      start, SIZE);
			for (TunnelGraph section : m_tunnelGraphs) {
				section.setTunnel(m_tunnelService.findByPK(section.getTunnelId()));
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String tunnelGraphUpdate() {
		try {
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelGraph = m_tunnelGraphService.findByPK(m_tunnelGraphId);

			if (m_tunnelGraph != null) {
				m_tunnelGraph.setTunnel(m_tunnelService.findByPK(m_tunnelGraph.getTunnelId()));
				int componemtType = m_tunnelGraph.getComponentType();

				if (componemtType == 1) {
					m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0,
					      Integer.MAX_VALUE);
				} else if (componemtType == 2) {
					m_workingWells = m_workingWellService.queryLimitedWorkingWellsByTunnelId(m_tunnelId, 0,
					      Integer.MAX_VALUE);
				} else if (componemtType == 3) {
					m_openSections = m_openSectionService.queryLimitedOpenSectionsByTunnelId(m_tunnelId, 0,
					      Integer.MAX_VALUE);
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

	public String tunnelGraphUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_tunnelGraph_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int count = m_tunnelGraphService.updateTunnelGraph(m_tunnelGraph);
			if (count > 0) {
				Log log = createLog(Modules.s_tunnelGraph_model, Operation.s_operation_update, m_tunnelGraph);

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

	public String getLineType() {
		return m_lineType;
	}

	public void setLineType(String lineType) {
		m_lineType = lineType;
	}

	public List<TunnelSection> getTunnelSections() {
		return m_tunnelSections;
	}

	public List<WorkingWell> getWorkingWells() {
		return m_workingWells;
	}

	public List<OpenSection> getOpenSections() {
		return m_openSections;
	}

	public void setTunnelSectionService(TunnelSectionService tunnelSectionService) {
		m_tunnelSectionService = tunnelSectionService;
	}

	public void setWorkingWellService(WorkingWellService workingWellService) {
		m_workingWellService = workingWellService;
	}

	public void setOpenSectionService(OpenSectionService openSectionService) {
		m_openSectionService = openSectionService;
	}

	public Tunnel getTunnel() {
   	return m_tunnel;
   }

}