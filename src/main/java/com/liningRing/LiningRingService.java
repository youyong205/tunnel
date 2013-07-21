package com.liningRing;

import java.util.List;

public interface LiningRingService {

	public int queryAllSize();
	
	public List<LiningRing> queryAllLiningRings();

	public List<LiningRing> queryLimitedLiningRings(int start, int size);

	public LiningRing findByName(String name);

	public LiningRing findByPK(int id);

	public int insertLiningRing(LiningRing liningRing);

	public int updateLiningRing(LiningRing liningRing);

	public int deleteLiningRing(int id);

}
