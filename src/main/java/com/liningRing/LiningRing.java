package com.liningRing;

import java.util.Date;

public class LiningRing {

	private int m_id;

	private String m_name;

	private String m_type;

	private String m_pipeShape;

	private int m_pipeNumber;

	private double m_pipeThickness;

	private double m_pipeWidth;

	private int m_wedgeNumber;

	private double m_angle;
	
	private String m_des;

	private Date m_creationDate;

	private Date m_modifyDate;

	public Date getCreationDate() {
		return m_creationDate;
	}

	public String getDes() {
		return m_des;
	}

	public int getId() {
		return m_id;
	}

	public Date getModifyDate() {
		return m_modifyDate;
	}

	public String getName() {
		return m_name;
	}

	public int getPipeNumber() {
		return m_pipeNumber;
	}

	public String getPipeShape() {
		return m_pipeShape;
	}

	public double getPipeThickness() {
		return m_pipeThickness;
	}

	public double getPipeWidth() {
		return m_pipeWidth;
	}

	public String getType() {
		return m_type;
	}

	public int getWedgeNumber() {
		return m_wedgeNumber;
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

	public void setModifyDate(Date modifyDate) {
		m_modifyDate = modifyDate;
	}

	public void setName(String name) {
		m_name = name;
	}

	public void setPipeNumber(int pipeNumber) {
		m_pipeNumber = pipeNumber;
	}

	public void setPipeShape(String pipeShape) {
		m_pipeShape = pipeShape;
	}

	public void setPipeThickness(double pipeThickness) {
		m_pipeThickness = pipeThickness;
	}

	public void setPipeWidth(double pipeWidth) {
		m_pipeWidth = pipeWidth;
	}

	public void setType(String type) {
		m_type = type;
	}

	public void setWedgeNumber(int wedgeNumber) {
		m_wedgeNumber = wedgeNumber;
	}
	
	public double getAngle() {
   	return m_angle;
   }

	public void setAngle(double angle) {
   	m_angle = angle;
   }

	@Override
   public String toString() {
	   return "LiningRing [m_id=" + m_id + ", m_name=" + m_name + ", m_type=" + m_type + ", m_pipeShape=" + m_pipeShape
	         + ", m_pipeNumber=" + m_pipeNumber + ", m_pipeThickness=" + m_pipeThickness + ", m_pipeWidth="
	         + m_pipeWidth + ", m_wedgeNumber=" + m_wedgeNumber + ", m_des=" + m_des + "]";
   }

}
