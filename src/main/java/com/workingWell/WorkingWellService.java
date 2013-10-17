package com.workingWell;

import java.util.List;

public interface WorkingWellService {

	public int deleteWorkingWell(int id);

	public WorkingWell findByName(String name);

	public WorkingWell findByPK(int id);

	public int insertWorkingWell(WorkingWell workingWell);

	public int queryAllSize();

	public List<WorkingWell> queryLimitedWorkingWells(int start, int size);

	public List<WorkingWell> queryLimitedWorkingWellsByTunnelId(int tunnelId, int start, int size);

	public int querySizeByTunnelId(int tunnelId);

	public int updateWorkingWell(WorkingWell workingWell);

	public List<WorkingWellPosition> queryWorkingWellPositions(int workingWellId);
	
	public int insertWorkingWellPosition(WorkingWellPosition position);
	
	public int deleteByWorkingWellId(int workingWellId);
}
