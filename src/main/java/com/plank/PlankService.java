package com.plank;

import java.util.List;

public interface PlankService {

	public int deletePlank(int id);

	public Plank findByName(String name);

	public Plank findByPK(int id);

	public int insertPlank(Plank plank);

	public List<Plank> queryLimitedPlanks(int tunnelId, int tunnelSectionId, int start, int size);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId);

	public int updatePlank(Plank plank);

}
