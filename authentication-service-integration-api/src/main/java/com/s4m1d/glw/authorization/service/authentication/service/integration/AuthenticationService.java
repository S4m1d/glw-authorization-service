package com.s4m1d.glw.authorization.service.authentication.service.integration;

import com.s4m1d.glw.authorization.service.authentication.service.integration.exception.AuthenticationFailException;

public interface AuthenticationService {
    String getUserNameByToken(String token);
    String createSession(String userName) throws AuthenticationFailException;
}
