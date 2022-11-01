package com.s4m1d.glw.authorization.service.business.logic;

import com.s4m1d.glw.authorization.service.business.logic.constant.AccountCreationResult;
import com.s4m1d.glw.authorization.service.business.logic.datamodel.AccountCreationInfo;

public interface AccountCreationService {
    AccountCreationResult createAccount(AccountCreationInfo accountCreationInfo);
}
