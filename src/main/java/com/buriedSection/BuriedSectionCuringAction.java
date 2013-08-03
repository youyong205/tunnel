package com.buriedSection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.Modules;
import com.curing.CuringAction;

public class BuriedSectionCuringAction extends CuringAction {

	private static final long serialVersionUID = -2259743688239747523L;

	@Autowired
	private BuriedSectionService m_buriedSectionService;

	private List<Item> m_items;

	@Override
	public String curingAdd() {
		validateTunnelId();
		m_items = queryItems();
		return super.curingAdd();
	}

	@Override
	public String curingList() {
		validateTunnelId();
		return super.curingList();
	}

	@Override
	public String curingUpdate() {
		validateTunnelId();
		m_items = queryItems();
		return super.curingUpdate();
	}

	@Override
	public String getActionModule() {
		return Modules.s_buriedSection_curing_model;
	}

	@Override
	public String getComponentNameById(int id) {
		BuriedSection channel = m_buriedSectionService.findByPK(id);
		if (channel != null) {
			return channel.getName();
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
		return Modules.s_buriedSection_model;
	}

	private List<Item> queryItems() {
		List<BuriedSection> channels = m_buriedSectionService.queryLimitedBuriedSectionsByTunnelId(m_tunnelId, 0,
		      Integer.MAX_VALUE);
		List<Item> items = new ArrayList<Item>();

		for (BuriedSection channel : channels) {
			Item item = new Item();

			item.setId(channel.getId());
			item.setName(channel.getName());
			items.add(item);
		}
		return items;
	}

	public void setBuriedSectionService(BuriedSectionService buriedSectionService) {
		m_buriedSectionService = buriedSectionService;
	}

}
