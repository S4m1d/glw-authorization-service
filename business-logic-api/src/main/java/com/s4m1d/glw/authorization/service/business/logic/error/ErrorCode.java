package com.s4m1d.glw.authorization.service.business.logic.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NO_ERRORS("Success"),

    UDE("Undefined exception occurred"),

    CRED_01("Account with such name already exists"),
    CRED_02("No account with such user name exists"),
    CRED_03("Wrong password"),

    AUTHENTICATION_01("No active session with this token"),
    AUTHENTICATION_02("Technical authentication error");

    private final String message;
}
