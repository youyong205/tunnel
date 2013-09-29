package com.liningRingConstruction;

public enum Level {

	A("A"),

	B("B"),

	C("C"),

	D("D"),

	E("E");

	private String m_name;

	private Level(String name) {
		m_name = name;
	}

	public Level findByName(String str, Level defaultLevel) {
		Level[] levels = Level.values();

		for (Level level : levels) {
			if (level.getName().equals(str)) {
				return level;
			}
		}

		return defaultLevel;
	}

	public String getName() {
		return m_name;
	}

	public void setName(String name) {
		m_name = name;
	}
}
