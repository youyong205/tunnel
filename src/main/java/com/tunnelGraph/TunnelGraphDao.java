package com.tunnelGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class TunnelGraphDao {

	private BaseDao m_baseDao;

	public int deleteTunnelGraph(int id) {
		return m_baseDao.delete("tunnelGraph.delete", id);
	}

	public TunnelGraph findByPK(int id) {
		return (TunnelGraph) m_baseDao.queryForObject("tunnelGraph.findById", id);
	}

	public int insertTunnelGraph(TunnelGraph tunnelGraph) {
		return (Integer) m_baseDao.insert("tunnelGraph.insert", tunnelGraph);
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedTunnelGraphsByTunnelIdAndLineType(int tunnelId, String lineType, int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("start", start);
		parameters.put("size", size);
		parameters.put("tunnelId", tunnelId);
		parameters.put("lineType", lineType);
		return m_baseDao.queryForList("tunnelGraph.queryLimitedTunnelGraphsByTunnelIdAndLineType", parameters);
	}

	public int querySizeByTunnelIdAndLineType(int tunnelId, String lineType) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("lineType", lineType);
		return (Integer) m_baseDao.queryForObject("tunnelGraph.querySizeByTunnelIdAndLineType", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateTunnelGraph(TunnelGraph tunnelGraph) {
		return m_baseDao.update("tunnelGraph.update", tunnelGraph);
	}

}
