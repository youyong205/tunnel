package com.liningRingConstruction;

import java.util.List;

import com.coverLoss.CoverLoss;
import com.cracks.Cracks;
import com.girthFault.GirthFault;
import com.girthOpen.GirthOpen;
import com.liningRingDeformation.LiningRingDeformation;
import com.liningRingLongitudinalDeformation.LiningRingLongitudinalDeformation;
import com.longitudinalFault.LongitudinalFault;
import com.longitudinalOpen.LongitudinalOpen;
import com.rust.Rust;
import com.seepage.Seepage;
import com.settlement.Settlement;

public interface LiningRingConstructionService {

	public int deleteLiningRingConstruction(int id);

	public LiningRingConstruction findByName(String name);

	public LiningRingConstruction findByPK(int id);

	public int insertLiningRingConstruction(LiningRingConstruction liningRingConstruction);

	public List<LiningRingConstruction> queryLimitedLiningRingConstructions(int tunnelId, int tunnelSectionId,
	      int start, int size);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId);

	public int updateCoverLossState(CoverLoss coverLoss);

	public int updateCracksState(Cracks cracks);

	public int updateDeformationState(LiningRingDeformation defomation);

	public int updateGirthFaultState(GirthFault girthFault);

	public int updateGirthOpenState(GirthOpen girthOpen);

	public int updateLiningRingConstruction(LiningRingConstruction liningRingConstruction);

	public int updateLongitudinalDeformationState(LiningRingLongitudinalDeformation longitudinalDeformationState);

	public int updateLongitudinalFaultState(LongitudinalFault longitudinalFault);

	public int updateLongitudinalOpenState(LongitudinalOpen longitudinalOpen);

	public int updateRustState(Rust rust);

	public int updateSeepageState(Seepage seepage);

	public int updateSettlementState(Settlement settlement);

}
