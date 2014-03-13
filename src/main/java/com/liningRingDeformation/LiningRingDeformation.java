package com.liningRingDeformation;

import java.util.Date;

import com.liningRingConstruction.LiningRingConstruction;
import com.tunnel.Tunnel;
import com.tunnelSection.TunnelSection;

public class LiningRingDeformation {
	private int m_id;

	private int m_tunnelId;

	private int m_tunnelSectionId;

	private int m_liningRingConstructionId;

	private Tunnel m_tunnel;

	private TunnelSection m_tunnelSection;

	private LiningRingConstruction m_liningRingConstruction;

	private String m_measuringPoing;

	private double m_value;

	private double m_maxLength;

	private double m_minLength;

	private double m_angle;

	private Date m_date;

	private String m_des;

	private Date m_creationDate;

	private Date m_modifyDate;

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

	public double getValue() {
		return m_value;
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

	public void setValue(double value) {
		m_value = value;
	}

	public double getMaxLength() {
		return m_maxLength;
	}

	public void setMaxLength(double maxLength) {
		m_maxLength = maxLength;
	}

	public double getMinLength() {
		return m_minLength;
	}

	public void setMinLength(double minLength) {
		m_minLength = minLength;
	}

	public double getAngle() {
		return m_angle;
	}

	public void setAngle(double angle) {
		m_angle = angle;
	}

	@Override
	public String toString() {
		return "LiningRingDeformation [m_id=" + m_id + ", m_tunnelId=" + m_tunnelId + ", m_tunnelSectionId="
		      + m_tunnelSectionId + ", m_liningRingConstructionId=" + m_liningRingConstructionId + ", m_tunnel="
		      + m_tunnel + ", m_tunnelSection=" + m_tunnelSection + ", m_liningRingConstruction="
		      + m_liningRingConstruction + ", m_measuringPoing=" + m_measuringPoing + ", m_value=" + m_value
		      + ", m_maxLength=" + m_maxLength + ", m_minLength=" + m_minLength + ", m_angle=" + m_angle + ", m_date="
		      + m_date + ", m_des=" + m_des + "]";
	}

}
