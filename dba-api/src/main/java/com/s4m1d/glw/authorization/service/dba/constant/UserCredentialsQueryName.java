package com.s4m1d.glw.authorization.service.dba.constant;

public class UserCredentialsQueryName {
    private static final String USER_CREDENTIALS_PREFIX = "UserCredentials.";
    public static final String DELETE_BY_LOGIN = USER_CREDENTIALS_PREFIX + "deleteByLogin";
    public static final String FIND_BY_LOGIN = USER_CREDENTIALS_PREFIX + "findById";
    public static final String USER_NAME_PARAMETER = "userName";
}
