package com.s4m1d.glw.authorization.service.business.logic;

import com.s4m1d.glw.authorization.service.authentication.service.integration.AuthenticationService;
import com.s4m1d.glw.authorization.service.business.logic.constant.AccountCreationResult;
import com.s4m1d.glw.authorization.service.business.logic.constant.AccountRemovalResult;
import com.s4m1d.glw.authorization.service.business.logic.datamodel.AccountCreationInfo;
import com.s4m1d.glw.authorization.service.business.logic.datamodel.AccountRemovalInfo;
import com.s4m1d.glw.authorization.service.dba.AccountDbaService;
import com.s4m1d.glw.authorization.service.dba.exception.AccountAlreadyExistsException;
import com.s4m1d.glw.authorization.service.dba.exception.AccountNotFoundException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class AccountServiceImplTest {
    private AccountServiceImpl accountService;
    private AccountDbaService accountDbaService;
    private AuthenticationService authenticationService;

    @BeforeMethod
    public void setup() {
        accountDbaService = mock(AccountDbaService.class);
        authenticationService = mock(AuthenticationService.class);
        accountService = new AccountServiceImpl(accountDbaService, authenticationService);

        when(authenticationService.getUserNameByToken(any())).thenReturn("JohnDoe");
    }

    @Test
    public void createAccount_creation_success() {
        AccountCreationResult accountCreationResult;
        try {
            accountCreationResult = accountService.createAccount(new AccountCreationInfo());
        } catch (Exception e) {
            throw new AssertionError(e);
        }

        Assert.assertEquals(accountCreationResult, AccountCreationResult.CREATION_SUCCESS);
    }

    @Test
    public void createAccount_already_exists() throws AccountAlreadyExistsException {
        doThrow(new AccountAlreadyExistsException()).when(accountDbaService).createAccount(any(), any());

        AccountCreationResult accountCreationResult;
        try {
            accountCreationResult = accountService.createAccount(new AccountCreationInfo());
        } catch (Exception e) {
            throw new AssertionError(e);
        }

        Assert.assertEquals(accountCreationResult, AccountCreationResult.ALREADY_EXISTS);
    }

    @Test
    public void createAccount_other_exception() throws AccountAlreadyExistsException {
        doThrow(new RuntimeException("some msg")).when(accountDbaService).createAccount(any(), any());

        AccountCreationResult accountCreationResult;
        try {
            accountCreationResult = accountService.createAccount(new AccountCreationInfo());
        } catch (Exception e) {
            throw new AssertionError(e);
        }

        Assert.assertEquals(accountCreationResult, AccountCreationResult.OTHER_EXCEPTION);
    }

    @Test
    public void removeAccount_success() {
        AccountRemovalResult accountRemovalResult;
        try {
            accountRemovalResult = accountService.removeAccount(new AccountRemovalInfo("some token"));
        } catch (Exception e) {
            throw new AssertionError(e);
        }

        Assert.assertEquals(accountRemovalResult, AccountRemovalResult.REMOVAL_SUCCESS);
    }

    @Test
    public void removeAccount_no_such_account() throws AccountNotFoundException {
        doThrow(new AccountNotFoundException()).when(accountDbaService).removeAccount(any());

        AccountRemovalResult accountRemovalResult;
        try {
            accountRemovalResult = accountService.removeAccount(new AccountRemovalInfo("some token"));
        } catch (Exception e) {
            throw new AssertionError(e);
        }

        Assert.assertEquals(accountRemovalResult, AccountRemovalResult.NO_SUCH_ACCOUNT);
    }

    @Test
    public void removeAccount_other_exception() throws AccountNotFoundException {
        doThrow(new RuntimeException("some message")).when(accountDbaService).removeAccount(any());

        AccountRemovalResult accountRemovalResult;
        try {
            accountRemovalResult = accountService.removeAccount(new AccountRemovalInfo("some token"));
        } catch (Exception e) {
            throw new AssertionError(e);
        }

        Assert.assertEquals(accountRemovalResult, AccountRemovalResult.OTHER_EXCEPTION);
    }

    @Test
    public void removeAccount_authentication_fail() {
        when(authenticationService.getUserNameByToken(any())).thenReturn(null);

        AccountRemovalResult accountRemovalResult;
        try {
            accountRemovalResult = accountService.removeAccount(new AccountRemovalInfo("some token"));
        } catch (Exception e) {
            throw new AssertionError(e);
        }

        Assert.assertEquals(accountRemovalResult, AccountRemovalResult.AUTHENTICATION_FAIL);
    }
}
