package com.rust;

import java.util.Date;

import com.liningRingConstruction.LiningRingConstruction;
import com.tunnel.Tunnel;
import com.tunnelSection.TunnelSection;

public class Rust {

	private Tunnel m_tunnel;

	private TunnelSection m_tunnelSection;

	private LiningRingConstruction m_liningRingConstruction;

	private int m_id;

	private int m_tunnelId;

	private int m_tunnelSectionId;

	private int m_liningRingConstructionId;

	private int m_blockIndex;

	private String m_shape;

	private double m_area;

	private double m_startAngle;

	private double m_endAngle;

	private Date m_date;

	private String m_des;

	private Date m_creationDate;

	private Date m_modifyDate;

	public Tunnel getTunnel() {
		return m_tunnel;
	}

	public void setTunnel(Tunnel tunnel) {
		m_tunnel = tunnel;
	}

	public TunnelSection getTunnelSection() {
		return m_tunnelSection;
	}

	public void setTunnelSection(TunnelSection tunnelSection) {
		m_tunnelSection = tunnelSection;
	}

	public LiningRingConstruction getLiningRingConstruction() {
		return m_liningRingConstruction;
	}

	public void setLiningRingConstruction(LiningRingConstruction liningRingConstruction) {
		m_liningRingConstruction = liningRingConstruction;
	}

	public int getId() {
		return m_id;
	}

	public void setId(int id) {
		m_id = id;
	}

	public int getTunnelId() {
		return m_tunnelId;
	}

	public void setTunnelId(int tunnelId) {
		m_tunnelId = tunnelId;
	}

	public int getTunnelSectionId() {
		return m_tunnelSectionId;
	}

	public void setTunnelSectionId(int tunnelSectionId) {
		m_tunnelSectionId = tunnelSectionId;
	}

	public int getLiningRingConstructionId() {
		return m_liningRingConstructionId;
	}

	public void setLiningRingConstructionId(int liningRingConstructionId) {
		m_liningRingConstructionId = liningRingConstructionId;
	}

	public int getBlockIndex() {
		return m_blockIndex;
	}

	public void setBlockIndex(int blockIndex) {
		m_blockIndex = blockIndex;
	}

	public String getShape() {
		return m_shape;
	}

	public void setShape(String shape) {
		m_shape = shape;
	}

	public double getArea() {
		return m_area;
	}

	public void setArea(double area) {
		m_area = area;
	}

	public double getStartAngle() {
		return m_startAngle;
	}

	public void setStartAngle(double startAngle) {
		m_startAngle = startAngle;
	}

	public double getEndAngle() {
		return m_endAngle;
	}

	public void setEndAngle(double endAngle) {
		m_endAngle = endAngle;
	}

	public Date getDate() {
		return m_date;
	}

	public void setDate(Date date) {
		m_date = date;
	}

	public String getDes() {
		return m_des;
	}

	public void setDes(String des) {
		m_des = des;
	}

	public Date getCreationDate() {
		return m_creationDate;
	}

	public void setCreationDate(Date creationDate) {
		m_creationDate = creationDate;
	}

	public Date getModifyDate() {
		return m_modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		m_modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "Rust [m_id=" + m_id + ", m_tunnelId=" + m_tunnelId + ", m_tunnelSectionId=" + m_tunnelSectionId
		      + ", m_liningRingConstructionId=" + m_liningRingConstructionId + ", m_blockIndex=" + m_blockIndex
		      + ", m_shape=" + m_shape + ", m_area=" + m_area + ", m_startAngle=" + m_startAngle + ", m_endAngle="
		      + m_endAngle + ", m_date=" + m_date + "]";
	}

}
