package com;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import com.liningRingConstruction.Level;
import com.liningRingConstruction.LiningRingConstruction;
import com.liningRingConstruction.LiningRingConstructionService;
import com.mailRecord.MailRecord;
import com.mailRecord.MailRecordService;
import com.tunnel.Tunnel;
import com.tunnel.TunnelService;
import com.tunnelSection.TunnelSection;
import com.tunnelSection.TunnelSectionService;

public class StateJob implements java.lang.Runnable, InitializingBean {

	private TunnelService m_tunnelService;

	private TunnelSectionService m_tunnelSectionService;

	private LiningRingConstructionService m_liningRingConstructionService;

	private EmailSenderJob m_emailSenderJob;

	private MailRecordService m_mailRecordService;

	private long m_duration = 60 * 60 * 1000L;

	private Logger m_logger = Logger.getLogger(StateJob.class);

	@Override
	public void run() {
		boolean active = true;

		while (active) {
			try {
				List<Tunnel> tunnels = m_tunnelService.queryAllTunnels();

				for (Tunnel tunnel : tunnels) {
					int tunnelId = tunnel.getId();
					List<TunnelSection> tunnelSections = m_tunnelSectionService.queryLimitedTunnelSectionsByTunnelId(
					      tunnelId, 0, Integer.MAX_VALUE);
					String tunnelState = Level.A.getName();

					for (TunnelSection tunnelSection : tunnelSections) {
						int tunnelSectionId = tunnelSection.getId();
						List<LiningRingConstruction> constructions = m_liningRingConstructionService
						      .queryLimitedLiningRingConstructions(tunnelId, tunnelSectionId, 0, Integer.MAX_VALUE);
						Map<String, Integer> states = new HashMap<String, Integer>();

						states.put(Level.A.getName(), 0);
						states.put(Level.B.getName(), 0);
						states.put(Level.C.getName(), 0);
						states.put(Level.D.getName(), 0);
						states.put(Level.E.getName(), 0);
						states.put(Level.NULL.getName(), 0);

						for (LiningRingConstruction construction : constructions) {
							String state = construction.getTotalState();
							Integer count = states.get(state);

							if (count == null) {
								states.put(state, 1);
							} else {
								states.put(state, 1 + count);
							}
						}
						int aCount = states.get(Level.A.getName());
						int bCount = states.get(Level.B.getName());
						int cCount = states.get(Level.C.getName());
						int dCount = states.get(Level.D.getName());
						int eCount = states.get(Level.E.getName());
						int totalCount = aCount + bCount + cCount + dCount + eCount;
						String tunnelSectionState = Level.A.getName();

						if (eCount > 0) {
							tunnelSectionState = Level.E.getName();
						} else if (dCount > totalCount * 0.1) {
							tunnelSectionState = Level.D.getName();
						} else if (dCount > 0 || cCount > totalCount * 0.7) {
							tunnelSectionState = Level.C.getName();
						} else if (cCount > 0 || bCount > totalCount * 0.8) {
							tunnelSectionState = Level.B.getName();
						} else {
							tunnelSectionState = Level.A.getName();
						}
						tunnelSection.setState(tunnelSectionState);
						tunnelState = tunnelState + "," + tunnelSectionState;
						m_tunnelSectionService.updateTunnelSection(tunnelSection);
					}
					String state = computeLevel(tunnelState);

					tunnel.setState(state);
					m_tunnelService.updateTunnel(tunnel);

					MailRecord record = buildEmailRecord(tunnelId);

					if (record != null) {
						m_emailSenderJob.addRecord(record);
					} else {
						System.out.println("Exist!!!!" + tunnelId);
					}
				}
			} catch (Exception e) {
				m_logger.error(e);
			}
			try {
				Thread.sleep(m_duration);
			} catch (InterruptedException e) {
				active = false;
			}
		}
	}

	private String computeLevel(String str) {
		if (str == null) {
			return Level.A.getName();
		} else {
			if (str.indexOf(Level.E.getName()) > -1) {
				return Level.E.getName();
			}
			if (str.indexOf(Level.D.getName()) > -1) {
				return Level.D.getName();
			}
			if (str.indexOf(Level.C.getName()) > -1) {
				return Level.C.getName();
			}
			if (str.indexOf(Level.B.getName()) > -1) {
				return Level.B.getName();
			}
			if (str.indexOf(Level.A.getName()) > -1) {
				return Level.A.getName();
			}
			return Level.NULL.getName();
		}
	}

	private MailRecord buildEmailRecord(int tunnelId) {
		MailRecord record = new MailRecord();
		Date current = getCurrentDate();
		MailRecord exist = m_mailRecordService.findDailyRecordByTime(tunnelId, current);

		if (exist == null) {
			record.setReceivers("yong.you@dianping.com");
			record.setTunnelId(tunnelId);
			record.setCreationDate(new Date());
			record.setTime(current);
			record.setTitle("test");
			record.setContent("test");
			record.setType(MailRecord.DAILY);
			return record;
		} else {
			return null;
		}
	}

	private Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();

		cal.setTimeInMillis(0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	public void setTunnelService(TunnelService tunnelService) {
		m_tunnelService = tunnelService;
	}

	public void setTunnelSectionService(TunnelSectionService tunnelSectionService) {
		m_tunnelSectionService = tunnelSectionService;
	}

	public void setLiningRingConstructionService(LiningRingConstructionService liningRingConstructionService) {
		m_liningRingConstructionService = liningRingConstructionService;
	}

	public void setEmailSenderJob(EmailSenderJob emailSenderJob) {
		m_emailSenderJob = emailSenderJob;
	}

	public void setMailRecordService(MailRecordService mailRecordService) {
		m_mailRecordService = mailRecordService;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Thread thread = new Thread(this);

		thread.start();
	}

}
