package com.epam.singleton;

public class StaticBlockInitialization {
	private static StaticBlockInitialization instance;
	private StaticBlockInitialization() {}
	static {
		try {
			instance= new StaticBlockInitialization();
		}catch(Exception e) {
			throw new RuntimeException("Exception occured during creation of singleton instance");
		}
	}
	public StaticBlockInitialization getInstance() {
		return instance;
	}
}
