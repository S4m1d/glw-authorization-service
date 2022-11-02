package com.s4m1d.glw.authorization.service.authentication.service.integration;

import com.s4m1d.glw.authorization.service.authentication.service.integration.exception.AuthenticationFailException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceStubImpl implements AuthenticationService {
    private static String JOHN_DOE_TOKEN = "asdf-wert-yuio-zxcv";
    private static String JOHN_DOE_USER_NAME = "JohnDoe";

    @Override
    public String getUserNameByToken(String token) {
        if (token.equals(JOHN_DOE_TOKEN)) {
            return JOHN_DOE_USER_NAME;
        } else {
            return null;
        }
    }

    @Override
    public String createSession(String userName) throws AuthenticationFailException {
        return JOHN_DOE_TOKEN;
    }
}
