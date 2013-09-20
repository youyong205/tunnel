package com.liningRingConstruction;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.girthFault.GirthFault;
import com.girthOpen.GirthOpen;
import com.liningRingDeformation.LiningRingDeformation;
import com.liningRingLongitudinalDeformation.LiningRingLongitudinalDeformation;
import com.settlement.Settlement;

public class LiningRingConstructionServiceImpl implements LiningRingConstructionService {

	private LiningRingConstructionDao m_liningRingConstructionDao;

	private Logger m_logger = Logger.getLogger(LiningRingConstructionServiceImpl.class);

	private Map<String, LiningRingConstruction> m_constructions = new LinkedHashMap<String, LiningRingConstruction>() {

		private static final long serialVersionUID = 2196477629965076834L;

		@Override
		protected boolean removeEldestEntry(Entry<String, LiningRingConstruction> eldest) {
			return size() > 200;
		}
	};

	@Override
	public int deleteLiningRingConstruction(int id) {
		try {
			int result = m_liningRingConstructionDao.deleteLiningRingConstruction(id);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public LiningRingConstruction findByName(String name) {
		LiningRingConstruction construction = m_constructions.get(name);

		if (construction == null) {
			try {
				construction = m_liningRingConstructionDao.findByName(name);

				if (construction != null) {
					m_constructions.put(name, construction);
				}
			} catch (Exception e) {
				m_logger.error(e.getMessage(), e);
				return null;
			}
		}
		return construction;
	}

	@Override
	public LiningRingConstruction findByPK(int id) {
		try {
			return m_liningRingConstructionDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertLiningRingConstruction(LiningRingConstruction liningRingConstruction) {
		try {
			int result = m_liningRingConstructionDao.insertLiningRingConstruction(liningRingConstruction);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LiningRingConstruction> queryLimitedLiningRingConstructions(int tunnelId, int tunnelSectionId,
	      int start, int size) {
		try {
			return m_liningRingConstructionDao.queryLimitedLiningRingConstructions(tunnelId, tunnelSectionId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<LiningRingConstruction>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId) {
		try {
			return m_liningRingConstructionDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setLiningRingConstructionDao(LiningRingConstructionDao liningRingConstructionDao) {
		m_liningRingConstructionDao = liningRingConstructionDao;
	}

	@Override
	public int updateLiningRingConstruction(LiningRingConstruction liningRingConstruction) {
		try {
			int result = m_liningRingConstructionDao.updateLiningRingConstruction(liningRingConstruction);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
   public int updateDeformationState(LiningRingDeformation defomation) {
		System.out.println("Update updateDeformationState State");
	   return 0;
   }

	@Override
   public int updateLongitudinalDeformationState(LiningRingLongitudinalDeformation longitudinalDeformationState) {
		System.out.println("Update updateLongitudinalDeformationState State");
	   return 0;
   }

	@Override
   public int updateGirthOpenState(GirthOpen girthOpen) {
	   return 0;
   }

	@Override
   public int updateGirthFaultState(GirthFault girthFault) {
	   return 0;
   }

	@Override
   public int updateSettlementState(Settlement settlement) {
	   // TODO Auto-generated method stub
	   return 0;
   }

}
