package com.saddleWeight;

import java.util.List;

public interface SaddleWeightService {

	public int deleteSaddleWeight(int id);

	public SaddleWeight findByName(String name);

	public SaddleWeight findByPK(int id);

	public int insertSaddleWeight(SaddleWeight saddleWeight);

	public List<SaddleWeight> queryLimitedSaddleWeights(int tunnelId, int tunnelSectionId, int start,
	      int size);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId);

	public int updateSaddleWeight(SaddleWeight saddleWeight);

}
