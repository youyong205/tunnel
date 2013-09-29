package com.girthOpen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.liningRingConstruction.LiningRingConstructionService;

public class GirthOpenServiceImpl implements GirthOpenService {

	private GirthOpenDao m_girthOpenDao;

	private LiningRingConstructionService m_liningRingConstructionService;

	private Logger m_logger = Logger.getLogger(GirthOpenServiceImpl.class);

	@Override
	public int deleteGirthOpen(int id) {
		try {
			int result = m_girthOpenDao.deleteGirthOpen(id);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public GirthOpen findByName(String name) {
		try {
			return m_girthOpenDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public GirthOpen findByPK(int id) {
		try {
			return m_girthOpenDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertGirthOpen(GirthOpen girthOpen) {
		try {
			int result = m_girthOpenDao.insertGirthOpen(girthOpen);

			m_liningRingConstructionService.updateGirthOpenState(girthOpen);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GirthOpen> queryGirthOpenByDuration(int girthOpenId, Date start, Date end) {
		try {
			return m_girthOpenDao.queryGirthOpenByDuration(girthOpenId, start, end);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<GirthOpen>();
		}
	}

	@Override
	public GirthOpen queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		try {
			return m_girthOpenDao.queryLastestDeformation(tunnelId, tunnelSectionId, liningRingConstructionId);

		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GirthOpen> queryLimitedGirthOpens(int tunnelId, int tunnelSectionId, int liningRingConstructionId,
	      int start, int size) {
		try {
			return m_girthOpenDao.queryLimitedGirthOpens(tunnelId, tunnelSectionId, liningRingConstructionId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<GirthOpen>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		try {
			return m_girthOpenDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId, liningRingConstructionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setGirthOpenDao(GirthOpenDao girthOpenDao) {
		m_girthOpenDao = girthOpenDao;
	}

	public void setLiningRingConstructionService(LiningRingConstructionService liningRingConstructionService) {
		m_liningRingConstructionService = liningRingConstructionService;
	}

	@Override
	public int updateGirthOpen(GirthOpen girthOpen) {
		try {
			int result = m_girthOpenDao.updateGirthOpen(girthOpen);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
