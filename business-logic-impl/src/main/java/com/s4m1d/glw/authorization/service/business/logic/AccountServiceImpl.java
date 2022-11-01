package com.s4m1d.glw.authorization.service.business.logic;

import com.s4m1d.glw.authorization.service.business.logic.constant.AccountCreationResult;
import com.s4m1d.glw.authorization.service.business.logic.constant.AccountRemovalResult;
import com.s4m1d.glw.authorization.service.business.logic.datamodel.AccountCreationInfo;
import com.s4m1d.glw.authorization.service.business.logic.datamodel.AccountRemovalInfo;
import com.s4m1d.glw.authorization.service.dba.AccountDbaService;
import com.s4m1d.glw.authorization.service.dba.exception.AccountAlreadyExistsException;
import com.s4m1d.glw.authorization.service.dba.exception.AccountNotFoundException;
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

    @Override
    public AccountRemovalResult removeAccount(AccountRemovalInfo accountRemovalInfo) {
        //todo remove all stubs, rework later
        try {
            accountDbaService.removeAccount("JohnDoe");
            return AccountRemovalResult.REMOVAL_SUCCESS;
        } catch (AccountNotFoundException e) {
            log.error("No Account with name JohnDoe");
            return AccountRemovalResult.NO_SUCH_ACCOUNT;
        } catch (Exception e) {
            log.error("An unexpected exception occurred");
            log.error(e);
            return AccountRemovalResult.OTHER_EXCEPTION;
        }
    }
}
