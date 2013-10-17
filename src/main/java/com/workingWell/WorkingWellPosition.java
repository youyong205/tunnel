package com.workingWell;

import java.util.Date;

public class WorkingWellPosition {

	private int m_workingWellId;

	private int m_tunnelSectionId;

	private Date m_creationDate;

	public int getWorkingWellId() {
		return m_workingWellId;
	}

	public void setWorkingWellId(int workingWellId) {
		m_workingWellId = workingWellId;
	}

	public int getTunnelSectionId() {
		return m_tunnelSectionId;
	}

	public void setTunnelSectionId(int tunnelSectionId) {
		m_tunnelSectionId = tunnelSectionId;
	}

	public Date getCreationDate() {
		return m_creationDate;
	}

	public void setCreationDate(Date creationDate) {
		m_creationDate = creationDate;
	}

}
