package com.constructionUnit;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

public class ConstructionUnitServiceImpl implements ConstructionUnitService {

	private ConstructionUnitDao m_constructionUnitDao;

	private Logger m_logger = Logger.getLogger(ConstructionUnitServiceImpl.class);

	private static final int SIZE = 100;

	private Map<Integer, ConstructionUnit> m_constructionUnits = new LinkedHashMap<Integer, ConstructionUnit>(SIZE) {

		private static final long serialVersionUID = 1L;

		@Override
		protected boolean removeEldestEntry(Entry<Integer, ConstructionUnit> eldest) {
			return size() > SIZE;
		}
	};

	@Override
	public int deleteConstructionUnit(int id) {
		try {
			int result = m_constructionUnitDao.deleteConstructionUnit(id);

			m_constructionUnits.remove(id);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public ConstructionUnit findByName(String name) {
		try {
			return m_constructionUnitDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public ConstructionUnit findByPK(int id) {
		ConstructionUnit constructionUnit = m_constructionUnits.get(id);
		if (constructionUnit == null) {
			try {
				constructionUnit = m_constructionUnitDao.findByPK(id);

				if (constructionUnit != null) {
					m_constructionUnits.put(id, constructionUnit);
				}
			} catch (Exception e) {
				m_logger.error(e.getMessage(), e);
			}
		}
		return constructionUnit;
	}

	@Override
	public int insertConstructionUnit(ConstructionUnit constructionUnit) {
		try {
			int result = m_constructionUnitDao.insertConstructionUnit(constructionUnit);

			m_constructionUnits.put(constructionUnit.getId(), constructionUnit);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ConstructionUnit> queryAllConstructionUnits() {
		try {
			return m_constructionUnitDao.queryAllConstructionUnits();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<ConstructionUnit>();
		}
	}

	@Override
	public int queryAllSize() {
		try {
			return m_constructionUnitDao.queryAllSize();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConstructionUnit> queryLimitedConstructionUnits(int start, int size) {
		try {
			return m_constructionUnitDao.queryLimitedConstructionUnits(start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<ConstructionUnit>();
		}
	}

	public void setConstructionUnitDao(ConstructionUnitDao constructionUnitDao) {
		m_constructionUnitDao = constructionUnitDao;
	}

	@Override
	public int updateConstructionUnit(ConstructionUnit constructionUnit) {
		try {
			int result = m_constructionUnitDao.updateConstructionUnit(constructionUnit);

			m_constructionUnits.put(constructionUnit.getId(), constructionUnit);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
