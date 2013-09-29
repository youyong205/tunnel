package com.liningRing;

import java.util.List;

public interface LiningRingBlockService {

	public int deleteLiningRingBlock(int liningRingId);

	public int insertLiningRingBlock(LiningRingBlock liningRing);

	public List<LiningRingBlock> queryByLiningRingId(int liningRingId);

}
