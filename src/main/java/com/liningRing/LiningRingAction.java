package com.liningRing;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.Constrants;
import com.PagedAction;
import com.log.Log;

public class LiningRingAction extends PagedAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(LiningRingAction.class);

	private List<LiningRing> m_liningRings;

	private int m_liningRingId;

	private LiningRingService m_liningRingService;

	private LiningRingBlockService m_liningRingBlockService;

	private LiningRing m_liningRing = new LiningRing();

	private List<LiningRingBlock> m_blocks = new ArrayList<LiningRingBlock>();

	public LiningRing getLiningRing() {
		return m_liningRing;
	}

	public List<LiningRing> getLiningRings() {
		return m_liningRings;
	}

	public void setLiningRing(LiningRing liningRing) {
		m_liningRing = liningRing;
	}

	public void setLiningRingId(int liningRingId) {
		m_liningRingId = liningRingId;
	}

	public void setLiningRingService(LiningRingService liningRingService) {
		m_liningRingService = liningRingService;
	}

	public String liningRingAdd(){
		return SUCCESS;
	}
	
	public String liningRingAddSubmit() {
		try {
			int id = m_liningRingService.insertLiningRing(m_liningRing);
			m_liningRingBlockService.deleteLiningRingBlock(id);

			for (LiningRingBlock block : m_blocks) {
				if (block != null && block.validate()) {
					block.setLiningRingId(id);
					m_liningRingBlockService.insertLiningRingBlock(block);
				}
			}
			if (id > 0) {
				Log log = createLog(Constrants.s_liningRing_model, Constrants.s_operation_add, m_liningRing);

				m_logService.insertLog(log);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String liningRingDelete() {
		try {
			int count = m_liningRingService.deleteLiningRing(m_liningRingId);
			m_liningRingBlockService.deleteLiningRingBlock(m_liningRingId);
			if (count > 0) {
				Log log = createLog(Constrants.s_liningRing_model, Constrants.s_operation_delete, m_liningRingId);

				m_logService.insertLog(log);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String liningRingList() {
		try {
			m_totalSize = m_liningRingService.queryAllSize();
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_liningRings = m_liningRingService.queryLimitedLiningRings(start, SIZE);
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String liningRingUpdate() {
		try {
			m_liningRing = m_liningRingService.findByPK(m_liningRingId);
			m_blocks = m_liningRingBlockService.queryByLiningRingId(m_liningRingId);
			if (m_liningRing != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String liningRingUpdateSubmit() {
		try {
			int count = m_liningRingService.updateLiningRing(m_liningRing);
			int id = m_liningRing.getId();
			m_liningRingBlockService.deleteLiningRingBlock(id);

			for (LiningRingBlock block : m_blocks) {
				if (block != null && block.validate()) {
					block.setLiningRingId(id);
					m_liningRingBlockService.insertLiningRingBlock(block);
				}
			}
			if (count > 0) {
				Log log = createLog(Constrants.s_liningRing_model, Constrants.s_operation_update, m_liningRing);

				m_logService.insertLog(log);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public List<LiningRingBlock> getBlocks() {
		return m_blocks;
	}

	public void setBlocks(List<LiningRingBlock> blocks) {
		m_blocks = blocks;
	}

	public void setLiningRingBlockService(LiningRingBlockService liningRingBlockService) {
		m_liningRingBlockService = liningRingBlockService;
	}

}