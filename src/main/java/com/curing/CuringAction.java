package com.curing;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.Constrants;
import com.FileUploadAction;
import com.constructionUnit.ConstructionUnit;
import com.constructionUnit.ConstructionUnitService;
import com.document.Document;
import com.log.Log;
import com.tunnel.Tunnel;
import com.tunnel.TunnelService;

public abstract class CuringAction extends FileUploadAction {

	private static final long serialVersionUID = 2802256599554299998L;

	private Logger m_logger = Logger.getLogger(CuringAction.class);

	protected List<Curing> m_curings;

	protected int m_curingId;

	private Tunnel m_tunnel;

	@Autowired
	protected CuringService m_curingService;

	@Autowired
	protected ConstructionUnitService m_constructionUnitService;

	protected List<ConstructionUnit> m_constructionUnits;

	protected List<Tunnel> m_tunnels;

	@Autowired
	protected TunnelService m_tunnelService;

	protected int m_tunnelId;

	protected Curing m_curing = new Curing();

	public String curingAdd() {
		m_tunnel = m_tunnelService.findByPK(m_tunnelId);
		m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
		m_tunnels = m_tunnelService.queryAllTunnels();
		return SUCCESS;
	}

	public String curingAddSubmit() {
		try {
			int id = m_curingService.insertCuring(m_curing);
			if (m_uploadFile.getFile() != null) {
				int documentId = m_documentService.insertDocument(Constrants.s_contactChannel_curing_model, m_uploadFile);
				m_curing.setDocumentId(documentId);
			}
			if (id > 0) {
				Log log = createLog(getActionModule(), Constrants.s_operation_add, m_curing);

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

	public String curingDelete() {
		try {
			int count = m_curingService.deleteCuring(m_curingId);
			if (count > 0) {
				Log log = createLog(getActionModule(), Constrants.s_operation_delete, m_curingId);

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

	public String curingList() {
		try {
			m_tunnels = m_tunnelService.queryAllTunnels();
			m_totalSize = m_curingService.queryCuringSizeByType(m_tunnelId, getModule());
			m_totalPages = computeTotalPages(m_totalSize);
			int start = (m_index - 1) * SIZE;
			if (start < 0) {
				start = 0;
			}
			m_curings = m_curingService.queryLimitedCuringsByType(m_tunnelId, getModule(), start, SIZE);

			for (Curing curing : m_curings) {
				curing.setComponentName(getComponentNameById(curing.getComponentId()));
			}
			return SUCCESS;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String curingUpdate() {
		try {
			m_constructionUnits = m_constructionUnitService.queryAllConstructionUnits();
			m_curing = m_curingService.findByPK(m_curingId);
			m_tunnel = m_tunnelService.findByPK(m_curing.getTunnelId());
			m_tunnels = m_tunnelService.queryAllTunnels();
			int documentId = m_curing.getDocumentId();

			if (documentId > 0) {
				m_curing.setDocument(m_documentService.findByPK(documentId));
			}
			if (m_curing != null) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return ERROR;
		}
	}

	public String curingUpdateSubmit() {
		try {
			int count = m_curingService.updateCuring(m_curing);
			int documentId = m_curing.getDocumentId();
			if (documentId > 0) {
				Document document = m_documentService.findByPK(documentId);
				m_documentService.updateDocument(Constrants.s_contactChannel_curing_model, m_uploadFile, document);
			} else {
				documentId = m_documentService.insertDocument(Constrants.s_contactChannel_curing_model, m_uploadFile);
				m_curing.setDocumentId(documentId);
			}

			if (count > 0) {
				Log log = createLog(getActionModule(), Constrants.s_operation_update, m_curing);

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

	public abstract String getActionModule();

	public abstract String getComponentNameById(int id);

	public List<ConstructionUnit> getConstructionUnits() {
		return m_constructionUnits;
	}

	public Curing getCuring() {
		return m_curing;
	}

	public List<Curing> getCurings() {
		return m_curings;
	}

	public abstract List<Item> getItems();

	public abstract String getModule();

	public Tunnel getTunnel() {
		return m_tunnel;
	}

	public int getTunnelId() {
		return m_tunnelId;
	}

	public List<Tunnel> getTunnels() {
		return m_tunnels;
	}

	public void setConstructionUnitService(ConstructionUnitService constructionUnitService) {
		m_constructionUnitService = constructionUnitService;
	}

	public void setCuring(Curing curing) {
		m_curing = curing;
	}

	public void setCuringId(int curingId) {
		m_curingId = curingId;
	}
	public void setCuringService(CuringService curingService) {
		m_curingService = curingService;
	}

	public void setTunnel(Tunnel tunnel) {
		m_tunnel = tunnel;
	}

	public void setTunnelId(int tunnelId) {
		m_tunnelId = tunnelId;
	}

	public void setTunnelService(TunnelService tunnelService) {
		m_tunnelService = tunnelService;
	}

	protected void validateTunnelId() {
		m_tunnelId = m_tunnelService.queryDefaultTunnelId();
	}

	public static class Item {

		private int m_id;

		private String m_name;

		public int getId() {
			return m_id;
		}

		public String getName() {
			return m_name;
		}

		public void setId(int id) {
			m_id = id;
		}

		public void setName(String name) {
			m_name = name;
		}
	}

}