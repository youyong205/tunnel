package com.settlement;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class SettlementDao {

	private BaseDao m_baseDao;

	public int deleteSettlement(int id) {
		return m_baseDao.delete("settlement.delete", id);
	}

	public Settlement findByName(String name) {
		return (Settlement) m_baseDao.queryForObject("settlement.findByName", name);
	}

	public Settlement findByPK(int id) {
		return (Settlement) m_baseDao.queryForObject("settlement.findById", id);
	}

	public int insertSettlement(Settlement settlement) {
		return (Integer) m_baseDao.insert("settlement.insert", settlement);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllSettlements() {
		return m_baseDao.queryForList("settlement.queryAllSettlements");
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedSettlements(int tunnelId, int tunnelSectionId, int liningRingConstructionId,
	      int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("settlement.queryLimitedSettlements", parameters);
	}

	@SuppressWarnings("rawtypes")
	public List querySettlementByDuration(int liningRingConstructionId, Date start, Date end) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("end", end);

		return m_baseDao.queryForList("settlement.querySettlementByDuration", parameters);
	}

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (Integer) m_baseDao.queryForObject("settlement.querySizeByTunnelAndSection", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateSettlement(Settlement settlement) {
		return m_baseDao.update("settlement.update", settlement);
	}

	public Settlement queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (Settlement) m_baseDao.queryForObject("settlement.queryLastestDeformation",
		      parameters);
	}

}
