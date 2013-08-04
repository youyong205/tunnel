package com.facility;

import java.util.List;

public interface FacilityService {

	public int deleteFacility(int id);

	public Facility findByName(String name);

	public Facility findByPK(int id);

	public int insertFacility(Facility facility);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId);

	public List<Facility> queryLimitedFacilitys(int tunnelId, int tunnelSectionId, int start,
	      int size);

	public int updateFacility(Facility facility);

}
