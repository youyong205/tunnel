package com.curing;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class CuringServiceImpl implements CuringService {

	@Autowired
	private CuringDao m_curingDao;

	private Logger m_logger = Logger.getLogger(CuringServiceImpl.class);

	private static final int SIZE = 100;

	private Map<Integer, Curing> m_curings = new LinkedHashMap<Integer, Curing>(SIZE) {

		private static final long serialVersionUID = 1L;

		@Override
		protected boolean removeEldestEntry(Entry<Integer, Curing> eldest) {
			return size() > SIZE;
		}
	};

	@Override
	public int deleteCuring(int id) {
		try {
			int result = m_curingDao.deleteCuring(id);
		
			m_curings.remove(id);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public Curing findByPK(int id) {
		Curing curing = m_curings.get(id);
		if (curing == null) {
			try {
				curing = m_curingDao.findByPK(id);

				if (curing != null) {
					m_curings.put(id, curing);
				}
			} catch (Exception e) {
				m_logger.error(e.getMessage(), e);
			}
		}
		return curing;
	}

	@Override
	public int insertCuring(Curing curing) {
		try {
			int result = m_curingDao.insertCuring(curing);
			
			m_curings.put(curing.getId(), curing);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public int queryCuringSizeByType(int tunnelId,String type) {
		try {
			return m_curingDao.queryCuringSizeByType(tunnelId,type);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Curing> queryLimitedCuringsByType(int tunnelId,String type,int start, int size) {
		try {
			return m_curingDao.queryLimitedCuringsByType(tunnelId,type,start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Curing>();
		}
	}

	public void setCuringDao(CuringDao curingDao) {
		m_curingDao = curingDao;
	}

	@Override
	public int updateCuring(Curing curing) {
		try {
			int result = m_curingDao.updateCuring(curing);
			
			m_curings.put(curing.getId(), curing);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
