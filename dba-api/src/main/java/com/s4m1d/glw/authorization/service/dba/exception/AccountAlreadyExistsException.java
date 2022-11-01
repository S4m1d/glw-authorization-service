package com.s4m1d.glw.authorization.service.dba.exception;

public class AccountAlreadyExistsException extends Exception{
    private final static String MESSAGE = "Account with this login already exists";
    public AccountAlreadyExistsException() {
        super(MESSAGE);
    }
}
