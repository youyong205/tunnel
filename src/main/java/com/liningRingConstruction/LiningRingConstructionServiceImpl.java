package com.liningRingConstruction;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.coverLoss.CoverLoss;
import com.cracks.Cracks;
import com.girthFault.GirthFault;
import com.girthOpen.GirthOpen;
import com.liningRingDeformation.LiningRingDeformation;
import com.liningRingLongitudinalDeformation.LiningRingLongitudinalDeformation;
import com.longitudinalFault.LongitudinalFault;
import com.longitudinalOpen.LongitudinalOpen;
import com.rust.Rust;
import com.seepage.Seepage;
import com.settlement.Settlement;
import com.tunnelSection.TunnelSection;
import com.tunnelSection.TunnelSectionService;

public class LiningRingConstructionServiceImpl implements LiningRingConstructionService {

	private LiningRingConstructionDao m_liningRingConstructionDao;

	private Logger m_logger = Logger.getLogger(LiningRingConstructionServiceImpl.class);

	private TunnelSectionService m_tunnelSectionService;

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
	public int updateCoverLossState(CoverLoss coverLoss) {
		return 0;
	}

	@Override
	public int updateCracksState(Cracks cracks) {
		return 0;
	}

	@Override
	public int updateDeformationState(LiningRingDeformation deformation) {
		int tunnelSectionId = deformation.getTunnelSectionId();
		TunnelSection tunnelSection = m_tunnelSectionService.findByPK(tunnelSectionId);
		int deformationId = deformation.getId();
		int liningRingConstructionId = deformation.getLiningRingConstructionId();

		double value = deformation.getValue();
		double externalDiameter = tunnelSection.getExternalDiameter();
		String deformationState = "A";

		if (value > externalDiameter * 30 / 1000) {
			deformationState = "E";
		} else if (value > externalDiameter * 20 / 1000) {
			deformationState = "D";
		} else if (value > externalDiameter * 10 / 1000) {
			deformationState = "C";
		} else if (value > externalDiameter * 5 / 1000) {
			deformationState = "B";
		} else {
			deformationState = "A";
		}
		return m_liningRingConstructionDao.updateDeformationState(deformationState, deformationId,
		      liningRingConstructionId);
	}

	@Override
	public int updateGirthFaultState(GirthFault girthFault) {
		return 0;
	}

	@Override
	public int updateGirthOpenState(GirthOpen girthOpen) {
		return 0;
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
	public int updateLongitudinalDeformationState(LiningRingLongitudinalDeformation longitudinalDeformation) {
		int longitudinalLongitudinalDeformationId = longitudinalDeformation.getId();
		int liningRingConstructionId = longitudinalDeformation.getLiningRingConstructionId();

		double value = longitudinalDeformation.getValue();
		String longitudinallDeformationState = "A";

		if (value > 1.0 / 1500) {
			longitudinallDeformationState = "E";
		} else if (value > 1.0 / 2500) {
			longitudinallDeformationState = "D";
		} else if (value > 1.0 / 10000) {
			longitudinallDeformationState = "C";
		} else if (value > 1.0 / 15000) {
			longitudinallDeformationState = "B";
		} else {
			longitudinallDeformationState = "A";
		}
		return m_liningRingConstructionDao.updateLongitudinalDeformationState(longitudinallDeformationState,
		      longitudinalLongitudinalDeformationId, liningRingConstructionId);
	}

	@Override
	public int updateLongitudinalFaultState(LongitudinalFault longitudinalFault) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateLongitudinalOpenState(LongitudinalOpen longitudinalOpen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateRustState(Rust rust) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSeepageState(Seepage seepage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSettlementState(Settlement settlement) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setTunnelSectionService(TunnelSectionService tunnelSectionService) {
		m_tunnelSectionService = tunnelSectionService;
	}

}
