package com.inspection;

import java.util.List;

public interface InspectionService {

	public int deleteInspection(int id);

	public Inspection findByPK(int id);

	public int insertInspection(Inspection inspection);

	public int queryInspectionSizeByType(int tunnelId, int tunnelSectionId,int componentId, String type);

	public List<Inspection> queryLimitedInspectionsByType(int tunnelId, int tunnelSectionId,int componentId, String type, int start,
	      int size);

	public int updateInspection(Inspection inspection);

}
