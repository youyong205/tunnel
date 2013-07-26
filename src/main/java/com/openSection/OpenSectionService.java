package com.openSection;

import java.util.List;

public interface OpenSectionService {

	public int deleteOpenSection(int id);
	
	public OpenSection findByName(String name);

	public OpenSection findByPK(int id);
	
	public int insertOpenSection(OpenSection openSection);

	public int queryAllSize();

	public List<OpenSection> queryLimitedOpenSectionsByTunnelId(int tunnelId,int start, int size);

	public List<OpenSection> queryLimitedOpenSections(int start, int size);

	public int querySizeByTunnelId(int tunnelId);

	public int updateOpenSection(OpenSection openSection);

}
