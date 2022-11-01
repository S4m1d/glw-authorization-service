package com.s4m1d.glw.authorization.service.dba;

import com.s4m1d.glw.authorization.service.dba.exception.AccountAlreadyExistsException;
import com.s4m1d.glw.authorization.service.dba.repository.UserCredentialsRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class AccountDbaServiceImplTest {
    private AccountDbaServiceImpl accountDbaService;
    private UserCredentialsRepository userCredentialsRepository;
    @BeforeMethod
    public void setup() {
        userCredentialsRepository = mock(UserCredentialsRepository.class);
        accountDbaService = new AccountDbaServiceImpl(userCredentialsRepository);
    }

    @Test
    public void test_success() {
        try {
            accountDbaService.createAccount("usrnm", "pwd");
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    @Test
    public void test_account_already_exists() {
        doThrow(new DataIntegrityViolationException("some msg")).when(userCredentialsRepository).insert(any());

        boolean isExceptionThrown = false;
        try {
            accountDbaService.createAccount("usrnm", "pwd");
        } catch (AccountAlreadyExistsException e) {
            isExceptionThrown = true;
        } catch (Exception e) {
            throw new AssertionError(e);
        }

        Assert.assertTrue(isExceptionThrown, "no exception thrown");
    }

    @Test
    public void test_other_exception_occurred() {
        doThrow(new RuntimeException("some msg")).when(userCredentialsRepository).insert(any());

        boolean isExceptionThrown = false;
        try {
            accountDbaService.createAccount("usrnm", "pwd");
        } catch (AccountAlreadyExistsException e) {
            throw new AssertionError(e);
        } catch (Exception e) {
            isExceptionThrown = true;
        }

        Assert.assertTrue(isExceptionThrown, "no exception thrown");
    }
}
