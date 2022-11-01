package com.s4m1d.glw.authorization.service.controller.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    UDE("Undefined exception occurred"),

    AC_01("Account with such name already exists"),

    AR_01("No account with such user name exists"),

    AUTH_01("No active session with this token");

    private final String message;
}
