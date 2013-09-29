package com.tunnel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class TunnelDao {

	private BaseDao m_baseDao;

	public int deleteTunnel(int id) {
		return m_baseDao.delete("tunnel.delete", id);
	}

	public Tunnel findByName(String name) {
		return (Tunnel) m_baseDao.queryForObject("tunnel.findByName", name);
	}

	public Tunnel findByPK(int id) {
		return (Tunnel) m_baseDao.queryForObject("tunnel.findById", id);
	}

	public int insertTunnel(Tunnel tunnel) {
		return (Integer) m_baseDao.insert("tunnel.insert", tunnel);
	}

	public int queryAllSize() {
		return (Integer) m_baseDao.queryForObject("tunnel.queryAllSize", null);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllTunnels() {
		return m_baseDao.queryForList("tunnel.queryAllTunnels");
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedTunnels(int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("tunnel.queryLimitedTunnels", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateTunnel(Tunnel tunnel) {
		return m_baseDao.update("tunnel.update", tunnel);
	}

}
