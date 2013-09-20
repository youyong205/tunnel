package com.inspection;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.Authority;
import com.Operation;
import com.PagedAction;
import com.constructionUnit.ConstructionUnit;
import com.constructionUnit.ConstructionUnitService;
import com.log.Log;
import com.tunnel.Tunnel;
import com.tunnel.TunnelService;
import com.tunnelSection.TunnelSection;
import com.tunnelSection.TunnelSectionService;

public abstract class InspectionAction extends PagedAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(InspectionAction.class);

	protected List<Inspection> m_inspections;

	protected int m_inspectionId;

	private Tunnel m_tunnel;

	@Autowired
	protected InspectionService m_inspectionService;

	@Autowired
	protected ConstructionUnitService m_constructionUnitService;

	protected List<ConstructionUnit> m_constructionUnits;

	protected List<Tunnel> m_tunnels;

	protected List<TunnelSection> m_tunnelSections;

	@Autowired
	protected TunnelService m_tunnelService;

	@Autowired
	protected TunnelSectionService m_tunnelSectionService;

	protected int m_tunnelId;

	protected int m_tunnelSectionId;

	protected Inspection m_inspection = new Inspection();

	public abstract String getActionModule();

	public abstract String getComponentNameById(int id);

	public List<ConstructionUnit> getConstructionUnits() {
		return m_constructionUnits;
	}

	public Inspection getInspection() {
		return m_inspection;
	}

	public List<Inspection> getInspections() {
		return m_inspections;
	}

	public abstract List<Item> getItems();

	public abstract String getModule();

	public int getParentTunnelSectionId() {
		return m_tunnelSectionId;
	}

	public Tunnel getTunnel() {
		return m_tunnel;
	}

	public int getTunnelId() {
		return m_tunnelId;
	}

	public List<Tunnel> getTunnels() {
		return m_tunnels;
	}

	public int getTunnelSectionId() {
		return m_tunnelSectionId;
	}

	public List<TunnelSection> getTunnelSections() {
		return m_tunnelSections;
	}

	public String inspectionAdd() {
		m_tunnel = m_tunnelService.findByPK(m_tunnelId);
		m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
		m_tunnels = m_tunnelService.queryAllTunnels();
		return SUCCESS;
	}

	public String inspectionAddSubmit() {
		Authority auth = checkAuthority(buildResource(getActionModule(), Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int id = m_inspectionService.insertInspection(m_inspection);
			if (id > 0) {
				Log log = createLog(getActionModule(), Operation.s_operation_add, m_inspection);

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

	public String inspectionDelete() {
		Authority auth = checkAuthority(buildResource(getActionModule(), Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int count = m_inspectionService.deleteInspection(m_inspectionId);
			if (count > 0) {
				Log log = createLog(getActionModule(), Operation.s_operation_delete, m_inspectionId);

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

	public String inspectionList() {
		Authority auth = checkAuthority(buildResource(getActionModule(), Operation.s_operation_detail));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_totalSize = m_inspectionService.queryInspectionSizeByType(m_tunnelId, m_tunnelSectionId, getModule());
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_inspections = m_inspectionService.queryLimitedInspectionsByType(m_tunnelId, m_tunnelSectionId, getModule(),
			      start, SIZE);

			for (Inspection inspection : m_inspections) {
				inspection.setComponentName(getComponentNameById(inspection.getComponentId()));
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String inspectionUpdate() {
		try {
			m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
			m_inspection = m_inspectionService.findByPK(m_inspectionId);
			m_tunnel = m_tunnelService.findByPK(m_inspection.getTunnelId());
			m_tunnels = m_tunnelService.queryAllTunnels();
			if (m_inspection != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String inspectionUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(getActionModule(), Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int count = m_inspectionService.updateInspection(m_inspection);
			if (count > 0) {
				Log log = createLog(getActionModule(), Operation.s_operation_update, m_inspection);

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

	public void setConstructionUnitService(ConstructionUnitService constructionUnitService) {
		m_constructionUnitService = constructionUnitService;
	}

	public void setInspection(Inspection inspection) {
		m_inspection = inspection;
	}

	public void setInspectionId(int inspectionId) {
		m_inspectionId = inspectionId;
	}

	public void setInspectionService(InspectionService inspectionService) {
		m_inspectionService = inspectionService;
	}

	public void setTunnel(Tunnel tunnel) {
		m_tunnel = tunnel;
	}

	public void setTunnelId(int tunnelId) {
		m_tunnelId = tunnelId;
	}

	public void setTunnelSectionId(int tunnelSectionId) {
		m_tunnelSectionId = tunnelSectionId;
	}

	public void setTunnelSectionService(TunnelSectionService tunnelSectionService) {
		m_tunnelSectionService = tunnelSectionService;
	}

	public void setTunnelService(TunnelService tunnelService) {
		m_tunnelService = tunnelService;
	}

	protected void validateTunnelId() {
		if (m_tunnelId == 0) {
			m_tunnelId = m_tunnelService.queryDefaultTunnelId();
		}
	}

	public static class Item {

		private int m_id;

		private String m_name;

		public int getId() {
			return m_id;
		}

		public String getName() {
			return m_name;
		}

		public void setId(int id) {
			m_id = id;
		}

		public void setName(String name) {
			m_name = name;
		}
	}

}