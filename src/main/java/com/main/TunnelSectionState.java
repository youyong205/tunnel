package com.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.Constrants;
import com.liningRingConstruction.LiningRingConstruction;

public class TunnelSectionState {

	private List<List<LiningRingState>> m_upStates = new ArrayList<List<LiningRingState>>();

	private List<List<LiningRingState>> m_downStates = new ArrayList<List<LiningRingState>>();

	private Map<String, Integer> m_upCounts = new TreeMap<String, Integer>();

	private Map<String, Integer> m_downCounts = new TreeMap<String, Integer>();

	private final int m_size = 100;

	public Map<String, Integer> getUpCounts() {
		return m_upCounts;
	}

	public void setUpCounts(Map<String, Integer> upCounts) {
		m_upCounts = upCounts;
	}

	public Map<String, Integer> getDownCounts() {
		return m_downCounts;
	}

	public void setDownCounts(Map<String, Integer> downCounts) {
		m_downCounts = downCounts;
	}

	public List<List<LiningRingState>> getUpStates() {
		return m_upStates;
	}

	public void setUpStates(List<List<LiningRingState>> upStates) {
		m_upStates = upStates;
	}

	public List<List<LiningRingState>> getDownStates() {
		return m_downStates;
	}

	public void setDownStates(List<List<LiningRingState>> downStates) {
		m_downStates = downStates;
	}

	public int getSize() {
		return m_size;
	}

	public TunnelSectionState(List<LiningRingConstruction> constructions, String stateType) {
		int length = constructions.size();

		for (int i = 0; i < length; i++) {
			LiningRingConstruction construction = constructions.get(i);
			String type = construction.getLineType();

			if (Constrants.UP.equals(type)) {
				addLiningRing(m_upStates, m_upCounts, construction, stateType);
			} else {
				addLiningRing(m_downStates, m_downCounts, construction, stateType);
			}
		}
	}

	private void addLiningRing(List<List<LiningRingState>> allStates, Map<String, Integer> counts,
	      LiningRingConstruction construction, String type) {
		int size = allStates.size();

		if (size == 0) {
			List<LiningRingState> states = new ArrayList<LiningRingState>();
			allStates.add(states);
		}

		size = allStates.size();

		List<LiningRingState> states = allStates.get(size - 1);
		int length = states.size();

		if (length == m_size) {
			states = new ArrayList<LiningRingState>();
			allStates.add(states);
		}

		String state = computeState(construction, type);
		Integer count = counts.get(state);

		if (count == null) {
			counts.put(state, 1);
		} else {
			counts.put(state, count + 1);
		}
		states.add(new LiningRingState(construction.getId(), construction.getTunnelId(), construction
		      .getTunnelSectionId(), construction.getName(), state));
	}

	public String computeState(LiningRingConstruction construction, String type) {
		double a = Math.random();

		if (a > 0.4) {
			return "A";
		}
		if (a > 0.3) {
			return "B";
		}
		if (a > 0.2) {
			return "C";
		}
		if (a > 0.1) {
			return "D";
		}
		return "E";
	}

	public static class LiningRingState {

		private int m_id;

		private int m_tunnelId;

		private int m_tunnelSectionId;

		private String m_name;

		private String m_state;

		public LiningRingState(int id, int tunnelId, int tunnelSectionId, String name, String state) {
			m_id = id;
			m_tunnelId = tunnelId;
			m_tunnelSectionId = tunnelSectionId;
			m_name = name;
			m_state = state;
		}

		public int getId() {
			return m_id;
		}

		public String getName() {
			return m_name;
		}

		public String getState() {
			return m_state;
		}

		public int getTunnelId() {
			return m_tunnelId;
		}

		public int getTunnelSectionId() {
			return m_tunnelSectionId;
		}
	}

}
