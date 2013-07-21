package com.liningRing;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class LiningRingBlockServiceImpl implements LiningRingBlockService {

	private LiningRingBlockDao m_liningRingBlockDao;

	private Logger m_logger = Logger.getLogger(LiningRingBlockServiceImpl.class);

	@SuppressWarnings("unchecked")
	public List<LiningRingBlock> queryByLiningRingId(int liningRingId) {
		try {
			return m_liningRingBlockDao.queryByLiningRingId(liningRingId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return new ArrayList<LiningRingBlock>();
	}

	public int insertLiningRingBlock(LiningRingBlock liningRing) {
		try {
			return m_liningRingBlockDao.insertLiningRingBlock(liningRing);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public int deleteLiningRingBlock(int liningRingId) {
		try {
			return m_liningRingBlockDao.deleteLiningRingBlock(liningRingId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setLiningRingBlockDao(LiningRingBlockDao liningRingBlockDao) {
   	m_liningRingBlockDao = liningRingBlockDao;
   }
	
}
