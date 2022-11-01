package com.s4m1d.glw.authorization.service.dba;

import com.s4m1d.glw.authorization.service.dba.datamodel.UserCredentials;
import com.s4m1d.glw.authorization.service.dba.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountDbaServiceImpl implements AccountDbaService{
    private final UserCredentialsRepository userCredentialsRepository;

    @Override
    public void createAccount(String userName, String pwd) {
        userCredentialsRepository.insert(
                UserCredentials
                        .builder()
                        .login(userName)
                        .password(pwd)
                        .build()
        );
    }
}
