package com.tunnel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

public class TunnelServiceImpl implements TunnelService {

	private TunnelDao m_tunnelDao;

	private Logger m_logger = Logger.getLogger(TunnelServiceImpl.class);

	private static final int SIZE = 100;

	private Map<Integer, Tunnel> m_tunnels = new LinkedHashMap<Integer, Tunnel>(SIZE) {

		private static final long serialVersionUID = 1L;

		@Override
		protected boolean removeEldestEntry(Entry<Integer, Tunnel> eldest) {
			return size() > SIZE;
		}
	};

	@Override
	public int deleteTunnel(int id) {
		try {
			int result = m_tunnelDao.deleteTunnel(id);
		
			m_tunnels.remove(id);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public Tunnel findByName(String name) {
		try {
			return m_tunnelDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public Tunnel findByPK(int id) {
		Tunnel tunnel = m_tunnels.get(id);
		if (tunnel == null) {
			try {
				tunnel = m_tunnelDao.findByPK(id);

				if (tunnel != null) {
					m_tunnels.put(id, tunnel);
				}
			} catch (Exception e) {
				m_logger.error(e.getMessage(), e);
			}
		}
		return tunnel;
	}

	@Override
	public int insertTunnel(Tunnel tunnel) {
		try {
			int result = m_tunnelDao.insertTunnel(tunnel);
			
			m_tunnels.put(tunnel.getId(), tunnel);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public int queryAllSize() {
		try {
			return m_tunnelDao.queryAllSize();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Tunnel> queryAllTunnels() {
		try {
			return m_tunnelDao.queryAllTunnels();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Tunnel>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tunnel> queryLimitedTunnels(int start, int size) {
		try {
			return m_tunnelDao.queryLimitedTunnels(start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<Tunnel>();
		}
	}

	public void setTunnelDao(TunnelDao tunnelDao) {
		m_tunnelDao = tunnelDao;
	}

	@Override
	public int updateTunnel(Tunnel tunnel) {
		try {
			int result = m_tunnelDao.updateTunnel(tunnel);
			
			m_tunnels.put(tunnel.getId(), tunnel);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
