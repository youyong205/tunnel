package com.rust;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.BaseDao;

public class RustDao {

	private BaseDao m_baseDao;

	public int deleteRust(int id) {
		return m_baseDao.delete("rust.delete", id);
	}

	public Rust findByName(String name) {
		return (Rust) m_baseDao.queryForObject("rust.findByName", name);
	}

	public Rust findByPK(int id) {
		return (Rust) m_baseDao.queryForObject("rust.findById", id);
	}

	public int insertRust(Rust rust) {
		return (Integer) m_baseDao.insert("rust.insert", rust);
	}

	@SuppressWarnings("rawtypes")
	public List queryAllRusts() {
		return m_baseDao.queryForList("rust.queryAllRusts");
	}

	public Rust queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (Rust) m_baseDao.queryForObject("rust.queryLastestDeformation", parameters);
	}

	@SuppressWarnings("rawtypes")
	public List queryLimitedRusts(int tunnelId, int tunnelSectionId, int liningRingConstructionId, int start, int size) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("size", size);

		return m_baseDao.queryForList("rust.queryLimitedRusts", parameters);
	}

	@SuppressWarnings("rawtypes")
	public List queryRustByDuration(int liningRingConstructionId, Date start, Date end) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("liningRingConstructionId", liningRingConstructionId);
		parameters.put("start", start);
		parameters.put("end", end);

		return m_baseDao.queryForList("rust.queryRustByDuration", parameters);
	}

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("tunnelId", tunnelId);
		parameters.put("tunnelSectionId", tunnelSectionId);
		parameters.put("liningRingConstructionId", liningRingConstructionId);

		return (Integer) m_baseDao.queryForObject("rust.querySizeByTunnelAndSection", parameters);
	}

	public void setBaseDao(BaseDao baseDao) {
		m_baseDao = baseDao;
	}

	public int updateRust(Rust rust) {
		return m_baseDao.update("rust.update", rust);
	}
	
	@SuppressWarnings("rawtypes")
   public List queryByIds(List<Integer> ids) {
		if(ids.size()>0){
			return m_baseDao.queryForList("rust.queryByIds", ids);
		}else{
			return new ArrayList();
		}
   }
}
