package com.mailRecord;

import java.util.Date;

import com.tunnel.Tunnel;

public class MailRecord {
	private int m_id;

	private int m_tunnelId;
	
	private Tunnel m_tunnel;
	
	private int m_type;

	private Date m_time;

	private String m_receivers;

	private String m_title;

	private String m_content;

	private int m_status;

	private Date m_creationDate;

	public int getId() {
		return m_id;
	}

	public void setId(int id) {
		m_id = id;
	}

	public int getType() {
		return m_type;
	}

	public void setType(int type) {
		m_type = type;
	}

	public Date getTime() {
		return m_time;
	}

	public void setTime(Date time) {
		m_time = time;
	}

	public String getReceivers() {
		return m_receivers;
	}

	public void setReceivers(String receivers) {
		m_receivers = receivers;
	}

	public String getTitle() {
		return m_title;
	}
	
	public int getTunnelId() {
   	return m_tunnelId;
   }

	public void setTunnelId(int tunnelId) {
   	m_tunnelId = tunnelId;
   }

	public void setTitle(String title) {
		m_title = title;
	}

	public String getContent() {
		return m_content;
	}

	public void setContent(String content) {
		m_content = content;
	}

	public int getStatus() {
		return m_status;
	}

	public void setStatus(int status) {
		m_status = status;
	}

	public Date getCreationDate() {
		return m_creationDate;
	}

	public void setCreationDate(Date creationDate) {
		m_creationDate = creationDate;
	}

	public Tunnel getTunnel() {
   	return m_tunnel;
   }

	public void setTunnel(Tunnel tunnel) {
   	m_tunnel = tunnel;
   }

	@Override
	public String toString() {
		return "Resource [m_id=" + m_id + ", m_type=" + m_type + ", m_time=" + m_time + ", m_receivers=" + m_receivers
		      + ", m_title=" + m_title + ", m_status=" + m_status + "]";
	}


}
