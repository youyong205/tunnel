package com.coverLoss;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.liningRingConstruction.LiningRingConstructionService;

public class CoverLossServiceImpl implements CoverLossService {

	private CoverLossDao m_coverLossDao;

	private LiningRingConstructionService m_liningRingConstructionService;

	private Logger m_logger = Logger.getLogger(CoverLossServiceImpl.class);

	@Override
	public int deleteCoverLoss(int id) {
		try {
			int result = m_coverLossDao.deleteCoverLoss(id);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public CoverLoss findByName(String name) {
		try {
			return m_coverLossDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public CoverLoss findByPK(int id) {
		try {
			return m_coverLossDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertCoverLoss(CoverLoss coverLoss) {
		try {
			int result = m_coverLossDao.insertCoverLoss(coverLoss);

			m_liningRingConstructionService.updateCoverLossState(coverLoss);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoverLoss> queryCoverLossByDuration(int coverLossId, Date start, Date end) {
		try {
			return m_coverLossDao.queryCoverLossByDuration(coverLossId, start, end);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<CoverLoss>();
		}
	}

	@Override
	public CoverLoss queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		try {
			return m_coverLossDao.queryLastestDeformation(tunnelId, tunnelSectionId, liningRingConstructionId);

		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoverLoss> queryLimitedCoverLosss(int tunnelId, int tunnelSectionId, int liningRingConstructionId,
	      int start, int size) {
		try {
			return m_coverLossDao.queryLimitedCoverLosss(tunnelId, tunnelSectionId, liningRingConstructionId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<CoverLoss>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		try {
			return m_coverLossDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId, liningRingConstructionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setCoverLossDao(CoverLossDao coverLossDao) {
		m_coverLossDao = coverLossDao;
	}

	public void setLiningRingConstructionService(LiningRingConstructionService liningRingConstructionService) {
		m_liningRingConstructionService = liningRingConstructionService;
	}

	@Override
	public int updateCoverLoss(CoverLoss coverLoss) {
		try {
			int result = m_coverLossDao.updateCoverLoss(coverLoss);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}
	

	@SuppressWarnings("unchecked")
   @Override
   public List<CoverLoss> queryByIds(List<Integer> ids) {
		try {
			return m_coverLossDao.queryByIds(ids);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<CoverLoss>();
		}
   }

}
