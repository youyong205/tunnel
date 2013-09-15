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

	private double m_changeValue;

	private double m_cumulativeValue;

	private Date m_date;

	private String m_des;

	private Date m_creationDate;

	private Date m_modifyDate;

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

	public String getMeasuringPoing() {
		return m_measuringPoing;
	}

	public void setMeasuringPoing(String measuringPoing) {
		m_measuringPoing = measuringPoing;
	}

	public double getValue() {
		return m_value;
	}

	public void setValue(double value) {
		m_value = value;
	}

	public double getChangeValue() {
		return m_changeValue;
	}

	public void setChangeValue(double changeValue) {
		m_changeValue = changeValue;
	}

	public double getCumulativeValue() {
		return m_cumulativeValue;
	}

	public void setCumulativeValue(double cumulativeValue) {
		m_cumulativeValue = cumulativeValue;
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

	@Override
	public String toString() {
		return "LiningRingDeformation [m_id=" + m_id + ", m_tunnelId=" + m_tunnelId + ", m_tunnelSectionId="
		      + m_tunnelSectionId + ", liningRingConstructionId=" + m_liningRingConstructionId + ", m_MeasuringPoing=" + m_measuringPoing
		      + ", m_value=" + m_value + ", m_changeValue=" + m_changeValue + ", m_cumulativeValue=" + m_cumulativeValue
		      + ", m_date=" + m_date + ", m_des=" + m_des + ", m_creationDate=" + m_creationDate + ", m_modifyDate="
		      + m_modifyDate + "]";
	}

}
