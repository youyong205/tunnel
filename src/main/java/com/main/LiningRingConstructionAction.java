package com.main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.Authority;
import com.Modules;
import com.Operation;
import com.ScheduledAction;
import com.liningRing.LiningRing;
import com.liningRing.LiningRingBlock;
import com.liningRing.LiningRingBlockService;
import com.liningRing.LiningRingGraph;
import com.liningRing.LiningRingService;
import com.liningRingConstruction.LiningRingConstruction;
import com.liningRingConstruction.LiningRingConstructionService;
import com.main.TunnelSectionState.LiningRingState;
import com.tunnel.Tunnel;
import com.tunnel.TunnelService;
import com.tunnelSection.TunnelSection;
import com.tunnelSection.TunnelSectionService;

public class LiningRingConstructionAction extends ScheduledAction {

	private static final long serialVersionUID = 2802256599534299998L;

	private Logger m_logger = Logger.getLogger(LiningRingConstructionAction.class);

	private List<LiningRingConstruction> m_liningRingConstructions;

	private int m_liningRingConstructionId;

	private LiningRingConstructionService m_liningRingConstructionService;

	private LiningRingService m_liningRingService;

	private LiningRingConstruction m_liningRingConstruction = new LiningRingConstruction();

	private int m_tunnelId;

	private int m_tunnelSectionId;

	private TunnelSection m_tunnelSection;

	private TunnelService m_tunnelService;

	private LiningRingBlockService m_liningRingBlockService;

	private TunnelSectionService m_tunnelSectionService;

	private List<Tunnel> m_tunnels;

	private List<TunnelSection> m_tunnelSections;

	private List<LiningRing> m_liningRings;

	private Map<String, String> m_upSvgs = new LinkedHashMap<String, String>();

	private Map<String, String> m_downSvgs = new LinkedHashMap<String, String>();

	private Map<String, Integer> m_upCounts;

	private Map<String, Integer> m_downCounts;

	private LiningRingGraph m_liningRingGraph;

	private String m_type;

	private String m_typeDes;

	public String buildSvgTitle(int index, int fixSize, int length) {
		String title = (fixSize * index + 1) + "-" + (fixSize * index + length);

		return title;
	}

	@Override
	public String getActionModule() {
		return Modules.s_liningRingConstruction_model;
	}

	public Map<String, Integer> getDownCounts() {
		return m_downCounts;
	}

	public Map<String, String> getDownSvgs() {
		return m_downSvgs;
	}

	public LiningRingConstruction getLiningRingConstruction() {
		return m_liningRingConstruction;
	}

	public List<LiningRingConstruction> getLiningRingConstructions() {
		return m_liningRingConstructions;
	}

	public LiningRingGraph getLiningRingGraph() {
		return m_liningRingGraph;
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

	public TunnelSection getTunnelSection() {
		return m_tunnelSection;
	}

	public int getTunnelSectionId() {
		return m_tunnelSectionId;
	}

	public List<TunnelSection> getTunnelSections() {
		return m_tunnelSections;
	}

	public String getType() {
		return m_type;
	}

	public String getTypeDes() {
		return m_typeDes;
	}

	public Map<String, Integer> getUpCounts() {
		return m_upCounts;
	}

	public Map<String, String> getUpSvgs() {
		return m_upSvgs;
	}

	public String liningRingConstructionDetail() {
		try {
			m_liningRings = m_liningRingService.queryAllLiningRings();
			m_liningRingConstruction = m_liningRingConstructionService.findByPK(m_liningRingConstructionId);
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(
			      m_liningRingConstruction.getTunnelId(), 0, Integer.MAX_VALUE);
			m_schedule = m_scheduleService.findByPK(m_liningRingConstruction.getScheduleId());
			m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();

			LiningRing ring = m_liningRingService.findByPK(m_liningRingConstruction.getLiningRingId());
			List<LiningRingBlock> liningRingBlocks = m_liningRingBlockService.queryByLiningRingId(ring.getId());

			m_liningRingGraph = new LiningRingGraph(ring.getAngle()).addBlocksInfo(liningRingBlocks);

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

			if (m_tunnelSectionId == 0 && m_tunnelSections != null && m_tunnelSections.size() > 0) {
				m_tunnelSectionId = m_tunnelSections.get(0).getId();
			}
			if (m_tunnelSectionId > 0) {
				m_tunnelSection = m_tunnelSectionService.findByPK(m_tunnelSectionId);
				m_liningRingConstructions = m_liningRingConstructionService.queryLimitedLiningRingConstructions(m_tunnelId,
				      m_tunnelSectionId, 0, Integer.MAX_VALUE);
			} else {
				m_liningRingConstructions = new ArrayList<LiningRingConstruction>();
			}

			if (m_liningRingConstructions.size() < 100) {
				for (int i = 0; i < 2090; i++) {
					LiningRingConstruction item = new LiningRingConstruction();
					item.setName("test");
					if (i < 1050) {
						item.setLineType("上行");
					} else {
						item.setLineType("下行");
					}
					m_liningRingConstructions.add(item);
				}
			}

			TunnelSectionState state = new TunnelSectionState(m_liningRingConstructions, m_type);
			TunnelSectionStateBuilder builder = new TunnelSectionStateBuilder();

			List<List<LiningRingState>> ups = state.getUpStates();
			List<List<LiningRingState>> downs = state.getDownStates();

			int size = state.getSize();
			int upLength = ups.size();
			for (int i = 0; i < upLength; i++) {
				List<LiningRingState> states = ups.get(i);
				m_upSvgs.put(buildSvgTitle(i, size, states.size()), builder.buildXml(states));
			}

			int downLength = downs.size();
			for (int i = 0; i < downLength; i++) {
				List<LiningRingState> states = downs.get(i);
				m_downSvgs.put(buildSvgTitle(i, size, states.size()), builder.buildXml(states));
			}
			m_upCounts = state.getUpCounts();
			m_downCounts = state.getDownCounts();
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
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
	
	public int getLiningRingConstructionId() {
   	return m_liningRingConstructionId;
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

	public void setType(String type) {
		m_type = type;
	}

	public void setTypeDes(String typeDes) {
		m_typeDes = typeDes;
	}

}