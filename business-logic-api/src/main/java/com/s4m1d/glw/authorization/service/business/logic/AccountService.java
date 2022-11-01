package com.s4m1d.glw.authorization.service.business.logic;

import com.s4m1d.glw.authorization.service.business.logic.constant.AccountCreationResult;
import com.s4m1d.glw.authorization.service.business.logic.constant.AccountRemovalResult;
import com.s4m1d.glw.authorization.service.business.logic.datamodel.AccountCreationInfo;
import com.s4m1d.glw.authorization.service.business.logic.datamodel.AccountRemovalInfo;

public interface AccountService {
    AccountCreationResult createAccount(AccountCreationInfo accountCreationInfo);
    AccountRemovalResult removeAccount(AccountRemovalInfo accountRemovalInfo);
}
