package com.liningRingDeformation;

import java.util.List;

public interface LiningRingDeformationService {

	public int deleteLiningRingDeformation(int id);

	public LiningRingDeformation findByName(String name);

	public LiningRingDeformation findByPK(int id);

	public int insertLiningRingDeformation(LiningRingDeformation liningRingDeformation);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingId);

	public List<LiningRingDeformation> queryLimitedLiningRingDeformations(int tunnelId, int tunnelSectionId, int liningRingConstructionId,int start,
	      int size);

	public int updateLiningRingDeformation(LiningRingDeformation liningRingDeformation);

}
