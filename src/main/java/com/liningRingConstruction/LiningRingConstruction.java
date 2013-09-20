package com.liningRingConstruction;

import java.util.Date;

import com.liningRing.LiningRing;
import com.schedule.Schedule;
import com.tunnel.Tunnel;
import com.tunnelSection.TunnelSection;

public class LiningRingConstruction {
	private int m_id;

	private int m_tunnelId;

	private int m_tunnelSectionId;

	private Tunnel m_tunnel;

	private TunnelSection m_tunnelSection;

	private int m_liningRingId;

	private LiningRing m_liningRing;

	private String m_lineType;

	private String m_name;

	private double m_startPosition;

	private double m_endPosition;

	private double m_positionAngle;

	private int m_scheduleId;

	private Schedule m_schedule;

	private String m_computingStaff;

	private String m_inspectors;

	private String m_surveyors;

	private String m_planeDeviation;

	private String m_elevationDeviation;

	private String m_gapUp;

	private String m_gapDown;

	private String m_gapLeft;

	private String m_gapRight;

	private String m_gap1;

	private String m_gap2;

	private String m_gap3;

	private String m_gap4;

	private String m_gap5;

	private String m_gap6;

	private String m_gap7;

	private String m_gap8;

	private double m_diameter;

	private double m_verticalDiameter;

	private String m_leftUp;

	private String m_leftDown;

	private String m_rightUp;

	private String m_rightDown;

	private String m_des;

	private String m_deformationState;

	private String m_longitudinalDeformationState;

	private String m_girthOpenState;

	private String m_longitudinalOpenState;

	private String m_girthFaultState;

	private String m_longitudinalFaultState;

	private String m_coverLossState;

	private String m_cracksState;

	private Date m_creationDate;

	private Date m_modifyDate;

	public String getComputingStaff() {
		return m_computingStaff;
	}

	public Date getCreationDate() {
		return m_creationDate;
	}

	public String getDes() {
		return m_des;
	}

	public double getDiameter() {
		return m_diameter;
	}

	public String getElevationDeviation() {
		return m_elevationDeviation;
	}

	public double getEndPosition() {
		return m_endPosition;
	}

	public String getGap1() {
		return m_gap1;
	}

	public String getGap2() {
		return m_gap2;
	}

	public String getGap3() {
		return m_gap3;
	}

	public String getGap4() {
		return m_gap4;
	}

	public String getGap5() {
		return m_gap5;
	}

	public String getGap6() {
		return m_gap6;
	}

	public String getGap7() {
		return m_gap7;
	}

	public String getGap8() {
		return m_gap8;
	}

	public String getGapDown() {
		return m_gapDown;
	}

	public String getGapLeft() {
		return m_gapLeft;
	}

	public String getGapRight() {
		return m_gapRight;
	}

	public String getGapUp() {
		return m_gapUp;
	}

	public int getId() {
		return m_id;
	}

	public String getInspectors() {
		return m_inspectors;
	}

	public String getLeftDown() {
		return m_leftDown;
	}

	public String getLeftUp() {
		return m_leftUp;
	}

	public String getLineType() {
		return m_lineType;
	}

	public LiningRing getLiningRing() {
		return m_liningRing;
	}

	public int getLiningRingId() {
		return m_liningRingId;
	}

	public Date getModifyDate() {
		return m_modifyDate;
	}

	public String getName() {
		return m_name;
	}

	public String getPlaneDeviation() {
		return m_planeDeviation;
	}

	public double getPositionAngle() {
		return m_positionAngle;
	}

	public String getRightDown() {
		return m_rightDown;
	}

	public String getRightUp() {
		return m_rightUp;
	}

	public Schedule getSchedule() {
		return m_schedule;
	}

	public int getScheduleId() {
		return m_scheduleId;
	}

	public double getStartPosition() {
		return m_startPosition;
	}

