package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;

public class LineChart {

	private List<Data> series = new ArrayList<Data>();

	public void setSeries(List<Data> series) {
		this.series = series;
	}

	public void add(String subTitle, Map<Long, Double> data) {
		series.add(new Data(subTitle, data));
	}

	public String getJsonString() {
		Gson gson = new Gson();

		return gson.toJson(this);
	}

	public List<Data> getSeries() {
		return series;
	}

	public static class Data {

		private String name;

		private Long[][] data;

		public Data(String name, Map<Long, Double> data) {
			int length = data.size();
			this.data = new Long[length][2];
			this.name = name;
			int index = 0;
			for (Entry<Long, Double> entry : data.entrySet()) {
				this.data[index][0] = entry.getKey();
				Double value = entry.getValue();
				this.data[index][1] = value.longValue();
				index++;
			}
		}

		public String getName() {
			return name;
		}
	}

	public static class Item {

		private String x;

		private String y;

		public Item(String x, String y) {
			this.x = x;
			this.y = y;
		}

		public String getX() {
			return x;
		}

		public String getY() {
			return y;
		}
	}

}
