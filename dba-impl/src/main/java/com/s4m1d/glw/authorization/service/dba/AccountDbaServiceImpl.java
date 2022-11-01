package com.s4m1d.glw.authorization.service.dba;

import com.s4m1d.glw.authorization.service.dba.datamodel.UserCredentials;
import com.s4m1d.glw.authorization.service.dba.exception.AccountAlreadyExistsException;
import com.s4m1d.glw.authorization.service.dba.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.sql.SQLIntegrityConstraintViolationException;

@Component
@RequiredArgsConstructor
@Log4j2
public class AccountDbaServiceImpl implements AccountDbaService{
    private final UserCredentialsRepository userCredentialsRepository;

    @Override
    public void createAccount(String userName, String pwd) throws AccountAlreadyExistsException {
        try {
            userCredentialsRepository.insert(
                    UserCredentials
                            .builder()
                            .login(userName)
                            .password(pwd)
                            .build()
            );
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            throw new AccountAlreadyExistsException();
        }
    }
}
