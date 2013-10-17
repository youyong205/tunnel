package com.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tunnelSection.TunnelSection;
import com.workingWell.WorkingWell;

public class TunnelStateBuilder {
	private final int SECTION_WIDTH = 50;

	private final int SECTION_HEIGHT = 20;

	private final int WORKING_WIDTH = 30;

	private final int WORKING_HEIGHT = 50;

	private final String GREEN = "green";

	private final String BLUE = "blue";

	private final String ORANGE = "orange";

	private final String YELLOW = "yellow";

	private final String RED = "red";

	private Map<String, String> m_colors = new HashMap<String, String>();

	public TunnelStateBuilder() {
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
		String format = "<svg width='100%' height='" + WORKING_HEIGHT + "' version='1.1'"
		      + "xmlns='http://www.w3.org/2000/svg'>";

		return format;
	}

	private String buildLink(TunnelSection tunnelSection) {
		String format = " <a xlink:href='userTunnelSectionState.do?tunnelId=%d&tunnelSectionId=%d'>";

		return String.format(format, tunnelSection.getTunnelId(), tunnelSection.getId());
	}

	private String buildLink(WorkingWell workingWell) {
		String format = " <a xlink:href='userWorkingWellDetail.do?tunnelId=%d&workingWellId=%d'>";

		return String.format(format, workingWell.getTunnelId(), workingWell.getId());
	}

	private String buildLinkFoot() {
		return " </a>";
	}

	private String buildRectangle(int offset, TunnelSection tunnelSection) {
		String format = "<rect x='%d' y='30' width='%d' height='%d'" + " style='fill:%s;stroke:pink;stroke-width:2;"
		      + "opacity:0.9'/>";

		return String.format(format, offset, SECTION_WIDTH, SECTION_HEIGHT,
		      m_colors.get(tunnelSection.getState()));
	}

	private String buildRectangle(int offset, WorkingWell workingWell) {
		String format = "<rect x='%d' y='00' width='%d' height='%d'" + " style='fill:%s;stroke:pink;stroke-width:2;"
		      + "opacity:0.9'/>";

		return String.format(format, offset, WORKING_WIDTH, WORKING_HEIGHT, GREEN);
	}

	public String buildXml(List<Object> objs) {
		StringBuilder sb = new StringBuilder(1024);

		sb.append(buildHeader());

		int length = objs.size();
		int offset = 0;
		for (int i = 0; i < length; i++) {
			Object obj = objs.get(i);
			
			if (obj instanceof TunnelSection) {
				TunnelSection section = (TunnelSection) obj;
				sb.append(buildLink(section));
				sb.append(buildRectangle(offset, section));
				sb.append(buildLinkFoot());
				offset +=SECTION_WIDTH;
			} else if (obj instanceof WorkingWell) {
				WorkingWell workingWell = (WorkingWell) obj;
				sb.append(buildLink(workingWell));
				sb.append(buildRectangle(offset, workingWell));
				sb.append(buildLinkFoot());
				offset +=WORKING_WIDTH;
			}
		}
		sb.append(buildFooter());

		return sb.toString();
	}

}
