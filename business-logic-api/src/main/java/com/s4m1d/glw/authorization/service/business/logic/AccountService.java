package com.s4m1d.glw.authorization.service.business.logic;

import com.s4m1d.glw.authorization.service.business.logic.constant.AccountCreationResult;
import com.s4m1d.glw.authorization.service.business.logic.constant.AccountRemovalResult;
import com.s4m1d.glw.authorization.service.business.logic.constant.SignInResult;
import com.s4m1d.glw.authorization.service.business.logic.datamodel.CredentialsInfo;
import com.s4m1d.glw.authorization.service.business.logic.datamodel.AccountRemovalInfo;

public interface AccountService {
    AccountCreationResult createAccount(CredentialsInfo credentialsInfo);
    AccountRemovalResult removeAccount(AccountRemovalInfo accountRemovalInfo);
    SignInResult signIn(CredentialsInfo credentialsInfo);
}
