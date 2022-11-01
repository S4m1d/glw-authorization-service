package com.s4m1d.glw.authorization.service.business.logic;

import com.s4m1d.glw.authorization.service.business.logic.constant.AccountCreationResult;
import com.s4m1d.glw.authorization.service.business.logic.datamodel.AccountCreationInfo;
import com.s4m1d.glw.authorization.service.dba.AccountDbaService;
import com.s4m1d.glw.authorization.service.dba.exception.AccountAlreadyExistsException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class AccountServiceImplTest {
    private AccountServiceImpl accountCreationService;
    private AccountDbaService accountDbaService;

    @BeforeMethod
    public void setup() {
        accountDbaService = mock(AccountDbaService.class);
        accountCreationService = new AccountServiceImpl(accountDbaService);
    }

    @Test
    public void createAccount_creation_success() {
        AccountCreationResult accountCreationResult;
        try {
            accountCreationResult = accountCreationService.createAccount(new AccountCreationInfo());
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
            accountCreationResult = accountCreationService.createAccount(new AccountCreationInfo());
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
            accountCreationResult = accountCreationService.createAccount(new AccountCreationInfo());
        } catch (Exception e) {
            throw new AssertionError(e);
        }

        Assert.assertEquals(accountCreationResult, AccountCreationResult.OTHER_EXCEPTION);
    }
}
