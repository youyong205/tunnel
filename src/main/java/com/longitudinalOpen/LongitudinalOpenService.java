package com.longitudinalOpen;

import java.util.Date;
import java.util.List;

public interface LongitudinalOpenService {

	public int deleteLongitudinalOpen(int id);

	public LongitudinalOpen findByName(String name);

	public LongitudinalOpen findByPK(int id);

	public int insertLongitudinalOpen(LongitudinalOpen longitudinalOpen);

	public LongitudinalOpen queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public List<LongitudinalOpen> queryLimitedLongitudinalOpens(int tunnelId, int tunnelSectionId,
	      int liningRingConstructionId, int start, int size);

	public List<LongitudinalOpen> queryLongitudinalOpenByDuration(int liningRingConstructionId, Date start,
	      Date end);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public int updateLongitudinalOpen(LongitudinalOpen longitudinalOpen);

}