	public String getSurveyors() {
		return m_surveyors;
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

	public double getVerticalDiameter() {
		return m_verticalDiameter;
	}

	public void setComputingStaff(String computingStaff) {
		m_computingStaff = computingStaff;
	}

	public void setCreationDate(Date creationDate) {
		m_creationDate = creationDate;
	}

	public void setDes(String des) {
		m_des = des;
	}

	public void setDiameter(double diameter) {
		m_diameter = diameter;
	}

	public void setElevationDeviation(String elevationDeviation) {
		m_elevationDeviation = elevationDeviation;
	}

	public void setEndPosition(double endPosition) {
		m_endPosition = endPosition;
	}

	public void setGap1(String gap1) {
		m_gap1 = gap1;
	}

	public void setGap2(String gap2) {
		m_gap2 = gap2;
	}

	public void setGap3(String gap3) {
		m_gap3 = gap3;
	}

	public void setGap4(String gap4) {
		m_gap4 = gap4;
	}

	public void setGap5(String gap5) {
		m_gap5 = gap5;
	}

	public void setGap6(String gap6) {
		m_gap6 = gap6;
	}

	public void setGap7(String gap7) {
		m_gap7 = gap7;
	}

	public void setGap8(String gap8) {
		m_gap8 = gap8;
	}

	public void setGapDown(String gapDown) {
		m_gapDown = gapDown;
	}

	public void setGapLeft(String gapLeft) {
		m_gapLeft = gapLeft;
	}

	public void setGapRight(String gapRight) {
		m_gapRight = gapRight;
	}

	public void setGapUp(String gapUp) {
		m_gapUp = gapUp;
	}

	public void setId(int id) {
		m_id = id;
	}

	public void setInspectors(String inspectors) {
		m_inspectors = inspectors;
	}

	public void setLeftDown(String leftDown) {
		m_leftDown = leftDown;
	}

	public void setLeftUp(String leftUp) {
		m_leftUp = leftUp;
	}

	public void setLineType(String lineType) {
		m_lineType = lineType;
	}

	public void setLiningRing(LiningRing liningRing) {
		m_liningRing = liningRing;
	}

	public void setLiningRingId(int liningRingId) {
		m_liningRingId = liningRingId;
	}

	public void setModifyDate(Date modifyDate) {
		m_modifyDate = modifyDate;
	}

	public void setName(String name) {
		m_name = name;
	}

	public void setPlaneDeviation(String planeDeviation) {
		m_planeDeviation = planeDeviation;
	}

	public void setPositionAngle(double positionAngle) {
		m_positionAngle = positionAngle;
	}

	public void setRightDown(String rightDown) {
		m_rightDown = rightDown;
	}

	public void setRightUp(String rightUp) {
		m_rightUp = rightUp;
	}

	public void setSchedule(Schedule schedule) {
		m_schedule = schedule;
	}

	public void setScheduleId(int scheduleId) {
		m_scheduleId = scheduleId;
	}

	public void setStartPosition(double startPosition) {
		m_startPosition = startPosition;
	}

	public void setSurveyors(String surveyors) {
		m_surveyors = surveyors;
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

	public void setVerticalDiameter(double verticalDiameter) {
		m_verticalDiameter = verticalDiameter;
	}

	public String getDeformationState() {
		return m_deformationState;
	}

	public void setDeformationState(String deformationState) {
		m_deformationState = deformationState;
	}

	public String getLongitudinalDeformationState() {
		return m_longitudinalDeformationState;
	}

	public void setLongitudinalDeformationState(String longitudinalDeformationState) {
		m_longitudinalDeformationState = longitudinalDeformationState;
	}

	public String getGirthOpenState() {
		return m_girthOpenState;
	}

	public void setGirthOpenState(String girthOpenState) {
		m_girthOpenState = girthOpenState;
	}

	public String getLongitudinalOpenState() {
		return m_longitudinalOpenState;
	}

	public void setLongitudinalOpenState(String longitudinalOpenState) {
		m_longitudinalOpenState = longitudinalOpenState;
	}

	public String getGirthFaultState() {
		return m_girthFaultState;
	}

	public void setGirthFaultState(String girthFaultState) {
		m_girthFaultState = girthFaultState;
	}

	public String getLongitudinalFaultState() {
		return m_longitudinalFaultState;
	}

	public void setLongitudinalFaultState(String longitudinalFaultState) {
		m_longitudinalFaultState = longitudinalFaultState;
	}

	public String getCoverLossState() {
		return m_coverLossState;
	}

	public void setCoverLossState(String coverLossState) {
		m_coverLossState = coverLossState;
	}

	public String getCracksState() {
		return m_cracksState;
	}

	public void setCracksState(String cracksState) {
		m_cracksState = cracksState;
	}

	@Override
	public String toString() {
		return "LiningRingConstruction [m_id=" + m_id + ", m_tunnelId=" + m_tunnelId + ", m_tunnelSectionId="
		      + m_tunnelSectionId + ", m_liningRingId=" + m_liningRingId + ", m_name=" + m_name + ", m_startPosition="
		      + m_startPosition + ", m_endPosition=" + m_endPosition + ", m_positionAngle=" + m_positionAngle
		      + ", m_scheduleId=" + m_scheduleId + ", m_computingStaff=" + m_computingStaff + ", m_inspectors="
		      + m_inspectors + ", m_surveyors=" + m_surveyors + ", m_planeDeviation=" + m_planeDeviation
		      + ", m_elevationDeviation=" + m_elevationDeviation + ", m_gapUp=" + m_gapUp + ", m_gapDown=" + m_gapDown
		      + ", m_gapLeft=" + m_gapLeft + ", m_gapRight=" + m_gapRight + ", m_gap1=" + m_gap1 + ", m_gap2=" + m_gap2
		      + ", m_gap3=" + m_gap3 + ", m_gap4=" + m_gap4 + ", m_gap5=" + m_gap5 + ", m_gap6=" + m_gap6 + ", m_gap7="
		      + m_gap7 + ", m_gap8=" + m_gap8 + ", m_diameter=" + m_diameter + ", m_verticalDiameter="
		      + m_verticalDiameter + ", m_leftUp=" + m_leftUp + ", m_leftDown=" + m_leftDown + ", m_rightUp=" + m_rightUp
		      + ", m_rightDown=" + m_rightDown + ", m_des=" + m_des + ", m_creationDate=" + m_creationDate
		      + ", m_modifyDate=" + m_modifyDate + "]";
	}

}
