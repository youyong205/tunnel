package com.pumpingStation;

import java.util.List;

public interface PumpingStationService {

	public int deletePumpingStation(int id);

	public PumpingStation findByName(String name);

	public PumpingStation findByPK(int id);

	public int insertPumpingStation(PumpingStation pumpingStation);

	public List<PumpingStation> queryLimitedPumpingStations(int tunnelId, int tunnelSectionId, int start, int size);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId);

	public int updatePumpingStation(PumpingStation pumpingStation);

}
