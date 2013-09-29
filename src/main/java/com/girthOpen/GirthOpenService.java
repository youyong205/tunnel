package com.girthOpen;

import java.util.Date;
import java.util.List;

public interface GirthOpenService {

	public int deleteGirthOpen(int id);

	public GirthOpen findByName(String name);

	public GirthOpen findByPK(int id);

	public int insertGirthOpen(GirthOpen girthOpen);

	public List<GirthOpen> queryGirthOpenByDuration(int liningRingConstructionId, Date start, Date end);

	public GirthOpen queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public List<GirthOpen> queryLimitedGirthOpens(int tunnelId, int tunnelSectionId, int liningRingConstructionId,
	      int start, int size);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public int updateGirthOpen(GirthOpen girthOpen);

}
