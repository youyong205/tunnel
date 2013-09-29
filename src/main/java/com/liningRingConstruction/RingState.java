package com.liningRingConstruction;

import java.util.ArrayList;
import java.util.List;

public class RingState {

	public String m_state;

	public List<BlockState> m_states = new ArrayList<BlockState>();

	public RingState(String state, String states) {
		m_state = state;

		if (states != null) {
			String[] temp = states.split(",");
			int length = temp.length;

			for (int i = 0; i < length; i++) {
				m_states.add(new BlockState(String.valueOf(i + 1), temp[i]));
			}
		}
	}

	public String getState() {
		return m_state;
	}

	public List<BlockState> getStates() {
		return m_states;
	}

	public static class BlockState {

		private String m_id;

		private String m_state;

		public BlockState(String id, String state) {
			m_id = id;
			m_state = state;
		}

		public String getId() {
			return m_id;
		}

		public String getState() {
			return m_state;
		}
	}
}
