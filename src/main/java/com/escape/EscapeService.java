package com.escape;

import java.util.List;

public interface EscapeService {

	public int deleteEscape(int id);

	public Escape findByName(String name);

	public Escape findByPK(int id);

	public int insertEscape(Escape escape);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId);

	public List<Escape> queryLimitedEscapes(int tunnelId, int tunnelSectionId, int start,
	      int size);

	public int updateEscape(Escape escape);

}
