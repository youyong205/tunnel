package com.tunnelSection;

import java.util.List;

public interface TunnelSectionService {

	public int deleteTunnelSection(int id);
	
	public TunnelSection findByName(String name);

	public TunnelSection findByPK(int id);
	
	public int insertTunnelSection(TunnelSection tunnelSection);

	public int queryAllSize();

	public List<TunnelSection> queryLimitedTunnelSectionsByTunnelId(int tunnelId,int start, int size);

	public List<TunnelSection> queryLimitedTunnelSections(int start, int size);

	public int querySizeByTunnelId(int tunnelId);

	public int updateTunnelSection(TunnelSection tunnelSection);

}
