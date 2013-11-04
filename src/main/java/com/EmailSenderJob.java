package com;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;

import com.mailRecord.MailRecord;
import com.mailRecord.MailRecordService;
import com.mailRecord.MailSender;

public class EmailSenderJob implements java.lang.Runnable, InitializingBean {

	private BlockingQueue<MailRecord> m_records = new LinkedBlockingQueue<MailRecord>(1000);

	private MailRecordService m_mailRecordService;

	private MailSender m_mailSender;

	public boolean addRecord(MailRecord mailRecord) {
		return m_records.offer(mailRecord);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Thread thread = new Thread(this);

		thread.start();
	}

	private List<String> parseReceivers(String email) {
		String[] emails = email.split(",");
		List<String> result = new ArrayList<String>();

		for (String str : emails) {
			result.add(str);
		}
		return result;
	}

	@Override
	public void run() {
		boolean active = true;
		System.out.println("===============send email start!===============");
		while (active) {
			try {
				MailRecord record = m_records.poll(5, TimeUnit.MILLISECONDS);

				if (record != null) {
					String title = record.getTitle();
					String content = record.getContent();
					List<String> emails = parseReceivers(record.getReceivers());

					boolean result = m_mailSender.sendEmail(title, content, emails);

					if (result) {
						record.setStatus(MailRecord.SUCCESS);
					} else {
						record.setStatus(MailRecord.FAIL);
					}
					m_mailRecordService.insertMailRecord(record);
				}
			} catch (InterruptedException e) {
				active = false;
			}
		}
	}

	public void setMailRecordService(MailRecordService mailRecordService) {
		m_mailRecordService = mailRecordService;
	}

	public void setMailSender(MailSender mailSender) {
		m_mailSender = mailSender;
	}

}
