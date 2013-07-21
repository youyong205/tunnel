package com.liningRing;

import java.util.List;

public interface LiningRingBlockService {

	public List<LiningRingBlock> queryByLiningRingId(int liningRingId);
	
	public int insertLiningRingBlock(LiningRingBlock liningRing) ;

	public int deleteLiningRingBlock(int liningRingId) ;

}
