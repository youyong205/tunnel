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

	private double m_sequence;

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

	private int m_deformationId;

	private int m_longitudinalDeformationId;

	private int m_girthOpenId;

	private int m_longitudinalOpenId;

	private int m_girthFaultId;

	private int m_longitudinalFaultId;

	private int m_coverLossId;

	private int m_settlementId;

	private int m_seepageId;

	private int m_cracksId;

	private int m_rustId;

	public int getDeformationId() {
		return m_deformationId;
	}

	public void setDeformationId(int deformationId) {
		m_deformationId = deformationId;
	}

	public int getLongitudinalDeformationId() {
		return m_longitudinalDeformationId;
	}

	public void setLongitudinalDeformationId(int longitudinalDeformationId) {
		m_longitudinalDeformationId = longitudinalDeformationId;
	}

	public int getGirthOpenId() {
		return m_girthOpenId;
	}

	public void setGirthOpenId(int girthOpenId) {
		m_girthOpenId = girthOpenId;
	}

	public int getLongitudinalOpenId() {
		return m_longitudinalOpenId;
	}

	public void setLongitudinalOpenId(int longitudinalOpenId) {
		m_longitudinalOpenId = longitudinalOpenId;
	}

	public int getGirthFaultId() {
		return m_girthFaultId;
	}

	public void setGirthFaultId(int girthFaultId) {
		m_girthFaultId = girthFaultId;
	}

	public int getLongitudinalFaultId() {
		return m_longitudinalFaultId;
	}

	public void setLongitudinalFaultId(int longitudinalFaultId) {
		m_longitudinalFaultId = longitudinalFaultId;
	}

	public int getCoverLossId() {
		return m_coverLossId;
	}

	public void setCoverLossId(int coverLossId) {
		m_coverLossId = coverLossId;
	}

	public int getSettlementId() {
		return m_settlementId;
	}

	public void setSettlementId(int settlementId) {
		m_settlementId = settlementId;
	}

	public int getSeepageId() {
		return m_seepageId;
	}

	public void setSeepageId(int seepageId) {
		m_seepageId = seepageId;
	}

	public int getCracksId() {
		return m_cracksId;
	}

	public void setCracksId(int cracksId) {
		m_cracksId = cracksId;
	}

	public int getRustId() {
		return m_rustId;
	}

	public void setRustId(int rustId) {
		m_rustId = rustId;
	}

	public String getTotalState() {
		String state = m_deformationState + ',' + m_longitudinalDeformationState + ',' + m_girthOpenState + ','
		      + m_longitudinalOpenState + ',' + m_girthFaultState + ',' + m_longitudinalFaultState + ','
		      + m_coverLossState + ',' + m_cracksState;

		return computeLevel(state);
	}

	private String computeLevel(String str) {
		if (str == null) {
			return Level.A.getName();
		} else {
			if (str.indexOf(Level.E.getName()) > -1) {
				return Level.E.getName();
			}
			if (str.indexOf(Level.D.getName()) > -1) {
				return Level.D.getName();
			}
			if (str.indexOf(Level.C.getName()) > -1) {
				return Level.C.getName();
			}
			if (str.indexOf(Level.B.getName()) > -1) {
				return Level.B.getName();
			}
			if (str.indexOf(Level.A.getName()) > -1) {
				return Level.A.getName();
			}
			return Level.NULL.getName();
		}
	}

	public String getComputingStaff() {
		return m_computingStaff;
	}

	public String getCoverLossState() {
		return m_coverLossState;
	}

	public String getCracksState() {
		return m_cracksState;
	}

	public RingState getCoverLossRingState() {
		String state = computeLevel(m_coverLossState);
		RingState ringState = new RingState(state, m_coverLossState);

		return ringState;
	}

	public RingState getCracksRingState() {
		String state = computeLevel(m_cracksState);
		RingState ringState = new RingState(state, m_cracksState);

		return ringState;
	}

	public Date getCreationDate() {
		return m_creationDate;
	}

	public RingState getDeformationRingState() {
		String state = computeLevel(m_deformationState);
		RingState ringState = new RingState(state, m_deformationState);

		return ringState;
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

	public String getGirthFaultState() {
		return m_girthFaultState;
	}

	public String getGirthOpenState() {
		return m_girthOpenState;
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

	public String getLongitudinalDeformationState() {
		return m_longitudinalDeformationState;
	}

	public RingState getLongitudinalDeformationRingState() {
		String state = computeLevel(m_longitudinalDeformationState);
		RingState ringState = new RingState(state, m_longitudinalDeformationState);

		return ringState;
	}

	public String getLongitudinalFaultState() {
		return m_longitudinalFaultState;
	}

	public String getLongitudinalOpenState() {
		return m_longitudinalOpenState;
	}

	public RingState getGirthFaultRingState() {
		String state = computeLevel(m_girthFaultState);
		RingState ringState = new RingState(state, m_girthFaultState);

		return ringState;
	}

	public RingState getGirthOpenRingState() {
		String state = computeLevel(m_girthOpenState);
		RingState ringState = new RingState(state, m_girthOpenState);

		return ringState;
	}

	public RingState getLongitudinalFaultRingState() {
		String state = computeLevel(m_longitudinalFaultState);
		RingState ringState = new RingState(state, m_longitudinalFaultState);

		return ringState;
	}

	public RingState getLongitudinalOpenRingState() {
		String state = computeLevel(m_longitudinalOpenState);
		RingState ringState = new RingState(state, m_longitudinalOpenState);

		return ringState;
	}

	public String getMaxGirthFaultState() {
		return computeLevel(m_girthFaultState);
	}

	public String getMaxGirthOpenState() {
		return computeLevel(m_girthOpenState);
	}

	public String getMaxLongitudinalFaultState() {
		return computeLevel(m_longitudinalFaultState);
	}

	public String getMaxLongitudinalOpenState() {
		return computeLevel(m_longitudinalOpenState);
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

	public void setCoverLossState(String coverLossState) {
		m_coverLossState = coverLossState;
	}

	public void setCracksState(String cracksState) {
		m_cracksState = cracksState;
	}

	public void setCreationDate(Date creationDate) {
		m_creationDate = creationDate;
	}

	public void setDeformationState(String deformationState) {
		m_deformationState = deformationState;
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

	public void setGirthFaultState(String girthFaultState) {
		m_girthFaultState = girthFaultState;
	}

	public void setGirthOpenState(String girthOpenState) {
		m_girthOpenState = girthOpenState;
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

	public void setLongitudinalDeformationState(String longitudinalDeformationState) {
		m_longitudinalDeformationState = longitudinalDeformationState;
	}

	public void setLongitudinalFaultState(String longitudinalFaultState) {
		m_longitudinalFaultState = longitudinalFaultState;
	}

	public void setLongitudinalOpenState(String longitudinalOpenState) {
		m_longitudinalOpenState = longitudinalOpenState;
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

	public double getSequence() {
		return m_sequence;
	}

	public void setSequence(double sequence) {
		m_sequence = sequence;
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
