package com.bracket;

import java.util.List;

public interface BracketService {

	public int deleteBracket(int id);

	public Bracket findByName(String name);

	public Bracket findByPK(int id);

	public int insertBracket(Bracket bracket);

	public List<Bracket> queryLimitedBrackets(int tunnelId, int tunnelSectionId, int start, int size);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId);

	public int updateBracket(Bracket bracket);

}
