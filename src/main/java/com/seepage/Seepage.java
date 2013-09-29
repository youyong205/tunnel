package com.seepage;

import java.util.Date;

import com.liningRingConstruction.LiningRingConstruction;
import com.tunnel.Tunnel;
import com.tunnelSection.TunnelSection;

public class Seepage {

	private int m_id;

	private int m_tunnelId;

	private int m_tunnelSectionId;

	private int m_liningRingConstructionId;

	private int m_blockIndex;

	private String m_shape;

	private double m_size;

	private double m_startAngle;

	private double m_endAngle;

	private int m_affect;

	private Date m_date;

	private String m_des;

	private Date m_creationDate;

	private Date m_modifyDate;

	private Tunnel m_tunnel;

	private TunnelSection m_tunnelSection;

	private LiningRingConstruction m_liningRingConstruction;

	public int getAffect() {
		return m_affect;
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

	public double getEndAngle() {
		return m_endAngle;
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

	public Date getModifyDate() {
		return m_modifyDate;
	}

	public String getShape() {
		return m_shape;
	}

	public double getSize() {
		return m_size;
	}

	public double getStartAngle() {
		return m_startAngle;
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

	public void setAffect(int affect) {
		m_affect = affect;
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

	public void setEndAngle(double endAngle) {
		m_endAngle = endAngle;
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

	public void setModifyDate(Date modifyDate) {
		m_modifyDate = modifyDate;
	}

	public void setShape(String shape) {
		m_shape = shape;
	}

	public void setSize(double size) {
		m_size = size;
	}

	public void setStartAngle(double startAngle) {
		m_startAngle = startAngle;
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
	
	@Override
	public String toString() {
		return "Seepage [m_id=" + m_id + ", m_tunnelId=" + m_tunnelId + ", m_tunnelSectionId=" + m_tunnelSectionId
		      + ", m_liningRingConstructionId=" + m_liningRingConstructionId + ", m_blockIndex=" + m_blockIndex
		      + ", m_shape=" + m_shape + ", m_size=" + m_size + ", m_startAngle=" + m_startAngle + ", m_endAngle="
		      + m_endAngle + ", m_affect=" + m_affect + ", m_date=" + m_date + "]";
	}

}
