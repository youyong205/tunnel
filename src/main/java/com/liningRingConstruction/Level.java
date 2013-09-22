package com.liningRingConstruction;

public enum Level {

	A("A"),

	B("B"),

	C("C"),

	D("D"),

	E("E");

	private Level(String name) {
		m_name = name;
	}

	private String m_name;

	public String getName() {
   	return m_name;
   }

	public void setName(String name) {
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
}
