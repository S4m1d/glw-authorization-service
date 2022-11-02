package com.s4m1d.glw.authorization.service.business.logic;

import com.s4m1d.glw.authorization.service.authentication.service.integration.AuthenticationService;
import com.s4m1d.glw.authorization.service.authentication.service.integration.exception.AuthenticationFailException;
import com.s4m1d.glw.authorization.service.business.logic.constant.AccountCreationResult;
import com.s4m1d.glw.authorization.service.business.logic.constant.AccountRemovalResult;
import com.s4m1d.glw.authorization.service.business.logic.constant.SignInResult;
import com.s4m1d.glw.authorization.service.business.logic.datamodel.CredentialsInfo;
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
    private final AuthenticationService authenticationService;

    @Override
    public AccountCreationResult createAccount(CredentialsInfo credentialsInfo) {
        try {
            accountDbaService.createAccount(credentialsInfo.getUserName(), credentialsInfo.getPassword());
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
            String userName = authenticationService.getUserNameByToken(accountRemovalInfo.getToken());
            if(userName == null) {
                return AccountRemovalResult.AUTHENTICATION_FAIL;
            }
            accountDbaService.removeAccount(userName);
            return AccountRemovalResult.REMOVAL_SUCCESS;
        } catch (AccountNotFoundException e) {
            log.error("No Account with this name");
            return AccountRemovalResult.ACCOUNT_NOT_FOUND;
        } catch (Exception e) {
            log.error("An unexpected exception occurred");
            log.error(e);
            return AccountRemovalResult.OTHER_EXCEPTION;
        }
    }

    @Override
    public SignInResult signIn(CredentialsInfo credentialsInfo) {
        try {
            String realPassword = accountDbaService.getPassword(credentialsInfo.getUserName());
            if (realPassword.equals(credentialsInfo.getPassword())) {
                String token = authenticationService.createSession(credentialsInfo.getUserName());
                SignInResult signInResult = SignInResult.SIGN_IN_SUCCESS;
                signInResult.setToken(token);
                return signInResult;
            } else {
                return SignInResult.WRONG_PASSWORD;
            }
        } catch (AccountNotFoundException e) {
            log.error(e);
            return SignInResult.ACCOUNT_NOT_FOUND;
        } catch (AuthenticationFailException e) {
            log.error(e);
            return SignInResult.AUTHENTICATION_FAIL;
        } catch (Exception e) {
            log.error((e));
            return SignInResult.OTHER_EXCEPTION;
        }
    }
}
