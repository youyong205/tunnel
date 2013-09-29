package com.constructionUnit;

import java.util.List;

import org.apache.log4j.Logger;

import com.Authority;
import com.Modules;
import com.Operation;
import com.PagedAction;
import com.log.Log;

public class ConstructionUnitAction extends PagedAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(ConstructionUnitAction.class);

	private List<ConstructionUnit> m_constructionUnits;

	private int m_constructionUnitId;

	private ConstructionUnitService m_constructionUnitService;

	private ConstructionUnit m_constructionUnit = new ConstructionUnit();

	public String constructionUnitAdd() {
		return SUCCESS;
	}

	public String constructionUnitAddSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_constructionUnit_model, Operation.s_operation_add));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int id = m_constructionUnitService.insertConstructionUnit(m_constructionUnit);
			if (id > 0) {
				Log log = createLog(Modules.s_constructionUnit_model, Operation.s_operation_add, m_constructionUnit);

				m_logService.insertLog(log);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String constructionUnitDelete() {
		Authority auth = checkAuthority(buildResource(Modules.s_constructionUnit_model, Operation.s_operation_delete));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int count = m_constructionUnitService.deleteConstructionUnit(m_constructionUnitId);
			if (count > 0) {
				Log log = createLog(Modules.s_constructionUnit_model, Operation.s_operation_delete, m_constructionUnitId);

				m_logService.insertLog(log);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String constructionUnitList() {
		Authority auth = checkAuthority(buildResource(Modules.s_constructionUnit_model, Operation.s_operation_detail));
		if (auth != null) {
			return auth.getName();
		}
		try {
			m_totalSize = m_constructionUnitService.queryAllSize();
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_constructionUnits = m_constructionUnitService.queryLimitedConstructionUnits(start, SIZE);
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String constructionUnitUpdate() {
		try {
			m_constructionUnit = m_constructionUnitService.findByPK(m_constructionUnitId);
			if (m_constructionUnit != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String constructionUnitUpdateSubmit() {
		Authority auth = checkAuthority(buildResource(Modules.s_constructionUnit_model, Operation.s_operation_update));
		if (auth != null) {
			return auth.getName();
		}
		try {
			int count = m_constructionUnitService.updateConstructionUnit(m_constructionUnit);
			if (count > 0) {
				Log log = createLog(Modules.s_constructionUnit_model, Operation.s_operation_update, m_constructionUnit);

				m_logService.insertLog(log);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	@Override
	public String getActionModule() {
		return Modules.s_constructionUnit_model;
	}

	public ConstructionUnit getConstructionUnit() {
		return m_constructionUnit;
	}

	public List<ConstructionUnit> getConstructionUnits() {
		return m_constructionUnits;
	}

	public void setConstructionUnit(ConstructionUnit constructionUnit) {
		m_constructionUnit = constructionUnit;
	}

	public void setConstructionUnitId(int constructionUnitId) {
		m_constructionUnitId = constructionUnitId;
	}

	public void setConstructionUnitService(ConstructionUnitService constructionUnitService) {
		m_constructionUnitService = constructionUnitService;
	}

}