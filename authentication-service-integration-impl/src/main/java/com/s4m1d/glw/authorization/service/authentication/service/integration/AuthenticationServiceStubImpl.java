package com.s4m1d.glw.authorization.service.authentication.service.integration;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceStubImpl implements AuthenticationService {
    private static String JOHN_DOE_TOKEN = "asdf-wert-yuio-zxcv";
    @Override
    public String getUserNameByToken(String token) {
        if (token.equals(JOHN_DOE_TOKEN)) {
            return "JohnDoe";
        } else {
            return null;
        }
    }
}
