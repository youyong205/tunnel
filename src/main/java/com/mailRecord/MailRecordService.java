package com.mailRecord;

import java.util.Date;
import java.util.List;

public interface MailRecordService {

	public int deleteMailRecord(int id);

	public MailRecord findByPK(int id);

	public int insertMailRecord(MailRecord mailRecord);

	public int queryAllSizeByTunnelAndType(int tunnelId,int type);

	public List<MailRecord> queryLimitedMailRecordsByTunnelAndType(int tunnelId,int type,int start, int size);

	public int updateMailRecord(MailRecord mailRecord);
	
	public MailRecord findDailyRecordByTime(int tunnelId,Date date);

}
