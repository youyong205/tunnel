package com.inspection;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class InspectionServiceImpl implements InspectionService {

	@Autowired
	private InspectionDao m_inspectionDao;

	private Logger m_logger = Logger.getLogger(InspectionServiceImpl.class);

	@Override
	public int deleteInspection(int id) {
		try {
			int result = m_inspectionDao.deleteInspection(id);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public Inspection findByPK(int id) {
		try {
			Inspection inspection = m_inspectionDao.findByPK(id);

			return inspection;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertInspection(Inspection inspection) {
		try {
			int result = m_inspectionDao.insertInspection(inspection);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public int queryInspectionSizeByType(int tunnelId, int tunnelSectionId,int componentId, String type) {
		try {
			return m_inspectionDao.queryInspectionSizeByType(tunnelId, tunnelSectionId, componentId,type);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Inspection> queryLimitedInspectionsByType(int tunnelId, int tunnelSectionId,int componentId,  String type, int start,
	      int size) {
		try {
			return m_inspectionDao.queryLimitedInspectionsByType(tunnelId, tunnelSectionId,componentId, type, start, size);
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
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
