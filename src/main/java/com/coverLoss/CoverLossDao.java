package com.coverLoss;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class CoverLossDao {

	private BaseDao m_baseDao;

	public int deleteCoverLoss(int id) {
		return m_baseDao.delete("coverLoss.delete", id);
	}

	public CoverLoss findByName(String name) {
		return (CoverLoss) m_baseDao.queryForObject("coverLoss.findByName", name);
	}

	public CoverLoss findByPK(int id) {
		return (CoverLoss) m_baseDao.queryForObject("coverLoss.findById", id);
	}

	public int insertCoverLoss(CoverLoss coverLoss) {
		return (Integer) m_baseDao.insert("coverLoss.insert", coverLoss);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllCoverLosss() {
		return m_baseDao.queryForList("coverLoss.queryAllCoverLosss");
	}

	@SuppressWarnings("rawtypes")
	public List queryCoverLossByDuration(int liningRingConstructionId, Date start, Date end) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("end", end);

		return m_baseDao.queryForList("coverLoss.queryCoverLossByDuration", parameters);
	}

	public CoverLoss queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (CoverLoss) m_baseDao.queryForObject("coverLoss.queryLastestDeformation", parameters);
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedCoverLosss(int tunnelId, int tunnelSectionId, int liningRingConstructionId, int start,
	      int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("coverLoss.queryLimitedCoverLosss", parameters);
	}

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (Integer) m_baseDao.queryForObject("coverLoss.querySizeByTunnelAndSection", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateCoverLoss(CoverLoss coverLoss) {
		return m_baseDao.update("coverLoss.update", coverLoss);
	}

	@SuppressWarnings("rawtypes")
	public List queryByIds(List<Integer> ids) {
		if (ids.size() > 0) {
			return m_baseDao.queryForList("coverLoss.queryByIds", ids);
		} else {
			return new ArrayList();
		}
	}

}
