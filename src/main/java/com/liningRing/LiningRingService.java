package com.liningRing;

import java.util.List;

public interface LiningRingService {

	public int deleteLiningRing(int id);
	
	public LiningRing findByName(String name);

	public LiningRing findByPK(int id);

	public int insertLiningRing(LiningRing liningRing);

	public List<LiningRing> queryAllLiningRings();

	public int queryAllSize();

	public List<LiningRing> queryLimitedLiningRings(int start, int size);

	public int updateLiningRing(LiningRing liningRing);

}
