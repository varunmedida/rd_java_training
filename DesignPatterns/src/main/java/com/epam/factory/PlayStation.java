package com.epam.factory;

public class PlayStation extends Game {

	private String name;
	private String type;

	public PlayStation(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}
	@Override
	public String getName() {

		return name;
	}
	@Override
	public String getType() {

		return type;
	}

}
