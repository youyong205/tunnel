package com.inspection;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class InspectionServiceImpl implements InspectionService {

	@Autowired
	private InspectionDao m_inspectionDao;

	private Logger m_logger = Logger.getLogger(InspectionServiceImpl.class);

	private static final int SIZE = 100;

	private Map<Integer, Inspection> m_inspections = new LinkedHashMap<Integer, Inspection>(SIZE) {

		private static final long serialVersionUID = 1L;

		@Override
		protected boolean removeEldestEntry(Entry<Integer, Inspection> eldest) {
			return size() > SIZE;
		}
	};

	@Override
	public int deleteInspection(int id) {
		try {
			int result = m_inspectionDao.deleteInspection(id);

			m_inspections.remove(id);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public Inspection findByPK(int id) {
		Inspection inspection = m_inspections.get(id);
		if (inspection == null) {
			try {
				inspection = m_inspectionDao.findByPK(id);

				if (inspection != null) {
					m_inspections.put(id, inspection);
				}
			} catch (Exception e) {
				m_logger.error(e.getMessage(), e);
			}
		}
		return inspection;
	}

	@Override
	public int insertInspection(Inspection inspection) {
		try {
			int result = m_inspectionDao.insertInspection(inspection);

			m_inspections.put(inspection.getId(), inspection);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public int queryInspectionSizeByType(int tunnelId, int tunnelSectionId, String type) {
		try {
			return m_inspectionDao.queryInspectionSizeByType(tunnelId, tunnelSectionId, type);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Inspection> queryLimitedInspectionsByType(int tunnelId, int tunnelSectionId, String type, int start,
	      int size) {
		try {
			return m_inspectionDao.queryLimitedInspectionsByType(tunnelId, tunnelSectionId, type, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Inspection>();
		}
	}

	public void setInspectionDao(InspectionDao inspectionDao) {
		m_inspectionDao = inspectionDao;
	}

	@Override
	public int updateInspection(Inspection inspection) {
		try {
			int result = m_inspectionDao.updateInspection(inspection);

			m_inspections.put(inspection.getId(), inspection);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
