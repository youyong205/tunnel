package com.bracket;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class BracketServiceImpl implements BracketService {

	private BracketDao m_bracketDao;

	private Logger m_logger = Logger.getLogger(BracketServiceImpl.class);

	@Override
	public int deleteBracket(int id) {
		try {
			int result = m_bracketDao.deleteBracket(id);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public Bracket findByName(String name) {
		try {
			return m_bracketDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public Bracket findByPK(int id) {
		try {
			return m_bracketDao.findByPK(id);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public int insertBracket(Bracket bracket) {
		try {
			int result = m_bracketDao.insertBracket(bracket);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bracket> queryLimitedBrackets(int tunnelId, int tunnelSectionId, int start, int size) {
		try {
			return m_bracketDao.queryLimitedBrackets(tunnelId, tunnelSectionId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Bracket>();
		}
	}

	@Override
	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId) {
		try {
			return m_bracketDao.querySizeByTunnelAndSection(tunnelId, tunnelSectionId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setBracketDao(BracketDao bracketDao) {
		m_bracketDao = bracketDao;
	}

	@Override
	public int updateBracket(Bracket bracket) {
		try {
			int result = m_bracketDao.updateBracket(bracket);

			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
