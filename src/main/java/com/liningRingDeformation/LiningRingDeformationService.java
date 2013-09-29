package com.liningRingDeformation;

import java.util.Date;
import java.util.List;

public interface LiningRingDeformationService {

	public int deleteLiningRingDeformation(int id);

	public LiningRingDeformation findByName(String name);

	public LiningRingDeformation findByPK(int id);

	public int insertLiningRingDeformation(LiningRingDeformation liningRingDeformation);

	public LiningRingDeformation queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public List<LiningRingDeformation> queryLimitedLiningRingDeformations(int tunnelId, int tunnelSectionId,
	      int liningRingConstructionId, int start, int size);

	public List<LiningRingDeformation> queryLiningRingDeformationByDuration(int liningRingConstructionId, Date start,
	      Date end);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public int updateLiningRingDeformation(LiningRingDeformation liningRingDeformation);

}
