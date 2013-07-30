package com.liningRing;

import java.util.List;

import com.BaseDao;

public class LiningRingBlockDao {

	private BaseDao m_baseDao;

	public int deleteLiningRingBlock(int liningRingId) {
		return m_baseDao.delete("liningRingBlock.deleteByLiningRingId", liningRingId);
	}

	public int insertLiningRingBlock(LiningRingBlock liningRing) {
		return (Integer) m_baseDao.insert("liningRingBlock.insert", liningRing);
	}
	
	@SuppressWarnings("rawtypes")
	public List queryByLiningRingId(int liningRingId){
		return m_baseDao.queryForList("liningRingBlock.queryByLiningRingId",liningRingId);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

}
