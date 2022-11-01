package com.s4m1d.glw.authorization.service.dba;

import com.s4m1d.glw.authorization.service.dba.exception.AccountAlreadyExistsException;
import com.s4m1d.glw.authorization.service.dba.exception.AccountNotFoundException;

public interface AccountDbaService {
    void createAccount(String userName, String pwd) throws AccountAlreadyExistsException;
    void removeAccount(String userName) throws AccountNotFoundException;
}
