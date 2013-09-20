package com.flueSheet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class FlueSheetDao {

	private BaseDao m_baseDao;

	public int deleteFlueSheet(int id) {
		return m_baseDao.delete("flueSheet.delete", id);
	}

	public FlueSheet findByName(String name) {
		return (FlueSheet) m_baseDao.queryForObject("flueSheet.findByName", name);
	}

	public FlueSheet findByPK(int id) {
		return (FlueSheet) m_baseDao.queryForObject("flueSheet.findById", id);
	}

	public int insertFlueSheet(FlueSheet flueSheet) {
		return (Integer) m_baseDao.insert("flueSheet.insert", flueSheet);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllFlueSheets() {
		return m_baseDao.queryForList("flueSheet.queryAllFlueSheets");
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedFlueSheets(int tunnelId,int tunnelSectionId,int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("flueSheet.queryLimitedFlueSheets", parameters);
	}

	public int querySizeByTunnelAndSection(int tunnelId,int tunnelSectionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);

		return (Integer)m_baseDao.queryForObject("flueSheet.querySizeByTunnelAndSection",parameters);
   }

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateFlueSheet(FlueSheet flueSheet) {
		return m_baseDao.update("flueSheet.update", flueSheet);
	}

}
