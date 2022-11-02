package com.s4m1d.glw.authorization.service.business.logic.constant;

import com.s4m1d.glw.authorization.service.business.logic.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public enum SignInResult {
    SIGN_IN_SUCCESS(ErrorCode.NO_ERRORS),
    ACCOUNT_NOT_FOUND(ErrorCode.CRED_02),
    WRONG_PASSWORD(ErrorCode.CRED_03),
    AUTHENTICATION_FAIL(ErrorCode.AUTHENTICATION_02),
    OTHER_EXCEPTION(ErrorCode.UDE);

    @Getter
    @Setter
    private String token;
    @Getter
    private final ErrorCode errorCode;
}
