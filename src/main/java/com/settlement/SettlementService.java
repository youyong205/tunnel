package com.settlement;

import java.util.Date;
import java.util.List;

public interface SettlementService {

	public int deleteSettlement(int id);

	public Settlement findByName(String name);

	public Settlement findByPK(int id);

	public List<Settlement> queryByIds(List<Integer> ids);

	public int insertSettlement(Settlement settlement);

	public Settlement queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public List<Settlement> queryLimitedSettlements(int tunnelId, int tunnelSectionId, int liningRingConstructionId,
	      int start, int size);

	public List<Settlement> querySettlementByDuration(int liningRingConstructionId, Date start, Date end);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public int updateSettlement(Settlement settlement);

}
