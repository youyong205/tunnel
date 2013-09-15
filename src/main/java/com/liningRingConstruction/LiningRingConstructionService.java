package com.liningRingConstruction;

import java.util.List;

public interface LiningRingConstructionService {

	public int deleteLiningRingConstruction(int id);

	public LiningRingConstruction findByName(String name);

	public LiningRingConstruction findByPK(int id);

	public int insertLiningRingConstruction(LiningRingConstruction liningRingConstruction);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId);

	public List<LiningRingConstruction> queryLimitedLiningRingConstructions(int tunnelId, int tunnelSectionId, int start,
	      int size);

	public int updateLiningRingConstruction(LiningRingConstruction liningRingConstruction);

}
