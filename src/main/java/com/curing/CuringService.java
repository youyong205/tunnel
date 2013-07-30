package com.curing;

import java.util.List;

public interface CuringService {

	public int deleteCuring(int id);

	public Curing findByPK(int id);

	public int insertCuring(Curing curing);

	public int queryCuringSizeByType(int tunnelId,String type);

	public List<Curing> queryLimitedCuringsByType(int tunnelId,String type, int start, int size);

	public int updateCuring(Curing curing);

}
