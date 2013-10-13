package com.rust;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.liningRingConstruction.LiningRingConstructionService;

public class RustServiceImpl implements RustService {

	private RustDao m_rustDao;

	private LiningRingConstructionService m_liningRingConstructionService;

	private Logger m_logger = Logger.getLogger(RustServiceImpl.class);

	@Override
	public int deleteRust(int id) {
		try {
			int result = m_rustDao.deleteRust(id);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public Rust findByName(String name) {
		try {
			return m_rustDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public Rust findByPK(int id) {
		try {
			return m_rustDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertRust(Rust rust) {
		try {
			int result = m_rustDao.insertRust(rust);

			m_liningRingConstructionService.updateRustState(rust);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public Rust queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		try {
			return m_rustDao.queryLastestDeformation(tunnelId, tunnelSectionId, liningRingConstructionId);

		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rust> queryLimitedRusts(int tunnelId, int tunnelSectionId, int liningRingConstructionId, int start,
	      int size) {
		try {
			return m_rustDao.queryLimitedRusts(tunnelId, tunnelSectionId, liningRingConstructionId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Rust>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rust> queryRustByDuration(int rustId, Date start, Date end) {
		try {
			return m_rustDao.queryRustByDuration(rustId, start, end);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Rust>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		try {
			return m_rustDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId, liningRingConstructionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setLiningRingConstructionService(LiningRingConstructionService liningRingConstructionService) {
		m_liningRingConstructionService = liningRingConstructionService;
	}

	public void setRustDao(RustDao rustDao) {
		m_rustDao = rustDao;
	}

	@Override
	public int updateRust(Rust rust) {
		try {
			int result = m_rustDao.updateRust(rust);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}
	
	@SuppressWarnings("unchecked")
   @Override
   public List<Rust> queryByIds(List<Integer> ids) {
		try {
			return m_rustDao.queryByIds(ids);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Rust>();
		}
   }

}
