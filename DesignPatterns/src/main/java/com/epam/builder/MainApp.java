package com.epam.builder;

public class MainApp {

	public static void main(String[] args) {
		Game game = new Game.GameBuilder("GOD OF WAR", "ACTION").setAvailableInCD(true).build();
		System.out.println(game);

	}

}
