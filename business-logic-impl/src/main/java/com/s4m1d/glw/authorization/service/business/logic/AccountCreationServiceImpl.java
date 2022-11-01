package com.s4m1d.glw.authorization.service.business.logic;

import com.s4m1d.glw.authorization.service.business.logic.datamodel.AccountCreationInfo;
import com.s4m1d.glw.authorization.service.dba.AccountDbaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountCreationServiceImpl implements AccountCreationService{
    private final AccountDbaService accountDbaService;
    @Override
    public void createAccount(AccountCreationInfo accountCreationInfo) {
        accountDbaService.createAccount(accountCreationInfo.getUserName(), accountCreationInfo.getPwd());
    }
}
