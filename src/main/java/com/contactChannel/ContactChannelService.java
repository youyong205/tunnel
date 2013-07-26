package com.contactChannel;

import java.util.List;

public interface ContactChannelService {

	public int deleteContactChannel(int id);
	
	public ContactChannel findByName(String name);

	public ContactChannel findByPK(int id);
	
	public int insertContactChannel(ContactChannel contactChannel);

	public int queryAllSize();

	public List<ContactChannel> queryLimitedContactChannelsByTunnelId(int tunnelId,int start, int size);

	public List<ContactChannel> queryLimitedContactChannels(int start, int size);

	public int querySizeByTunnelId(int tunnelId);

	public int updateContactChannel(ContactChannel contactChannel);

}
