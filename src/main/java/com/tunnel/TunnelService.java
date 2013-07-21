package com.tunnel;

import java.util.List;

public interface TunnelService {

	public int queryAllSize();
	
	public List<Tunnel> queryAllTunnels();

	public List<Tunnel> queryLimitedTunnels(int start, int size);

	public Tunnel findByName(String name);

	public Tunnel findByPK(int id);

	public int insertTunnel(Tunnel tunnel);

	public int updateTunnel(Tunnel tunnel);

	public int deleteTunnel(int id);

}
