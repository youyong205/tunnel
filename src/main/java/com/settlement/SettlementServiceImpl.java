package com.settlement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.liningRingConstruction.LiningRingConstructionService;

public class SettlementServiceImpl implements SettlementService {

	private SettlementDao m_settlementDao;

	private LiningRingConstructionService m_liningRingConstructionService;

	private Logger m_logger = Logger.getLogger(SettlementServiceImpl.class);

	@Override
	public int deleteSettlement(int id) {
		try {
			int result = m_settlementDao.deleteSettlement(id);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public Settlement findByName(String name) {
		try {
			return m_settlementDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public Settlement findByPK(int id) {
		try {
			return m_settlementDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertSettlement(Settlement settlement) {
		try {
			int result = m_settlementDao.insertSettlement(settlement);

			m_liningRingConstructionService.updateSettlementState(settlement);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public Settlement queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		try {
			return m_settlementDao.queryLastestDeformation(tunnelId, tunnelSectionId, liningRingConstructionId);

		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Settlement> queryLimitedSettlements(int tunnelId, int tunnelSectionId, int liningRingConstructionId,
	      int start, int size) {
		try {
			return m_settlementDao.queryLimitedSettlements(tunnelId, tunnelSectionId, liningRingConstructionId, start,
			      size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Settlement>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Settlement> querySettlementByDuration(int settlementId, Date start, Date end) {
		try {
			return m_settlementDao.querySettlementByDuration(settlementId, start, end);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Settlement>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		try {
			return m_settlementDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId, liningRingConstructionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setLiningRingConstructionService(LiningRingConstructionService liningRingConstructionService) {
		m_liningRingConstructionService = liningRingConstructionService;
	}

	public void setSettlementDao(SettlementDao settlementDao) {
		m_settlementDao = settlementDao;
	}

	@Override
	public int updateSettlement(Settlement settlement) {
		try {
			int result = m_settlementDao.updateSettlement(settlement);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
   @Override
   public List<Settlement> queryByIds(List<Integer> ids) {
		try {
			return m_settlementDao.queryByIds(ids);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Settlement>();
		}
   }
}
