package com.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.main.TunnelSectionState.LiningRingState;

public class TunnelSectionStateBuilder {
	private final int WIDTH = 7;

	private final int HEIGHT = 20;

	private final String GREEN = "green";

	private final String BLUE = "blue";

	private final String ORANGE = "orange";

	private final String YELLOW = "yellow";

	private final String RED = "red";

	private Map<String, String> m_colors = new HashMap<String, String>();

	public TunnelSectionStateBuilder() {
		m_colors.put("A", GREEN);
		m_colors.put("B", BLUE);
		m_colors.put("C", ORANGE);
		m_colors.put("D", YELLOW);
		m_colors.put("E", RED);
	}

	private String buildFooter() {
		return "</svg>";
	}

	private String buildHeader() {
		String format = "<svg width='100%' height='" + HEIGHT + "' version='1.1'" + "xmlns='http://www.w3.org/2000/svg'>";

		return format;
	}

	private String buildLink(LiningRingState state) {
		String format = " <a xlink:href='userLiningRingConstructionDetail.do?tunnelId=%d&tunnelSectionId=%d&liningRingConstructionId=%d'>";

		return String.format(format, state.getTunnelId(), state.getTunnelSectionId(), state.getId());
	}

	private String buildLinkFoot() {
		return " </a>";
	}

	private String buildRectangle(int index, LiningRingState state) {
		String format = "<rect x='%d' y='00' width='%d' height='%d'" + " style='fill:%s;stroke:pink;stroke-width:1;"
		      + "opacity:0.9'/>";

		return String.format(format, WIDTH * index, WIDTH, HEIGHT, m_colors.get(state.getState()));
	}

	public String buildXml(List<LiningRingState> states) {
		StringBuilder sb = new StringBuilder(1024);

		sb.append(buildHeader());

		int length = states.size();
		for (int i = 0; i < length; i++) {
			LiningRingState state = states.get(i);
			sb.append(buildLink(state));
			sb.append(buildRectangle(i, state));
			sb.append(buildLinkFoot());
		}
		sb.append(buildFooter());

		return sb.toString();
	}

}
