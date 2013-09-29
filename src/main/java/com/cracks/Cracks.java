package com.cracks;

import java.util.Date;

import com.liningRingConstruction.LiningRingConstruction;
import com.tunnel.Tunnel;
import com.tunnelSection.TunnelSection;

public class Cracks {

	private Tunnel m_tunnel;

	private TunnelSection m_tunnelSection;

	private LiningRingConstruction m_liningRingConstruction;

	private int m_id;

	private int m_tunnelId;

	private int m_tunnelSectionId;

	private int m_liningRingConstructionId;

	private int m_blockIndex;

	private int m_number;

	private String m_type;

	private double m_length;

	private double m_width;

	private double m_angle;

	private double m_dip;

	private Date m_date;

	private String m_des;

	private Date m_creationDate;

	private Date m_modifyDate;

	public double getAngle() {
		return m_angle;
	}

	public int getBlockIndex() {
		return m_blockIndex;
	}

	public Date getCreationDate() {
		return m_creationDate;
	}

	public Date getDate() {
		return m_date;
	}

	public String getDes() {
		return m_des;
	}

	public double getDip() {
		return m_dip;
	}

	public int getId() {
		return m_id;
	}

	public double getLength() {
		return m_length;
	}

	public LiningRingConstruction getLiningRingConstruction() {
		return m_liningRingConstruction;
	}

	public int getLiningRingConstructionId() {
		return m_liningRingConstructionId;
	}

	public Date getModifyDate() {
		return m_modifyDate;
	}

	public int getNumber() {
		return m_number;
	}

	public Tunnel getTunnel() {
		return m_tunnel;
	}

	public int getTunnelId() {
		return m_tunnelId;
	}

	public TunnelSection getTunnelSection() {
		return m_tunnelSection;
	}

	public int getTunnelSectionId() {
		return m_tunnelSectionId;
	}

	public String getType() {
		return m_type;
	}

	public double getWidth() {
		return m_width;
	}

	public void setAngle(double angle) {
		m_angle = angle;
	}

	public void setBlockIndex(int blockIndex) {
		m_blockIndex = blockIndex;
	}

	public void setCreationDate(Date creationDate) {
		m_creationDate = creationDate;
	}

	public void setDate(Date date) {
		m_date = date;
	}

	public void setDes(String des) {
		m_des = des;
	}

	public void setDip(double dip) {
		m_dip = dip;
	}

	public void setId(int id) {
		m_id = id;
	}

	public void setLength(double length) {
		m_length = length;
	}

	public void setLiningRingConstruction(LiningRingConstruction liningRingConstruction) {
		m_liningRingConstruction = liningRingConstruction;
	}

	public void setLiningRingConstructionId(int liningRingConstructionId) {
		m_liningRingConstructionId = liningRingConstructionId;
	}

	public void setModifyDate(Date modifyDate) {
		m_modifyDate = modifyDate;
	}

	public void setNumber(int number) {
		m_number = number;
	}

	public void setTunnel(Tunnel tunnel) {
		m_tunnel = tunnel;
	}

	public void setTunnelId(int tunnelId) {
		m_tunnelId = tunnelId;
	}

	public void setTunnelSection(TunnelSection tunnelSection) {
		m_tunnelSection = tunnelSection;
	}

	public void setTunnelSectionId(int tunnelSectionId) {
		m_tunnelSectionId = tunnelSectionId;
	}

	public void setType(String type) {
		m_type = type;
	}

	public void setWidth(double width) {
		m_width = width;
	}

	@Override
	public String toString() {
		return "Cracks [m_id=" + m_id + ", m_tunnelId=" + m_tunnelId + ", m_tunnelSectionId=" + m_tunnelSectionId
		      + ", m_liningRingConstructionId=" + m_liningRingConstructionId + ", m_blockIndex=" + m_blockIndex
		      + ", m_number=" + m_number + ", m_type=" + m_type + ", m_length=" + m_length + ", m_width=" + m_width
		      + ", m_angle=" + m_angle + ", m_dip=" + m_dip + ", m_date=" + m_date + "]";
	}

}
