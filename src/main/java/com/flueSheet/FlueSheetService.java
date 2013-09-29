package com.flueSheet;

import java.util.List;

public interface FlueSheetService {

	public int deleteFlueSheet(int id);

	public FlueSheet findByName(String name);

	public FlueSheet findByPK(int id);

	public int insertFlueSheet(FlueSheet flueSheet);

	public List<FlueSheet> queryLimitedFlueSheets(int tunnelId, int tunnelSectionId, int start, int size);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId);

	public int updateFlueSheet(FlueSheet flueSheet);

}
