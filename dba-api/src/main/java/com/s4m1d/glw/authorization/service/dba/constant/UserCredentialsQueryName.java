package com.s4m1d.glw.authorization.service.dba.constant;

public class UserCredentialsQueryName {
    private static final String USER_CREDENTIALS_PREFIX = "UserCredentials.";
    public static final String DELETE_BY_LOGIN = USER_CREDENTIALS_PREFIX + "deleteByLogin";
    public static final String FIND_BY_LOGIN = USER_CREDENTIALS_PREFIX + "findByLogin";
    public static final String GET_PASSWORD_BY_LOGIN = USER_CREDENTIALS_PREFIX + "getPasswordByLogin";
    public static final String USER_NAME_PARAMETER = "userName";
}
