package com.epam.factory;

public class Xbox extends Game {

	private String name;
	private String type;

	public Xbox(String name, String type) {
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
