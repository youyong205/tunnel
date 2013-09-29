package com.seepage;

import java.util.Date;
import java.util.List;

public interface SeepageService {

	public int deleteSeepage(int id);

	public Seepage findByName(String name);

	public Seepage findByPK(int id);

	public int insertSeepage(Seepage seepage);

	public Seepage queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public List<Seepage> queryLimitedSeepages(int tunnelId, int tunnelSectionId, int liningRingConstructionId,
	      int start, int size);

	public List<Seepage> querySeepageByDuration(int liningRingConstructionId, Date start, Date end);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public int updateSeepage(Seepage seepage);

}
