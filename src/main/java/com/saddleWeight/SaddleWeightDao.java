package com.saddleWeight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class SaddleWeightDao {

	private BaseDao m_baseDao;

	public int deleteSaddleWeight(int id) {
		return m_baseDao.delete("saddleWeight.delete", id);
	}

	public SaddleWeight findByName(String name) {
		return (SaddleWeight) m_baseDao.queryForObject("saddleWeight.findByName", name);
	}

	public SaddleWeight findByPK(int id) {
		return (SaddleWeight) m_baseDao.queryForObject("saddleWeight.findById", id);
	}

	public int insertSaddleWeight(SaddleWeight saddleWeight) {
		return (Integer) m_baseDao.insert("saddleWeight.insert", saddleWeight);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllSaddleWeights() {
		return m_baseDao.queryForList("saddleWeight.queryAllSaddleWeights");
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedSaddleWeights(int tunnelId,int tunnelSectionId,int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("saddleWeight.queryLimitedSaddleWeights", parameters);
	}

	public int querySizeByTunnelAndSection(int tunnelId,int tunnelSectionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);

		return (Integer)m_baseDao.queryForObject("saddleWeight.querySizeByTunnelAndSection",parameters);
   }

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateSaddleWeight(SaddleWeight saddleWeight) {
		return m_baseDao.update("saddleWeight.update", saddleWeight);
	}

}
