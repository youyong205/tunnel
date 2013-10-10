package com.workingWell;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.Modules;
import com.inspection.InspectionAction;

public class WorkingWellInspectionAction extends InspectionAction {

	private static final long serialVersionUID = -2259743688239747523L;

	@Autowired
	private WorkingWellService m_workingWellService;

	private List<Item> m_items;

	@Override
	public String getActionModule() {
		return Modules.s_workingWell_inspection_model;
	}

	@Override
	public String getComponentNameById(int id) {
		WorkingWell channel = m_workingWellService.findByPK(id);
		if (channel != null) {
			return channel.getName();
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
		return Modules.s_workingWell_model;
	}

	@Override
	public String inspectionAdd() {
		validateTunnelId();
		m_items = queryItems();
		return super.inspectionAdd();
	}

	@Override
	public String inspectionList() {
		validateTunnelId();
		m_items = queryItems();
		return super.inspectionList();
	}

	@Override
	public String inspectionUpdate() {
		validateTunnelId();
		m_items = queryItems();
		return super.inspectionUpdate();
	}

	private List<Item> queryItems() {
		List<WorkingWell> channels = m_workingWellService.queryLimitedWorkingWellsByTunnelId(m_tunnelId, 0,
		      Integer.MAX_VALUE);
		List<Item> items = new ArrayList<Item>();

		for (WorkingWell channel : channels) {
			Item item = new Item();

			item.setId(channel.getId());
			item.setName(channel.getName());
			items.add(item);
		}
		return items;
	}

	public void setWorkingWellService(WorkingWellService workingWellService) {
		m_workingWellService = workingWellService;
	}

}
