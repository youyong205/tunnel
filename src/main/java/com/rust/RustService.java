package com.rust;

import java.util.Date;
import java.util.List;

public interface RustService {

	public int deleteRust(int id);

	public Rust findByName(String name);

	public Rust findByPK(int id);

	public int insertRust(Rust rust);

	public Rust queryLastestDeformation(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public List<Rust> queryLimitedRusts(int tunnelId, int tunnelSectionId,
	      int liningRingConstructionId, int start, int size);

	public List<Rust> queryRustByDuration(int liningRingConstructionId, Date start,
	      Date end);

	public int querySizeByTunnelAndSection(int tunnelId, int tunnelSectionId, int liningRingConstructionId);

	public int updateRust(Rust rust);

}
