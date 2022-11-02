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
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class AccountServiceImplTest {
    private AccountServiceImpl accountService;
    private AccountDbaService accountDbaService;
    private AuthenticationService authenticationService;

    private static final String EXISTING_USER_NAME = "some_1337_user_name";
    private static final String NOT_EXISTING_USER_NAME = "some_666_user_name";
    private static final String MATCHING_PASSWORD = "myCoolMatchingPassword1234";
    private static final String NOT_MATCHING_PASSWORD = "iAmScammerDontTrustMyPassword666";
    private static final String TOKEN = "asdf-wert-yuio-zxcv";

    @BeforeMethod
    public void setup() throws AccountNotFoundException, AuthenticationFailException {
        accountDbaService = mock(AccountDbaService.class);
        authenticationService = mock(AuthenticationService.class);
        accountService = new AccountServiceImpl(accountDbaService, authenticationService);

        when(authenticationService.getUserNameByToken(any())).thenReturn("JohnDoe");
        when(authenticationService.createSession(any())).thenReturn(TOKEN);
        when(accountDbaService.getPassword(EXISTING_USER_NAME)).thenReturn(MATCHING_PASSWORD);
        when(accountDbaService.getPassword(NOT_EXISTING_USER_NAME)).thenThrow(new AccountNotFoundException());
    }

    @Test
    public void createAccount_creation_success() {
        AccountCreationResult accountCreationResult;
        try {
            accountCreationResult = accountService.createAccount(new CredentialsInfo());
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
            accountCreationResult = accountService.createAccount(new CredentialsInfo());
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
            accountCreationResult = accountService.createAccount(new CredentialsInfo());
        } catch (Exception e) {
            throw new AssertionError(e);
        }

        Assert.assertEquals(accountCreationResult, AccountCreationResult.OTHER_EXCEPTION);
    }

    @Test
    public void removeAccount_success() {
        AccountRemovalResult accountRemovalResult;
        try {
            accountRemovalResult = accountService.removeAccount(new AccountRemovalInfo(TOKEN));
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
            accountRemovalResult = accountService.removeAccount(new AccountRemovalInfo(TOKEN));
        } catch (Exception e) {
            throw new AssertionError(e);
        }

        Assert.assertEquals(accountRemovalResult, AccountRemovalResult.ACCOUNT_NOT_FOUND);
    }

    @Test
    public void removeAccount_other_exception() throws AccountNotFoundException {
        doThrow(new RuntimeException("some message")).when(accountDbaService).removeAccount(any());

        AccountRemovalResult accountRemovalResult;
        try {
            accountRemovalResult = accountService.removeAccount(new AccountRemovalInfo(TOKEN));
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
            accountRemovalResult = accountService.removeAccount(new AccountRemovalInfo(TOKEN));
        } catch (Exception e) {
            throw new AssertionError(e);
        }

        Assert.assertEquals(accountRemovalResult, AccountRemovalResult.AUTHENTICATION_FAIL);
    }

    @Test
    public void signIn_success() {
        CredentialsInfo credentialsInfo = new CredentialsInfo();
        credentialsInfo.setUserName(EXISTING_USER_NAME);
        credentialsInfo.setPassword(MATCHING_PASSWORD);

        SignInResult signInResult;
        try {
            signInResult = accountService.signIn(credentialsInfo);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
        
        Assert.assertEquals(signInResult, SignInResult.SIGN_IN_SUCCESS);
        Assert.assertEquals(signInResult.getToken(), TOKEN);
    }

    @Test
    public void signIn_wrong_password() {
        CredentialsInfo credentialsInfo = new CredentialsInfo();
        credentialsInfo.setUserName(EXISTING_USER_NAME);
        credentialsInfo.setPassword(NOT_MATCHING_PASSWORD);

        SignInResult signInResult;
        try {
            signInResult = accountService.signIn(credentialsInfo);
        } catch (Exception e) {
            throw new AssertionError(e);
        }

        Assert.assertEquals(signInResult, SignInResult.WRONG_PASSWORD);
        Assert.assertNull(signInResult.getToken());
    }

    @Test
    public void signIn_account_not_found() {
        CredentialsInfo credentialsInfo = new CredentialsInfo();
        credentialsInfo.setUserName(NOT_EXISTING_USER_NAME);
        credentialsInfo.setPassword(MATCHING_PASSWORD);

        SignInResult signInResult;
        try {
            signInResult = accountService.signIn(credentialsInfo);
        } catch (Exception e) {
            throw new AssertionError(e);
        }

        Assert.assertEquals(signInResult, SignInResult.ACCOUNT_NOT_FOUND);
        Assert.assertNull(signInResult.getToken());
    }

    @Test
    public void signIn_account_authentication_fail() throws AuthenticationFailException {
        CredentialsInfo credentialsInfo = new CredentialsInfo();
        credentialsInfo.setUserName(EXISTING_USER_NAME);
        credentialsInfo.setPassword(MATCHING_PASSWORD);
        when(authenticationService.createSession(any())).thenThrow(new AuthenticationFailException());

        SignInResult signInResult;
        try {
            signInResult = accountService.signIn(credentialsInfo);
        } catch (Exception e) {
            throw new AssertionError(e);
        }

        Assert.assertEquals(signInResult, SignInResult.AUTHENTICATION_FAIL);
        Assert.assertNull(signInResult.getToken());
    }

    @Test
    public void signIn_account_other_exception() throws AccountNotFoundException {
        CredentialsInfo credentialsInfo = new CredentialsInfo();
        credentialsInfo.setUserName(EXISTING_USER_NAME);
        credentialsInfo.setPassword(MATCHING_PASSWORD);
        when(accountDbaService.getPassword(any())).thenThrow(new RuntimeException());

        SignInResult signInResult;
        try {
            signInResult = accountService.signIn(credentialsInfo);
        } catch (Exception e) {
            throw new AssertionError(e);
        }

        Assert.assertEquals(signInResult, SignInResult.OTHER_EXCEPTION);
        Assert.assertNull(signInResult.getToken());
    }
}
