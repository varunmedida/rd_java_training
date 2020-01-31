package com.epam.factory;

public class MainApp {

	public static void main(String[] args) {
		
		Game playstation=GameFactory.getGame("playstation", "God Of War", "Action");
		Game xbox=GameFactory.getGame("xbox", "Blur", "Racing");
		System.out.println(playstation);
		System.out.println(xbox);

	}

}
