package com.constructionUnit;

import java.util.List;

public interface ConstructionUnitService {

	public int deleteConstructionUnit(int id);
	
	public ConstructionUnit findByName(String name);

	public ConstructionUnit findByPK(int id);

	public int insertConstructionUnit(ConstructionUnit constructionUnit);

	public List<ConstructionUnit> queryAllConstructionUnits();

	public int queryAllSize();

	public List<ConstructionUnit> queryLimitedConstructionUnits(int start, int size);

	public int updateConstructionUnit(ConstructionUnit constructionUnit);

}
