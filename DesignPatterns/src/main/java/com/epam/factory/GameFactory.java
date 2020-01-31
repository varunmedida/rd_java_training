package com.epam.factory;

public class GameFactory {
	
	public static Game getGame(String console, String name,String type) {
		if("Playstation".equalsIgnoreCase(console)) {
			return new PlayStation(name, type);
		}else if("Xbox".equalsIgnoreCase(console)) {
			return new Xbox(name, type);
		}
		return null;
	}
}
