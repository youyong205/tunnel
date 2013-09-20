package com.liningRing;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class LiningRingGraph {

	private double angle;

	private Map<String, Double> blocks = new LinkedHashMap<String, Double>();

	public LiningRingGraph(double angle) {
		this.angle = angle;
	}

	public LiningRingGraph addBlocksInfo(List<LiningRingBlock> liningRingBlocks) {
		for (LiningRingBlock block : liningRingBlocks) {
			blocks.put(buildIndexText(block.getBlockIndex()), block.getAngle());
		}

		return this;
	}

	public String buildIndexText(int i) {
		return "第" + i + "块";
	}
	
	public double getAngle() {
   	return angle;
   }

	public Map<String, Double> getBlocks() {
   	return blocks;
   }

	public String getGsonString() {
		Gson gson = new Gson();

		return gson.toJson(this);
	}

	public void setAngle(double angle) {
   	this.angle = angle;
   }

	public void setBlocks(Map<String, Double> blocks) {
   	this.blocks = blocks;
   }
}
