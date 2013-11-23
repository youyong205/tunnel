package com.tunnelGraph;

import java.util.List;

public interface TunnelGraphService {

	public int deleteTunnelGraph(int id);

	public TunnelGraph findByPK(int id);

	public int insertTunnelGraph(TunnelGraph tunnelGraph);

	public List<TunnelGraph> queryLimitedTunnelGraphsByTunnelIdAndLineType(int tunnelId, String lineType, int start,
	      int size);

	public int querySizeByTunnelIdAndLineType(int tunnelId, String lineType);

	public int updateTunnelGraph(TunnelGraph tunnelGraph);

}
