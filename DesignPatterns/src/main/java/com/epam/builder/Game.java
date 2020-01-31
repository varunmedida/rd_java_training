package com.epam.builder;

public class Game {
	private String name;
	private String type;
	private boolean availableInCD;

	public Game(GameBuilder gameBuilder) {
		this.name = gameBuilder.name;
		this.type = gameBuilder.type;
		this.availableInCD = gameBuilder.availableInCD;
	}

	@Override
	public String toString() {
		return "Game [name=" + name + ", type=" + type + ", availableInCD=" + availableInCD + "]";
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public boolean isAvailableInCD() {
		return availableInCD;
	}

	public static class GameBuilder {
		private String name;
		private String type;
		private boolean availableInCD;

		public GameBuilder(String name, String type) {
			this.name = name;
			this.type = type;
		}

		public GameBuilder setAvailableInCD(boolean availableInCD) {
			this.availableInCD = availableInCD;
			return this;
		}

		public Game build() {
			return new Game(this);
		}

	}

}
