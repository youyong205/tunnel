package com.buriedSection;

import java.util.List;

public interface BuriedSectionService {

	public int deleteBuriedSection(int id);

	public BuriedSection findByName(String name);

	public BuriedSection findByPK(int id);

	public int insertBuriedSection(BuriedSection buriedSection);

	public int queryAllSize();

	public List<BuriedSection> queryLimitedBuriedSections(int start, int size);

	public List<BuriedSection> queryLimitedBuriedSectionsByTunnelId(int tunnelId, int start, int size);

	public int querySizeByTunnelId(int tunnelId);

	public int updateBuriedSection(BuriedSection buriedSection);

}
