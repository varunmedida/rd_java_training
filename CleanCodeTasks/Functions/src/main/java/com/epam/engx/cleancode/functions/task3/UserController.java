package com.epam.engx.cleancode.functions.task3;

import com.epam.engx.cleancode.functions.task3.thirdpartyjar.Controller;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.User;

public abstract class UserController implements Controller {

    private UserAuthenticator userAuthenticator;

    public void authenticateUser(String userName, String password) {
        User user = userAuthenticator.loginUserAndSessionManager(userName, password);
        if (user == null)
            generateFailLoginResponse();
        else
            generateSuccessLoginResponse(userName);
    }


}
