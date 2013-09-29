package com.coverLoss;

import java.util.Date;
import java.util.List;

public interface CoverLossService {

	public int deleteCoverLoss(int id);

	public CoverLoss findByName(String name);

	public CoverLoss findByPK(int id);

	public int insertCoverLoss(CoverLoss coverLoss);

	public List<CoverLoss> queryCoverLossByDuration(int liningRingConstructionId, Date start,
	      Date end);

	public CoverLoss queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public List<CoverLoss> queryLimitedCoverLosss(int tunnelId, int tunnelSectionId,
	      int liningRingConstructionId, int start, int size);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public int updateCoverLoss(CoverLoss coverLoss);

}
