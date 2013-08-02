package com.rectangleComponent;

import java.util.List;

public interface RectangleComponentService {

	public int deleteRectangleComponent(int id);

	public RectangleComponent findByName(String name);

	public RectangleComponent findByPK(int id);

	public int insertRectangleComponent(RectangleComponent rectangleComponent);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId);

	public List<RectangleComponent> queryLimitedRectangleComponents(int tunnelId, int tunnelSectionId, int start,
	      int size);

	public int updateRectangleComponent(RectangleComponent rectangleComponent);

}
