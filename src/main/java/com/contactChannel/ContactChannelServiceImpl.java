package com.contactChannel;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ContactChannelServiceImpl implements ContactChannelService {

	private ContactChannelDao m_contactChannelDao;

	private Logger m_logger = Logger.getLogger(ContactChannelServiceImpl.class);

	@Override
	public int deleteContactChannel(int id) {
		try {
			int result = m_contactChannelDao.deleteContactChannel(id);
			return result;
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@Override
	public ContactChannel findByName(String name) {
		try {
			return m_contactChannelDao.findByName(name);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public ContactChannel findByPK(int id) {
		try {
			return m_contactChannelDao.findByPK(id);

		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public int insertContactChannel(ContactChannel contactChannel) {
		try {
			return m_contactChannelDao.insertContactChannel(contactChannel);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ContactChannel> queryAllContactChannels() {
		try {
			return m_contactChannelDao.queryAllContactChannels();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<ContactChannel>();
		}
	}

	@Override
	public int queryAllSize() {
		try {
			return m_contactChannelDao.queryAllSize();
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContactChannel> queryLimitedContactChannels(int start, int size) {
		try {
			return m_contactChannelDao.queryLimitedContactChannels(start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<ContactChannel>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContactChannel> queryLimitedContactChannelsByTunnelId(int tunnelId, int start, int size) {
		try {
			return m_contactChannelDao.queryLimitedContactChannelsByTunnelId(tunnelId, start, size);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return new ArrayList<ContactChannel>();
		}
	}

	@Override
	public int querySizeByTunnelId(int tunnelId) {
		try {
			return m_contactChannelDao.querySizeByTunnelId(tunnelId);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

	public void setContactChannelDao(ContactChannelDao contactChannelDao) {
		m_contactChannelDao = contactChannelDao;
	}

	@Override
	public int updateContactChannel(ContactChannel contactChannel) {
		try {
			return m_contactChannelDao.updateContactChannel(contactChannel);
		} catch (Exception e) {
			m_logger.error(e.getMessage(), e);
			return -1;
		}
	}

}
