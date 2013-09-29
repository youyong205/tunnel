package com.tunnel;

import java.util.List;

public interface TunnelService {

	public int deleteTunnel(int id);

	public Tunnel findByName(String name);

	public Tunnel findByPK(int id);

	public int insertTunnel(Tunnel tunnel);

	public int queryAllSize();

	public List<Tunnel> queryAllTunnels();

	public int queryDefaultTunnelId();

	public List<Tunnel> queryLimitedTunnels(int start, int size);

	public int updateTunnel(Tunnel tunnel);

}
