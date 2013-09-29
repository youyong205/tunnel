package com.girthOpen;

import java.util.Date;

import com.liningRingConstruction.LiningRingConstruction;
import com.tunnel.Tunnel;
import com.tunnelSection.TunnelSection;

public class GirthOpen {
	private int m_id;

	private int m_tunnelId;

	private int m_tunnelSectionId;

	private int m_liningRingConstructionId;

	private int m_type;

	private int m_serious;

	private int m_blockIndex;

	private Tunnel m_tunnel;

	private TunnelSection m_tunnelSection;

	private LiningRingConstruction m_liningRingConstruction;

	private String m_measuringPoing;

	private double m_value;

	private Date m_date;

	private String m_des;

	private Date m_creationDate;

	private Date m_modifyDate;

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

	public int getId() {
		return m_id;
	}

	public LiningRingConstruction getLiningRingConstruction() {
		return m_liningRingConstruction;
	}

	public int getLiningRingConstructionId() {
		return m_liningRingConstructionId;
	}

	public String getMeasuringPoing() {
		return m_measuringPoing;
	}

	public Date getModifyDate() {
		return m_modifyDate;
	}

	public int getSerious() {
		return m_serious;
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

	public int getType() {
		return m_type;
	}

	public double getValue() {
		return m_value;
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

	public void setId(int id) {
		m_id = id;
	}

	public void setLiningRingConstruction(LiningRingConstruction liningRingConstruction) {
		m_liningRingConstruction = liningRingConstruction;
	}

	public void setLiningRingConstructionId(int liningRingConstructionId) {
		m_liningRingConstructionId = liningRingConstructionId;
	}

	public void setMeasuringPoing(String measuringPoing) {
		m_measuringPoing = measuringPoing;
	}

	public void setModifyDate(Date modifyDate) {
		m_modifyDate = modifyDate;
	}

	public void setSerious(int serious) {
		m_serious = serious;
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

	public void setType(int type) {
		m_type = type;
	}

	public void setValue(double value) {
		m_value = value;
	}

	@Override
	public String toString() {
		return "GirthOpen [m_id=" + m_id + ", m_tunnelId=" + m_tunnelId + ", m_tunnelSectionId=" + m_tunnelSectionId
		      + ", liningRingConstructionId=" + m_liningRingConstructionId + ", m_MeasuringPoing=" + m_measuringPoing
		      + ", m_value=" + m_value + ", m_date=" + m_date + ", m_des=" + m_des + ", m_creationDate=" + m_creationDate
		      + ", m_modifyDate=" + m_modifyDate + "]";
	}

}
