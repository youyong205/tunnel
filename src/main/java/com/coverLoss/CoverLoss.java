package com.coverLoss;

import java.util.Date;

import com.liningRingConstruction.LiningRingConstruction;
import com.tunnel.Tunnel;
import com.tunnelSection.TunnelSection;

public class CoverLoss {

	private Tunnel m_tunnel;

	private TunnelSection m_tunnelSection;

	private LiningRingConstruction m_liningRingConstruction;

	private int m_id;

	private int m_tunnelId;

	private int m_tunnelSectionId;

	private int m_liningRingConstructionId;

	private int m_blockIndex;

	private String m_type;

	private String m_shape;

	private double m_width;

	private double m_height;

	private double m_depth;

	private double m_area;

	private Date m_date;

	private String m_des;

	private Date m_creationDate;

	private Date m_modifyDate;

	public double getArea() {
		return m_area;
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

	public double getDepth() {
		return m_depth;
	}

	public String getDes() {
		return m_des;
	}

	public double getHeight() {
		return m_height;
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

	public void setArea(double area) {
		m_area = area;
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

	public void setDepth(double depth) {
		m_depth = depth;
	}

	public void setDes(String des) {
		m_des = des;
	}

	public void setHeight(double height) {
		m_height = height;
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
		return "CoverLoss [m_id=" + m_id + ", m_tunnelId=" + m_tunnelId + ", m_tunnelSectionId=" + m_tunnelSectionId
		      + ", m_liningRingConstructionId=" + m_liningRingConstructionId + ", m_blockIndex=" + m_blockIndex
		      + ", m_type=" + m_type + ", m_shape=" + m_shape + ", m_width=" + m_width + ", m_height=" + m_height
		      + ", m_depth=" + m_depth + ", m_area=" + m_area + ", m_date=" + m_date + ", m_des=" + m_des
		      + ", m_creationDate=" + m_creationDate + ", m_modifyDate=" + m_modifyDate + "]";
	}

}
