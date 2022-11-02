package com.s4m1d.glw.authorization.service.dba;

import com.s4m1d.glw.authorization.service.dba.exception.AccountAlreadyExistsException;
import com.s4m1d.glw.authorization.service.dba.exception.AccountNotFoundException;
import com.s4m1d.glw.authorization.service.dba.repository.UserCredentialsRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.NoResultException;

import static org.mockito.Mockito.*;

public class AccountDbaServiceImplTest {
    private AccountDbaServiceImpl accountDbaService;
    private UserCredentialsRepository userCredentialsRepository;
    private final String USER_NAME = "some_1337_user_name";
    private final String PASSWORD = "666$!_some_very_cool_password_!$777";

    @BeforeMethod
    public void setup() {
        userCredentialsRepository = mock(UserCredentialsRepository.class);
        accountDbaService = new AccountDbaServiceImpl(userCredentialsRepository);

        when(userCredentialsRepository.checkIfExists(any())).thenReturn(true);
    }

    @Test
    public void createAccount_success() {
        try {
            accountDbaService.createAccount(USER_NAME, "pwd");
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    @Test
    public void createAccount_account_already_exists() {
        doThrow(new DataIntegrityViolationException("some msg")).when(userCredentialsRepository).insert(any());

        boolean isExceptionThrown = false;
        try {
            accountDbaService.createAccount(USER_NAME, "pwd");
        } catch (AccountAlreadyExistsException e) {
            isExceptionThrown = true;
        } catch (Exception e) {
            throw new AssertionError(e);
        }

        Assert.assertTrue(isExceptionThrown, "no exception thrown");
    }

    @Test
    public void createAccount_other_exception_occurred() {
        doThrow(new RuntimeException("some msg")).when(userCredentialsRepository).insert(any());

        boolean isExceptionThrown = false;
        try {
            accountDbaService.createAccount(USER_NAME, "pwd");
        } catch (AccountAlreadyExistsException e) {
            throw new AssertionError(e);
        } catch (Exception e) {
            isExceptionThrown = true;
        }

        Assert.assertTrue(isExceptionThrown, "no exception thrown");
    }

    @Test
    public void removeAccount_success() {
        try {
            accountDbaService.removeAccount(USER_NAME);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    @Test
    public void removeAccount_no_such_account() {
        when(userCredentialsRepository.checkIfExists(any())).thenReturn(false);

        boolean isExceptionThrown = false;
        try {
            accountDbaService.removeAccount(USER_NAME);
        } catch (AccountNotFoundException e) {
            isExceptionThrown = true;
        } catch (Exception e) {
            throw new AssertionError(e);
        }

        Assert.assertTrue(isExceptionThrown);
    }

    @Test
    public void removeAccount_other_exception() {
        doThrow(new RuntimeException("some message")).when(userCredentialsRepository).delete(any());

        boolean isExceptionThrown = false;
        try {
            accountDbaService.removeAccount(USER_NAME);
        } catch (AccountNotFoundException e) {
            throw new AssertionError(e);
        } catch (Exception e) {
            isExceptionThrown = true;
        }

        Assert.assertTrue(isExceptionThrown);
    }

    @Test
    public void getPassword_success() {
        when(userCredentialsRepository.getUserPassword(any())).thenReturn(PASSWORD);

        String pwd;
        try{
            pwd = accountDbaService.getPassword(USER_NAME);
        } catch (Exception e) {
            throw new AssertionError(e);
        }

        Assert.assertEquals(pwd, PASSWORD);
    }

    @Test
    public void getPassword_no_such_account() {
        when(userCredentialsRepository.getUserPassword(any())).thenThrow(new NoResultException());

        boolean isExceptionThrown = false;
        try {
            accountDbaService.getPassword(USER_NAME);
        } catch (AccountNotFoundException e) {
            isExceptionThrown = true;
        } catch (Exception e) {
            throw new AssertionError(e);
        }

        Assert.assertTrue(isExceptionThrown);
    }
}
