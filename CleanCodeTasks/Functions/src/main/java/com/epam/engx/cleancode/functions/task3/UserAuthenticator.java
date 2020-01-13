package com.epam.engx.cleancode.functions.task3;

import com.epam.engx.cleancode.functions.task3.thirdpartyjar.SessionManager;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.User;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.UserService;

public abstract class UserAuthenticator implements UserService {

	private SessionManager sessionManager;

	public User loginUserAndSessionManager(String userName, String password) {
		User user = getUserByName(userName);
		if (isPasswordCorrect(user, password)) {
			sessionManager.setCurrentUser(user);
			return user;
		}
		return null;
	}

}
