package com.cracks;

import java.util.Date;
import java.util.List;

public interface CracksService {

	public int deleteCracks(int id);

	public Cracks findByName(String name);

	public Cracks findByPK(int id);

	public int insertCracks(Cracks cracks);

	public List<Cracks> queryCracksByDuration(int liningRingConstructionId, Date start,
	      Date end);

	public Cracks queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public List<Cracks> queryLimitedCrackss(int tunnelId, int tunnelSectionId,
	      int liningRingConstructionId, int start, int size);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public int updateCracks(Cracks cracks);

}
