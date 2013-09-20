package com.liningRingLongitudinalDeformation;

import java.util.Date;
import java.util.List;

public interface LiningRingLongitudinalDeformationService {

	public int deleteLiningRingLongitudinalDeformation(int id);

	public LiningRingLongitudinalDeformation findByName(String name);

	public LiningRingLongitudinalDeformation findByPK(int id);

	public int insertLiningRingLongitudinalDeformation(LiningRingLongitudinalDeformation liningRingLongitudinalDeformation);

	public List<LiningRingLongitudinalDeformation> queryLimitedLiningRingLongitudinalDeformations(int tunnelId, int tunnelSectionId,
	      int liningRingConstructionId, int start, int size);

	public List<LiningRingLongitudinalDeformation> queryLiningRingLongitudinalDeformationByDuration(int liningRingConstructionId, Date start,
	      Date end);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public LiningRingLongitudinalDeformation queryLastestLongitudinalDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public int updateLiningRingLongitudinalDeformation(LiningRingLongitudinalDeformation liningRingLongitudinalDeformation);

}
