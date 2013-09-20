package com.liningRingConstruction;

import java.util.List;

import com.liningRingDeformation.LiningRingDeformation;
import com.liningRingLongitudinalDeformation.LiningRingLongitudinalDeformation;

public interface LiningRingConstructionService {

	public int deleteLiningRingConstruction(int id);

	public LiningRingConstruction findByName(String name);

	public LiningRingConstruction findByPK(int id);

	public int insertLiningRingConstruction(LiningRingConstruction liningRingConstruction);

	public List<LiningRingConstruction> queryLimitedLiningRingConstructions(int tunnelId, int tunnelSectionId,
	      int start, int size);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId);

	public int updateLiningRingConstruction(LiningRingConstruction liningRingConstruction);

	public int updateDeformationState(LiningRingDeformation defomation);

	public int updateLongitudinalDeformationState(LiningRingLongitudinalDeformation longitudinalDeformationState);

//	public int updateGirthOpenState(GirthOpen girthOpen);
//
//	public int updateLongitudinalOpenStateOpenState(LongitudinalOpen longitudinalOpen);
//
//	public int updateGirthFaultState(GirthFault girthFault);
//
//	public int updateLongitudinalFaultState(LongitudinalFault longitudinalFault);
//
//	public int updateCoverLossState(CoverLoss coverLoss);
//
//	public int updateCracksState(Cracks cracks);

}
