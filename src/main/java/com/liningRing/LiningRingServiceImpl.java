package com.liningRing;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

public class LiningRingServiceImpl implements LiningRingService {

	private LiningRingDao m_liningRingDao;

	private Logger m_logger = Logger.getLogger(LiningRingServiceImpl.class);

	private static final int SIZE = 100;

	private Map<Integer, LiningRing> m_liningRings = new LinkedHashMap<Integer, LiningRing>(SIZE) {

		private static final long serialVersionUID = 1L;

		@Override
		protected boolean removeEldestEntry(Entry<Integer, LiningRing> eldest) {
			return size() > SIZE;
		}
	};

	@Override
	public int deleteLiningRing(int id) {
		try {
			int result = m_liningRingDao.deleteLiningRing(id);
		
			m_liningRings.remove(id);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public LiningRing findByPK(int id) {
		LiningRing liningRing = m_liningRings.get(id);
		if (liningRing == null) {
			try {
				liningRing = m_liningRingDao.findByPK(id);

				if (liningRing != null) {
					m_liningRings.put(id, liningRing);
				}
			} catch (Exception e) {
				m_logger.error(e.getMessage(), e);
			}
		}
		return liningRing;
	}

	@Override
	public int insertLiningRing(LiningRing liningRing) {
		try {
			int result = m_liningRingDao.insertLiningRing(liningRing);
			
			m_liningRings.put(liningRing.getId(), liningRing);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public int queryAllSize() {
		try {
			return m_liningRingDao.queryAllSize();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	public List<LiningRing> queryAllLiningRings() {
		try {
			return m_liningRingDao.queryAllLiningRings();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<LiningRing>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LiningRing> queryLimitedLiningRings(int start, int size) {
		try {
			return m_liningRingDao.queryLimitedLiningRings(start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<LiningRing>();
		}
	}

	public void setLiningRingDao(LiningRingDao liningRingDao) {
		m_liningRingDao = liningRingDao;
	}

	@Override
	public int updateLiningRing(LiningRing liningRing) {
		try {
			int result = m_liningRingDao.updateLiningRing(liningRing);
			
			m_liningRings.put(liningRing.getId(), liningRing);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
   public LiningRing findByName(String name) {
		try {
			return m_liningRingDao.findByName(name);
			
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
   }

}
