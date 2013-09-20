package com.rectangleComponent;

import java.util.List;

public interface RectangleComponentService {

	public int deleteRectangleComponent(int id);

	public RectangleComponent findByName(String name);

	public RectangleComponent findByPK(int id);

	public int insertRectangleComponent(RectangleComponent rectangleComponent);

	public List<RectangleComponent> queryLimitedRectangleComponents(int tunnelId, int tunnelSectionId, int start,
	      int size);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId);

	public int updateRectangleComponent(RectangleComponent rectangleComponent);

}
