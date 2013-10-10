package com.mailRecord;

import java.util.List;

public interface MailRecordService {

	public int deleteMailRecord(int id);

	public MailRecord findByPK(int id);

	public int insertMailRecord(MailRecord mailRecord);

	public int queryAllSize();

	public List<MailRecord> queryLimitedMailRecords(int start, int size);

	public int updateMailRecord(MailRecord mailRecord);

}
