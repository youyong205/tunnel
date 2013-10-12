package com.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.liningRingConstruction.LiningRingConstruction;
import com.tunnelSection.TunnelSection;

public class TunnelState {

	private List<List<TunnelSectionState>> m_states = new ArrayList<List<TunnelSectionState>>();

	private Map<String, Integer> m_counts = new TreeMap<String, Integer>();

	private final int m_size = 18;

	public TunnelState(List<TunnelSection> sections) {
		int length = sections.size();

		for (int i = 0; i < length; i++) {
			TunnelSection section = sections.get(i);

			addSection(m_states, m_counts, section);
		}
	}

	private void addSection(List<List<TunnelSectionState>> allStates, Map<String, Integer> counts,
			TunnelSection section) {
		int size = allStates.size();

		if (size == 0) {
			List<TunnelSectionState> states = new ArrayList<TunnelSectionState>();
			allStates.add(states);
		}

		size = allStates.size();

		List<TunnelSectionState> states = allStates.get(size - 1);
		int length = states.size();

		if (length == m_size) {
			states = new ArrayList<TunnelSectionState>();
			allStates.add(states);
		}

		String state = section.getState();
		Integer count = counts.get(state);

		if (count == null) {
			counts.put(state, 1);
		} else {
			counts.put(state, count + 1);
		}
		states.add(new TunnelSectionState(section.getId(), section.getTunnelId(), section.getName(), state));
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

	public int getSize() {
		return m_size;
	}

	public Map<String, Integer> getCounts() {
		return m_counts;
	}

	public List<List<TunnelSectionState>> getStates() {
		return m_states;
	}

	public static class TunnelSectionState {

		private int m_tunnelId;

		private int m_tunnelSectionId;

		private String m_name;

		private String m_state;

		public TunnelSectionState(int tunnelSectionId, int tunnelId, String name, String state) {
			m_tunnelSectionId = tunnelSectionId;
			m_tunnelId = tunnelId;
			m_name = name;
			m_state = state;
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