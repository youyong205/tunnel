package com.linePipe;

import java.util.List;

public interface LinePipeService {

	public int deleteLinePipe(int id);

	public LinePipe findByName(String name);

	public LinePipe findByPK(int id);

	public int insertLinePipe(LinePipe linePipe);

	public List<LinePipe> queryLimitedLinePipes(int tunnelId, int tunnelSectionId, int start, int size);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId);

	public int updateLinePipe(LinePipe linePipe);

}
