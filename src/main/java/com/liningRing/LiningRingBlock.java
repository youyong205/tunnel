package com.liningRing;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class LiningRingBlock {
	private int m_id;

	private int m_liningRingId;

	private int m_blockIndex;

	private double m_angle;

	private String m_color;

	private String m_des;

	private Date m_creationDate;

	private Date m_modifyDate;

	public double getAngle() {
		return m_angle;
	}

	public int getBlockIndex() {
		return m_blockIndex;
	}

	public String getColor() {
		return m_color;
	}

	public Date getCreationDate() {
		return m_creationDate;
	}

	public String getDes() {
		return m_des;
	}

	public int getId() {
		return m_id;
	}

	public int getLiningRingId() {
		return m_liningRingId;
	}

	public Date getModifyDate() {
		return m_modifyDate;
	}

	public void setAngle(double angle) {
		m_angle = angle;
	}

	public void setBlockIndex(int blockIndex) {
		m_blockIndex = blockIndex;
	}

	public void setColor(String color) {
		m_color = color;
	}

	public void setCreationDate(Date creationDate) {
		m_creationDate = creationDate;
	}

	public void setDes(String des) {
		m_des = des;
	}

	public void setId(int id) {
		m_id = id;
	}

	public void setLiningRingId(int liningRingId) {
		m_liningRingId = liningRingId;
	}

	public void setModifyDate(Date modifyDate) {
		m_modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "LiningRingBlock [m_id=" + m_id + ", m_liningRingId=" + m_liningRingId + ", m_blockIndex=" + m_blockIndex
		      + ", m_angle=" + m_angle + ", m_color=" + m_color + ", m_des=" + m_des + "]";
	}

	public boolean validate() {
		return m_blockIndex > 0 && m_angle > 0 && StringUtils.isNotEmpty(m_color);

	}

}
