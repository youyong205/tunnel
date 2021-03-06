package com.liningRingConstruction;

import java.util.List;

import org.apache.log4j.Logger;

import com.Authority;
import com.Modules;
import com.Operation;
import com.ScheduledAction;
import com.liningRing.LiningRing;
import com.liningRing.LiningRingBlock;
import com.liningRing.LiningRingBlockService;
import com.liningRing.LiningRingService;
import com.log.Log;
import com.schedule.Schedule;
import com.tunnel.Tunnel;
import com.tunnel.TunnelService;
import com.tunnelSection.TunnelSection;
import com.tunnelSection.TunnelSectionService;

public class LiningRingConstructionAction extends ScheduledAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(LiningRingConstructionAction.class);

	private List<LiningRingConstruction> m_liningRingConstructions;

	private int m_liningRingConstructionId;

	private LiningRingConstructionService m_liningRingConstructionService;

	private LiningRingService m_liningRingService;

	private LiningRingConstruction m_liningRingConstruction = new LiningRingConstruction();

	private List<LiningRingBlock> m_liningRingBlocks;

	private LiningRingBlockService m_liningRingBlockService;

	private int m_tunnelId;

	private int m_tunnelSectionId;

	private TunnelService m_tunnelService;

	private TunnelSectionService m_tunnelSectionService;

	private List<Tunnel> m_tunnels;

	private List<TunnelSection> m_tunnelSections;

	private List<LiningRing> m_liningRings;

	private String[] m_girthFaultState = new String[10];
	
	private String[] m_longitudinalOpenState = new String[10];

	private String[] m_girthOpenState = new String[10];

	private String[] m_longitudinalFaultState = new String[10];

	private String[] m_coverLossState = new String[10];

	private String[] m_cracksState = new String[10];

	@Override
	public String getActionModule() {
		return Modules.s_liningRingConstruction_model;
	}

	public List<LiningRingBlock> getLiningRingBlocks() {
		return m_liningRingBlocks;
	}

	public LiningRingConstruction getLiningRingConstruction() {
		return m_liningRingConstruction;
	}

	public List<LiningRingConstruction> getLiningRingConstructions() {
		return m_liningRingConstructions;
	}

	public List<LiningRing> getLiningRings() {
		return m_liningRings;
	}

	public int getParentTunnelSectionId() {
		return m_tunnelSectionId;
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

	public String liningRingConstructionAdd() {
		m_tunnels = m_tunnelService.queryAllTunnels();
		m_liningRings = m_liningRingService.queryAllLiningRings();
		if (m_tunnelId == 0) {
			m_tunnelId = m_tunnelService.queryDefaultTunnelId();
		}
		m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);
		m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
		return SUCCESS;
	}

	private void buildDefaultState(LiningRingConstruction liningRingConstruction) {
		int liningRingId = m_liningRingConstruction.getLiningRingId();
		int size = m_liningRingBlockService.queryByLiningRingId(liningRingId).size();
		StringBuilder sb = new StringBuilder();
		boolean first = true;

		for (int i = 0; i < size; i++) {
			if (first) {
				sb.append("-");
				first = false;
			} else {
				sb.append(",").append("-");
			}
		}
		String defaultState = "-";
		String defaultBlockState = sb.toString();

		m_liningRingConstruction.setDeformationState(defaultState);
		m_liningRingConstruction.setLongitudinalDeformationState(defaultState);
		
		m_liningRingConstruction.setGirthFaultState(defaultBlockState);
		m_liningRingConstruction.setLongitudinalOpenState(defaultBlockState);
		m_liningRingConstruction.setGirthOpenState(defaultBlockState);
		m_liningRingConstruction.setLongitudinalFaultState(defaultBlockState);
		m_liningRingConstruction.setCoverLossState(defaultBlockState);
		m_liningRingConstruction.setCracksState(defaultBlockState);
	}

	private String buildBlockState(String[] states) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		int size = states.length;

		for (int i = 0; i < size; i++) {
			String item = states[i];

			if (first) {
				if (item != null && item.length() > 0) {
					sb.append(item);
					first = false;
				}
			} else {
				if (item != null && item.length() > 0) {
					sb.append(",").append(item);
				}
			}
		}
		
		return sb.toString();
	}

	private void updateDefaultState(LiningRingConstruction liningRingConstruction) {
		m_liningRingConstruction.setLongitudinalOpenState(buildBlockState(m_longitudinalOpenState));
		m_liningRingConstruction.setGirthOpenState(buildBlockState(m_girthOpenState));
		m_liningRingConstruction.setGirthFaultState(buildBlockState(m_girthFaultState));
		m_liningRingConstruction.setLongitudinalFaultState(buildBlockState(m_longitudinalFaultState));
		m_liningRingConstruction.setCoverLossState(buildBlockState(m_coverLossState));
		m_liningRingConstruction.setCracksState(buildBlockState(m_cracksState));
	}

	public String liningRingConstructionAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_liningRingConstruction_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_schedule.setType(getActionModule());
			int scheduleId = m_scheduleService.insertSchedule(m_schedule);
			m_liningRingConstruction.setScheduleId(scheduleId);
			buildDefaultState(m_liningRingConstruction);
			int id = m_liningRingConstructionService.insertLiningRingConstruction(m_liningRingConstruction);
			if (id > 0) {
				Log log = createLog(Modules.s_liningRingConstruction_model, Operation.s_operation_add,
				      m_liningRingConstruction);

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

	public String liningRingConstructionDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_liningRingConstruction_model,
		      Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_liningRingConstruction = m_liningRingConstructionService.findByPK(m_liningRingConstructionId);
			m_scheduleService.deleteSchedule(m_liningRingConstruction.getScheduleId());
			int count = m_liningRingConstructionService.deleteLiningRingConstruction(m_liningRingConstructionId);
			if (count > 0) {
				Log log = createLog(Modules.s_liningRingConstruction_model, Operation.s_operation_delete,
				      m_liningRingConstructionId);

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

	public String liningRingConstructionList() {
		Authority auth = checkAuthority(buildResource(Modules.s_liningRingConstruction_model,
		      Operation.s_operation_detail));
		if (auth != null) {
			return auth.getName();
		}
		try {
			if (m_tunnelId == 0) {
				m_tunnelId = m_tunnelService.queryDefaultTunnelId();
			}
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0,
			      Integer.MAX_VALUE);

			m_totalSize = m_liningRingConstructionService.querySizeByTunnelAndSection(m_tunnelId, m_tunnelSectionId);
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_liningRingConstructions = m_liningRingConstructionService.queryLimitedLiningRingConstructions(m_tunnelId,
			      m_tunnelSectionId, start, SIZE);
			for (LiningRingConstruction liningRingConstruction : m_liningRingConstructions) {
				liningRingConstruction.setTunnel(m_tunnelService.findByPK(liningRingConstruction.getTunnelId()));
				int scheduleId = liningRingConstruction.getScheduleId();
				if (scheduleId > 0) {
					Schedule schedule = m_scheduleService.findByPK(scheduleId);
					if (schedule != null) {
						liningRingConstruction.setSchedule(schedule);
						schedule.setConstructionUnit(m_constructionUnitService.findByPK(schedule.getConstructionUnitId()));
					}
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String liningRingConstructionUpdate() {
		try {
			m_liningRings = m_liningRingService.queryAllLiningRings();
			m_liningRingConstruction = m_liningRingConstructionService.findByPK(m_liningRingConstructionId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(
			      m_liningRingConstruction.getTunnelId(), 0, Integer.MAX_VALUE);
			m_schedule = m_scheduleService.findByPK(m_liningRingConstruction.getScheduleId());
			m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();

			if (m_liningRingConstruction != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String liningRingConstructionUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_liningRingConstruction_model,
		      Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}

		try {
			m_scheduleService.updateSchedule(m_schedule);
			updateDefaultState(m_liningRingConstruction);
			int count = m_liningRingConstructionService.updateLiningRingConstruction(m_liningRingConstruction);
			if (count > 0) {
				Log log = createLog(Modules.s_liningRingConstruction_model, Operation.s_operation_update,
				      m_liningRingConstruction);

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

	public String queryAllLiningRingBlocks() {
		m_liningRingConstruction = m_liningRingConstructionService.findByPK(m_liningRingConstructionId);
		int liningRingId = m_liningRingConstruction.getLiningRingId();
		m_liningRingBlocks = m_liningRingBlockService.queryByLiningRingId(liningRingId);

		return SUCCESS;
	}

	public String queryAllLiningRingConstruction() {
		m_liningRingConstructions = m_liningRingConstructionService.queryLimitedLiningRingConstructions(m_tunnelId,
		      m_tunnelSectionId, 0, Integer.MAX_VALUE);

		return SUCCESS;
	}

	public String queryAllLiningRingConstructions() {
		m_liningRingConstructions = m_liningRingConstructionService.queryLimitedLiningRingConstructions(m_tunnelId,
		      m_tunnelSectionId, 0, Integer.MAX_VALUE);

		return SUCCESS;
	}

	public void setLiningRingBlockService(LiningRingBlockService liningRingBlockService) {
		m_liningRingBlockService = liningRingBlockService;
	}

	public void setLiningRingConstruction(LiningRingConstruction liningRingConstruction) {
		m_liningRingConstruction = liningRingConstruction;
	}

	public void setLiningRingConstructionId(int liningRingConstructionId) {
		m_liningRingConstructionId = liningRingConstructionId;
	}

	public void setLiningRingConstructionService(LiningRingConstructionService liningRingConstructionService) {
		m_liningRingConstructionService = liningRingConstructionService;
	}

	public void setLiningRingService(LiningRingService liningRingService) {
		m_liningRingService = liningRingService;
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

	public void setLongitudinalOpenState(String[] longitudinalOpenState) {
   	m_longitudinalOpenState = longitudinalOpenState;
   }

	public void setGirthOpenState(String[] girthOpenState) {
		m_girthOpenState = girthOpenState;
   }

	public void setGirthFaultState(String[] girthFaultState) {
   	m_girthFaultState = girthFaultState;
   }

	public void setLongitudinalFaultState(String[] longitudinalFaultState) {
   	m_longitudinalFaultState = longitudinalFaultState;
   }

	public void setCoverLossState(String[] coverLossState) {
   	m_coverLossState = coverLossState;
   }

	public void setCracksState(String[] cracksState) {
   	m_cracksState = cracksState;
   }

}