package com.linePipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class LinePipeDao {

	private BaseDao m_baseDao;

	public int deleteLinePipe(int id) {
		return m_baseDao.delete("linePipe.delete", id);
	}

	public LinePipe findByName(String name) {
		return (LinePipe) m_baseDao.queryForObject("linePipe.findByName", name);
	}

	public LinePipe findByPK(int id) {
		return (LinePipe) m_baseDao.queryForObject("linePipe.findById", id);
	}

	public int insertLinePipe(LinePipe linePipe) {
		return (Integer) m_baseDao.insert("linePipe.insert", linePipe);
	}

	public int querySizeByTunnelAndSection(int tunnelId,int tunnelSectionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);

		return (Integer)m_baseDao.queryForObject("linePipe.querySizeByTunnelAndSection",parameters);
   }

	@SuppressWarnings("rawtypes")
	public List queryAllLinePipes() {
		return m_baseDao.queryForList("linePipe.queryAllLinePipes");
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedLinePipes(int tunnelId,int tunnelSectionId,int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("linePipe.queryLimitedLinePipes", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateLinePipe(LinePipe linePipe) {
		return m_baseDao.update("linePipe.update", linePipe);
	}

}
