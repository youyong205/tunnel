package com.girthFault;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.liningRingConstruction.LiningRingConstructionService;

public class GirthFaultServiceImpl implements GirthFaultService {

	private GirthFaultDao m_girthFaultDao;

	private LiningRingConstructionService m_liningRingConstructionService;
	
	private Logger m_logger = Logger.getLogger(GirthFaultServiceImpl.class);

	@Override
	public int deleteGirthFault(int id) {
		try {
			int result = m_girthFaultDao.deleteGirthFault(id);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public GirthFault findByName(String name) {
		try {
			return m_girthFaultDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public GirthFault findByPK(int id) {
		try {
			return m_girthFaultDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertGirthFault(GirthFault girthFault) {
		try {
			int result = m_girthFaultDao.insertGirthFault(girthFault);

			m_liningRingConstructionService.updateGirthFaultState(girthFault);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GirthFault> queryLimitedGirthFaults(int tunnelId, int tunnelSectionId,
	      int liningRingConstructionId, int start, int size) {
		try {
			return m_girthFaultDao.queryLimitedGirthFaults(tunnelId, tunnelSectionId,
			      liningRingConstructionId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<GirthFault>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GirthFault> queryGirthFaultByDuration(int girthFaultId, Date start,
	      Date end) {
		try {
			return m_girthFaultDao.queryGirthFaultByDuration(girthFaultId, start, end);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<GirthFault>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		try {
			return m_girthFaultDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId, liningRingConstructionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setGirthFaultDao(GirthFaultDao girthFaultDao) {
		m_girthFaultDao = girthFaultDao;
	}

	@Override
	public int updateGirthFault(GirthFault girthFault) {
		try {
			int result = m_girthFaultDao.updateGirthFault(girthFault);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public GirthFault queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		try {
			return m_girthFaultDao.queryLastestDeformation(tunnelId, tunnelSectionId, liningRingConstructionId);

		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	public void setLiningRingConstructionService(LiningRingConstructionService liningRingConstructionService) {
   	m_liningRingConstructionService = liningRingConstructionService;
   }
	
}
