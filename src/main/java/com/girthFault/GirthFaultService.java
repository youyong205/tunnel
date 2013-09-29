package com.girthFault;

import java.util.Date;
import java.util.List;

public interface GirthFaultService {

	public int deleteGirthFault(int id);

	public GirthFault findByName(String name);

	public GirthFault findByPK(int id);

	public int insertGirthFault(GirthFault girthFault);

	public List<GirthFault> queryGirthFaultByDuration(int liningRingConstructionId, Date start, Date end);

	public GirthFault queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public List<GirthFault> queryLimitedGirthFaults(int tunnelId, int tunnelSectionId, int liningRingConstructionId,
	      int start, int size);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public int updateGirthFault(GirthFault girthFault);

}
