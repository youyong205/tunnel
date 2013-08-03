package com.saddleWeight;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.Modules;
import com.curing.CuringAction;

public class SaddleWeightCuringAction extends CuringAction {

	private static final long serialVersionUID = -2259743688239747523L;

	@Autowired
	private SaddleWeightService m_saddleWeightService;

	private List<Item> m_items;

	@Override
	public String getActionModule() {
		return Modules.s_saddleWeight_curing_model;
	}

	@Override
	public String getComponentNameById(int id) {
		SaddleWeight saddleWeight = m_saddleWeightService.findByPK(id);
		if (saddleWeight != null) {
			return saddleWeight.getName();
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
		return Modules.s_saddleWeight_model;
	}

	@Override
	public String curingAdd() {
		if (m_tunnelId == 0) {
			m_tunnelId = m_tunnelService.queryDefaultTunnelId();
		}
		m_tunnels = m_tunnelService.queryAllTunnels();
		m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);
		int tunnelSectionId = m_tunnelSectionId;
		
		if (tunnelSectionId == 0 && m_tunnelSections.size() > 0) {
			tunnelSectionId = m_tunnelSections.get(0).getId();
		}
		m_items = queryItems(m_tunnelId,tunnelSectionId);
		return super.curingAdd();
	}

	@Override
	public String curingList() {
		if (m_tunnelId == 0) {
			m_tunnelId = m_tunnelService.queryDefaultTunnelId();
		}
		m_tunnels = m_tunnelService.queryAllTunnels();
		m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);
		return super.curingList();
	}

	@Override
	public String curingUpdate() {
		String result = super.curingUpdate();

		m_tunnelId = m_curing.getTunnelId();
		int tunnelSectionId = m_curing.getTunnelSectionId();
		m_tunnels = m_tunnelService.queryAllTunnels();
		m_tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(m_tunnelId, 0, Integer.MAX_VALUE);
		m_items = queryItems(m_tunnelId, tunnelSectionId);
		return result;
	}

	private List<Item> queryItems(int tunnelId, int tunnelSectionId) {
		List<SaddleWeight> saddleWeights = m_saddleWeightService.queryLimitedSaddleWeights(
		      tunnelId, tunnelSectionId, 0, Integer.MAX_VALUE);
		List<Item> items = new ArrayList<Item>();

		for (SaddleWeight channel : saddleWeights) {
			Item item = new Item();

			item.setId(channel.getId());
			item.setName(channel.getName());
			items.add(item);
		}
		return items;
	}

	public void setSaddleWeightService(SaddleWeightService saddleWeightService) {
		m_saddleWeightService = saddleWeightService;
	}

}
