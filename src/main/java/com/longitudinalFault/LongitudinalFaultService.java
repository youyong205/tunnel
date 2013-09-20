package com.longitudinalFault;

import java.util.Date;
import java.util.List;

public interface LongitudinalFaultService {

	public int deleteLongitudinalFault(int id);

	public LongitudinalFault findByName(String name);

	public LongitudinalFault findByPK(int id);

	public int insertLongitudinalFault(LongitudinalFault longitudinalFault);

	public List<LongitudinalFault> queryLimitedLongitudinalFaults(int tunnelId, int tunnelSectionId,
	      int liningRingConstructionId, int start, int size);

	public List<LongitudinalFault> queryLongitudinalFaultByDuration(int liningRingConstructionId, Date start,
	      Date end);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public LongitudinalFault queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public int updateLongitudinalFault(LongitudinalFault longitudinalFault);

}
