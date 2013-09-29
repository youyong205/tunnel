package com.bracket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.Modules;
import com.inspection.InspectionAction;

public class BracketInspectionAction extends InspectionAction {

	private static final long serialVersionUID = -2259743688239747523L;

	@Autowired
	private BracketService m_bracketService;

	private List<Item> m_items;

	@Override
	public String getActionModule() {
		return Modules.s_bracket_inspection_model;
	}

	@Override
	public String getComponentNameById(int id) {
		Bracket bracket = m_bracketService.findByPK(id);
		if (bracket != null) {
			return bracket.getName();
		} else {
			return Modules.s_deleted;
		}
	}

	@Override
	public List<Item> getItems() {
		return m_items;
	}

	@Override
	public String getModule() {
		return Modules.s_bracket_model;
	}

	@Override
	public String inspectionAdd() {
		if (m_tunnelId == 0) {
			m_tunnelId = m_tunnelService.queryDefaultTunnelId();
		}
		m_tunnels = m_tunnelService.queryAllTunnels();
		m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);
		int tunnelSectionId = m_tunnelSectionId;

		if (tunnelSectionId == 0 && m_tunnelSections.size() > 0) {
			tunnelSectionId = m_tunnelSections.get(0).getId();
		}
		m_items = queryItems(m_tunnelId, tunnelSectionId);
		return super.inspectionAdd();
	}

	@Override
	public String inspectionList() {
		if (m_tunnelId == 0) {
			m_tunnelId = m_tunnelService.queryDefaultTunnelId();
		}
		m_tunnels = m_tunnelService.queryAllTunnels();
		m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);
		return super.inspectionList();
	}

	@Override
	public String inspectionUpdate() {
		String result = super.inspectionUpdate();

		m_tunnelId = m_inspection.getTunnelId();
		int tunnelSectionId = m_inspection.getTunnelSectionId();
		m_tunnels = m_tunnelService.queryAllTunnels();
		m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);
		m_items = queryItems(m_tunnelId, tunnelSectionId);
		return result;
	}

	private List<Item> queryItems(int tunnelId, int tunnelSectionId) {
		List<Bracket> brackets = m_bracketService.queryLimitedBrackets(tunnelId, tunnelSectionId, 0, Integer.MAX_VALUE);
		List<Item> items = new ArrayList<Item>();

		for (Bracket channel : brackets) {
			Item item = new Item();

			item.setId(channel.getId());
			item.setName(channel.getName());
			items.add(item);
		}
		return items;
	}

	public void setBracketService(BracketService bracketService) {
		m_bracketService = bracketService;
	}

}
