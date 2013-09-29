package com.liningRingConstruction;

public class LingRingConstructionState {

	private String m_deformationState;

	private String m_longitudinalDeformationState;

	private String m_girthOpenState;

	private RingState m_longitudinalOpenState;

	private RingState m_girthFaultState;

	private RingState m_longitudinalFaultState;

	private RingState m_coverLossState;

	private RingState m_cracksState;

	public LingRingConstructionState(LiningRingConstruction construction) {
		m_deformationState = construction.getDeformationState();
		m_longitudinalDeformationState = construction.getLongitudinalDeformationState();
		m_girthOpenState = construction.getGirthOpenState();
		m_longitudinalOpenState = construction.getLongitudinalOpenRingState();
		m_girthFaultState = construction.getGirthFaultRingState();
		m_longitudinalFaultState = construction.getLongitudinalFaultRingState();
		m_coverLossState = construction.getCoverLossRingState();
		m_cracksState = construction.getCracksRingState();
	}
}
