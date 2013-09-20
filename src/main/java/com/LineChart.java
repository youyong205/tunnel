package com;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class LineChart {

	private List<Data> series = new ArrayList<Data>();

	public void add(String title, Map<String, Double> data) {
		series.add(new Data(title, data));
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

		private Map<String, Double> data = new LinkedHashMap<String, Double>();

		public Data(String name, Map<String, Double> data) {
			this.name = name;
			this.data = data;
		}

		public Map<String, Double> getData() {
			return data;
		}

		public String getName() {
			return name;
		}

	}

}
