package com.rectangleComponent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class RectangleComponentDao {

	private BaseDao m_baseDao;

	public int deleteRectangleComponent(int id) {
		return m_baseDao.delete("rectangleComponent.delete", id);
	}

	public RectangleComponent findByName(String name) {
		return (RectangleComponent) m_baseDao.queryForObject("rectangleComponent.findByName", name);
	}

	public RectangleComponent findByPK(int id) {
		return (RectangleComponent) m_baseDao.queryForObject("rectangleComponent.findById", id);
	}

	public int insertRectangleComponent(RectangleComponent rectangleComponent) {
		return (Integer) m_baseDao.insert("rectangleComponent.insert", rectangleComponent);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllRectangleComponents() {
		return m_baseDao.queryForList("rectangleComponent.queryAllRectangleComponents");
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedRectangleComponents(int tunnelId,int tunnelSectionId,int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("rectangleComponent.queryLimitedRectangleComponents", parameters);
	}

	public int querySizeByTunnelAndSection(int tunnelId,int tunnelSectionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);

		return (Integer)m_baseDao.queryForObject("rectangleComponent.querySizeByTunnelAndSection",parameters);
   }

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateRectangleComponent(RectangleComponent rectangleComponent) {
		return m_baseDao.update("rectangleComponent.update", rectangleComponent);
	}

}
