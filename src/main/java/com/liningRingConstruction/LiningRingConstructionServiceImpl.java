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
import com.liningRing.LiningRingBlock;
import com.liningRing.LiningRingBlockService;
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

	private LiningRingBlockService m_liningRingBlockService;

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
	public int updateDeformationState(LiningRingDeformation deformation) {
		int tunnelSectionId = deformation.getTunnelSectionId();
		TunnelSection tunnelSection = m_tunnelSectionService.findByPK(tunnelSectionId);
		int deformationId = deformation.getId();
		int liningRingConstructionId = deformation.getLiningRingConstructionId();
		double value = deformation.getValue();
		double externalDiameter = tunnelSection.getExternalDiameter();
		String deformationState = "A";
		double[] standard = { 30.0 / 1000, 20.0 / 1000, 10.0 / 1000, 5.0 / 1000 };

		if (value > externalDiameter * standard[0]) {
			deformationState = "E";
		} else if (value > externalDiameter * standard[1]) {
			deformationState = "D";
		} else if (value > externalDiameter * standard[2]) {
			deformationState = "C";
		} else if (value > externalDiameter * standard[3]) {
			deformationState = "B";
		} else {
			deformationState = "A";
		}
		return m_liningRingConstructionDao.updateDeformationState(deformationState, deformationId,
		      liningRingConstructionId);
	}

	private String computeBlockState(int realSize, String oldState, String newState, int blockIndex) {
		if (oldState == null) {
			oldState = "";
		}

		String[] state = oldState.split(",");

		if (realSize != state.length) {
			state = new String[realSize];
			for (int i = 0; i < realSize; i++) {
				state[i] = "-";
			}
		}
		StringBuilder sb = new StringBuilder();
		boolean first = true;

		for (int i = 0; i < realSize; i++) {
			if (first) {
				if (i + 1 == blockIndex) {
					sb.append(newState);
				} else {
					sb.append(state[i]);
				}
				first = false;
			} else {
				sb.append(',');
				if (i + 1 == blockIndex) {
					sb.append(newState);
				} else {
					sb.append(state[i]);
				}
			}
		}

		return sb.toString();
	}

	@Override
	public int updateGirthOpenState(GirthOpen girthOpen) {
		int liningRingConstructionId = girthOpen.getLiningRingConstructionId();
		int blockIndex = girthOpen.getBlockIndex();
		LiningRingConstruction liningRingConstruction = m_liningRingConstructionDao.findByPK(liningRingConstructionId);
		String oldState = liningRingConstruction.getGirthOpenState();
		double value = girthOpen.getValue();
		String newBlockState = "A";
		double[] standard = { 9, 7, 4 };

		if (girthOpen.getSerious() == 2) {
			newBlockState = "E";
		} else if (value > standard[0]) {
			newBlockState = "D";
		} else if (value > standard[1]) {
			newBlockState = "C";
		} else if (value > standard[2]) {
			newBlockState = "B";
		} else {
			newBlockState = "A";
		}
		int lingRingId = liningRingConstruction.getLiningRingId();
		List<LiningRingBlock> blocks = m_liningRingBlockService.queryByLiningRingId(lingRingId);
		String girthOpenState = computeBlockState(blocks.size(), oldState, newBlockState, blockIndex);
		String lastIds = computeBlockState(blocks.size(), liningRingConstruction.getGirthOpenId(),
		      String.valueOf(girthOpen.getId()), blockIndex);
		return m_liningRingConstructionDao.updateGirthOpenState(girthOpenState, lastIds, liningRingConstructionId);
	}

	@Override
	public int updateGirthFaultState(GirthFault girthFault) {
		int liningRingConstructionId = girthFault.getLiningRingConstructionId();
		int blockIndex = girthFault.getBlockIndex();
		LiningRingConstruction liningRingConstruction = m_liningRingConstructionDao.findByPK(liningRingConstructionId);
		String oldState = liningRingConstruction.getGirthFaultState();
		double value = girthFault.getValue();
		String newBlockState = "A";
		double[] standard = { 9, 7, 4 };

		if (girthFault.getSerious() == 2) {
			newBlockState = "E";
		} else if (value > standard[0]) {
			newBlockState = "D";
		} else if (value > standard[1]) {
			newBlockState = "C";
		} else if (value > standard[2]) {
			newBlockState = "B";
		} else {
			newBlockState = "A";
		}
		int lingRingId = liningRingConstruction.getLiningRingId();
		List<LiningRingBlock> blocks = m_liningRingBlockService.queryByLiningRingId(lingRingId);
		String girthFaultState = computeBlockState(blocks.size(), oldState, newBlockState, blockIndex);
		String lastIds = computeBlockState(blocks.size(), liningRingConstruction.getGirthFaultId(),
		      String.valueOf(girthFault.getId()), blockIndex);
		return m_liningRingConstructionDao.updateGirthFaultState(girthFaultState, lastIds, liningRingConstructionId);
	}

	@Override
	public int updateLongitudinalOpenState(LongitudinalOpen longitudinalOpen) {
		int liningRingConstructionId = longitudinalOpen.getLiningRingConstructionId();
		int blockIndex = longitudinalOpen.getBlockIndex();
		LiningRingConstruction liningRingConstruction = m_liningRingConstructionDao.findByPK(liningRingConstructionId);
		String oldState = liningRingConstruction.getLongitudinalOpenState();
		double value = longitudinalOpen.getValue();
		String newBlockState = "A";
		double[] standard = { 9, 7, 4 };

		if (longitudinalOpen.getSerious() == 2) {
			newBlockState = "E";
		} else if (value > standard[0]) {
			newBlockState = "D";
		} else if (value > standard[1]) {
			newBlockState = "C";
		} else if (value > standard[2]) {
			newBlockState = "B";
		} else {
			newBlockState = "A";
		}
		int lingRingId = liningRingConstruction.getLiningRingId();
		List<LiningRingBlock> blocks = m_liningRingBlockService.queryByLiningRingId(lingRingId);
		String longitudinalOpenState = computeBlockState(blocks.size(), oldState, newBlockState, blockIndex);
		String lastIds = computeBlockState(blocks.size(), liningRingConstruction.getLongitudinalOpenId(),
		      String.valueOf(longitudinalOpen.getId()), blockIndex);
		return m_liningRingConstructionDao.updateLongitudinalOpenState(longitudinalOpenState, lastIds,
		      liningRingConstructionId);
	}

	@Override
	public int updateLongitudinalFaultState(LongitudinalFault longitudinalFault) {
		int liningRingConstructionId = longitudinalFault.getLiningRingConstructionId();
		int blockIndex = longitudinalFault.getBlockIndex();
		LiningRingConstruction liningRingConstruction = m_liningRingConstructionDao.findByPK(liningRingConstructionId);
		String oldState = liningRingConstruction.getLongitudinalFaultState();
		double value = longitudinalFault.getValue();
		String newBlockState = "A";
		double[] standard = { 9, 7, 4 };

		if (longitudinalFault.getSerious() == 2) {
			newBlockState = "E";
		} else if (value > standard[0]) {
			newBlockState = "D";
		} else if (value > standard[1]) {
			newBlockState = "C";
		} else if (value > standard[2]) {
			newBlockState = "B";
		} else {
			newBlockState = "A";
		}
		int lingRingId = liningRingConstruction.getLiningRingId();
		List<LiningRingBlock> blocks = m_liningRingBlockService.queryByLiningRingId(lingRingId);
		String longitudinalFaultState = computeBlockState(blocks.size(), oldState, newBlockState, blockIndex);
		String lastIds = computeBlockState(blocks.size(), liningRingConstruction.getLongitudinalFaultId(),
		      String.valueOf(longitudinalFault.getId()), blockIndex);
		return m_liningRingConstructionDao.updateLongitudinalFaultState(longitudinalFaultState, lastIds,
		      liningRingConstructionId);
	}

	@Override
	public int updateCoverLossState(CoverLoss coverLoss) {
		int liningRingConstructionId = coverLoss.getLiningRingConstructionId();
		int blockIndex = coverLoss.getBlockIndex();
		LiningRingConstruction liningRingConstruction = m_liningRingConstructionDao.findByPK(liningRingConstructionId);
		String oldState = liningRingConstruction.getCoverLossState();
		double value = 0;
		String newBlockState = "A";
		double[] standard = { 10, 5, 0 };

		if (coverLoss.getSerious() == 2) {
			newBlockState = "E";
		} else if (value > standard[0]) {
			newBlockState = "D";
		} else if (value > standard[1]) {
			newBlockState = "C";
		} else if (value > standard[2]) {
			newBlockState = "B";
		} else {
			newBlockState = "A";
		}
		int lingRingId = liningRingConstruction.getLiningRingId();
		List<LiningRingBlock> blocks = m_liningRingBlockService.queryByLiningRingId(lingRingId);
		String coverLossState = computeBlockState(blocks.size(), oldState, newBlockState, blockIndex);
		String lastIds = computeBlockState(blocks.size(), liningRingConstruction.getCoverLossId(),
		      String.valueOf(coverLoss.getId()), blockIndex);
		return m_liningRingConstructionDao.updateCoverLossState(coverLossState, lastIds, liningRingConstructionId);
	}

	@Override
	public int updateCracksState(Cracks cracks) {
		int liningRingConstructionId = cracks.getLiningRingConstructionId();
		int blockIndex = cracks.getBlockIndex();
		int tunnelSectionId = cracks.getTunnelSectionId();
		TunnelSection tunnelSection = m_tunnelSectionService.findByPK(tunnelSectionId);
		LiningRingConstruction liningRingConstruction = m_liningRingConstructionDao.findByPK(liningRingConstructionId);
		String oldState = liningRingConstruction.getCracksState();
		double number = cracks.getNumber();
		double width = cracks.getWidth();
		String newBlockState = "A";
		String enviroment = tunnelSection.getEnvironment();

		double numberStandard[] = { 3, 2, 0 };
		double widthStandard1[] = { 0.4, 0.3, 0 };
		double widthStandard2[] = { 0.3, 0.2, 0 };
		if (cracks.getSerious() == 2) {
			newBlockState = "E";
		} else {
			if ("A".equals(enviroment) || "B".equals(enviroment) || "C".equals(enviroment)) {
				if (number > numberStandard[0] || width > widthStandard1[0]) {
					newBlockState = "D";
				} else if (number > numberStandard[1] || width > widthStandard1[1]) {
					newBlockState = "C";
				} else if (number > numberStandard[2] || width > widthStandard1[2]) {
					newBlockState = "B";
				} else {
					newBlockState = "A";
				}
			} else if ("D".equals(enviroment) || "E".equals(enviroment)) {
				if (number > numberStandard[0] || width > widthStandard2[0]) {
					newBlockState = "D";
				} else if (number > numberStandard[1] || width > widthStandard2[1]) {
					newBlockState = "C";
				} else if (number > numberStandard[2] || width > widthStandard2[2]) {
					newBlockState = "B";
				} else {
					newBlockState = "A";
				}
			}
		}
		int lingRingId = liningRingConstruction.getLiningRingId();
		List<LiningRingBlock> blocks = m_liningRingBlockService.queryByLiningRingId(lingRingId);
		String cracksState = computeBlockState(blocks.size(), oldState, newBlockState, blockIndex);
		String lastIds = computeBlockState(blocks.size(), liningRingConstruction.getCracksId(),
		      String.valueOf(cracks.getId()), blockIndex);
		return m_liningRingConstructionDao.updateCracksState(cracksState, lastIds, liningRingConstructionId);
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
		double[] standard = { 1.0 / 1500, 1.0 / 2500, 1.0 / 10000, 1.0 / 15000 };

		if (value > standard[0]) {
			longitudinallDeformationState = "E";
		} else if (value > standard[1]) {
			longitudinallDeformationState = "D";
		} else if (value > standard[2]) {
			longitudinallDeformationState = "C";
		} else if (value > standard[3]) {
			longitudinallDeformationState = "B";
		} else {
			longitudinallDeformationState = "A";
		}
		return m_liningRingConstructionDao.updateLongitudinalDeformationState(longitudinallDeformationState,
		      longitudinalLongitudinalDeformationId, liningRingConstructionId);
	}

	@Override
	public int updateRustState(Rust rust) {
		return 0;
	}

	@Override
	public int updateSeepageState(Seepage seepage) {
		return 0;
	}

	@Override
	public int updateSettlementState(Settlement settlement) {
		return 0;
	}

	public void setTunnelSectionService(TunnelSectionService tunnelSectionService) {
		m_tunnelSectionService = tunnelSectionService;
	}

	public void setLiningRingBlockService(LiningRingBlockService liningRingBlockService) {
		m_liningRingBlockService = liningRingBlockService;
	}

}
