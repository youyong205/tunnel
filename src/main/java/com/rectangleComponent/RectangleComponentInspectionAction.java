package com.rectangleComponent;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.Constrants;
import com.inspection.InspectionAction;

public class RectangleComponentInspectionAction extends InspectionAction {

	private static final long serialVersionUID = -2259743688239747523L;

	@Autowired
	private RectangleComponentService m_rectangleComponentService;

	private List<Item> m_items;

	@Override
	public String getActionModule() {
		return Constrants.s_rectangleComponent_inspection_model;
	}

	@Override
	public String getComponentNameById(int id) {
		RectangleComponent rectangleComponent = m_rectangleComponentService.findByPK(id);
		if (rectangleComponent != null) {
			return rectangleComponent.getName();
		} else {
			return "Default";
		}
	}

	@Override
	public List<Item> getItems() {
		return m_items;
	}

	@Override
	public String getModule() {
		return Constrants.s_rectangleComponent_model;
	}

	@Override
	public String inspectionAdd() {
		if (m_tunnelId == 0) {
			m_tunnelId = m_tunnelService.queryDefaultTunnelId();
		}
		m_tunnels = m_tunnelService.queryAllTunnels();
		m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);

		if (m_tunnelSectionId == 0) {
			if (m_tunnelSections.size() > 0) {
				m_tunnelSectionId = m_tunnelSections.get(0).getId();
			}
		}
		m_items = queryItems();
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
		m_tunnelSectionId = m_inspection.getTunnelSectionId();
		m_tunnels = m_tunnelService.queryAllTunnels();
		m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);
		m_items = queryItems();
		return result;
	}

	private List<Item> queryItems() {
		List<RectangleComponent> rectangleComponents = m_rectangleComponentService.queryLimitedRectangleComponents(
		      m_tunnelId, m_tunnelSectionId, 0, Integer.MAX_VALUE);
		List<Item> items = new ArrayList<Item>();

		for (RectangleComponent channel : rectangleComponents) {
			Item item = new Item();

			item.setId(channel.getId());
			item.setName(channel.getName());
			items.add(item);
		}
		return items;
	}

	public void setRectangleComponentService(RectangleComponentService rectangleComponentService) {
		m_rectangleComponentService = rectangleComponentService;
	}

}
