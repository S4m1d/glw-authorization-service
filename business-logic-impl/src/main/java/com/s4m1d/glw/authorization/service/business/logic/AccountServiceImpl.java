package com.s4m1d.glw.authorization.service.business.logic;

import com.s4m1d.glw.authorization.service.business.logic.constant.AccountCreationResult;
import com.s4m1d.glw.authorization.service.business.logic.datamodel.AccountCreationInfo;
import com.s4m1d.glw.authorization.service.dba.AccountDbaService;
import com.s4m1d.glw.authorization.service.dba.exception.AccountAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import static com.s4m1d.glw.authorization.service.business.logic.constant.AccountCreationResult.*;

@Component
@RequiredArgsConstructor
@Log4j2
public class AccountServiceImpl implements AccountService {
    private final AccountDbaService accountDbaService;
    @Override
    public AccountCreationResult createAccount(AccountCreationInfo accountCreationInfo) {
        try {
            accountDbaService.createAccount(accountCreationInfo.getUserName(), accountCreationInfo.getPwd());
            return CREATION_SUCCESS;
        } catch (AccountAlreadyExistsException e) {
            log.error("Account already exists");
            return ALREADY_EXISTS;
        } catch (Exception e) {
            log.error("An unexpected error occurred while account creation");
            log.error(e);
            return OTHER_EXCEPTION;
        }
    }
}
