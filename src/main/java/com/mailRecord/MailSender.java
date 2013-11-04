package com.mailRecord;

import java.util.List;

public interface MailSender {

	public boolean sendEmail(String title, String content, List<String> emails);

}
