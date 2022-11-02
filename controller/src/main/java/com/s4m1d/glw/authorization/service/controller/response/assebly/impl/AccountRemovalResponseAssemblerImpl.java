package com.s4m1d.glw.authorization.service.controller.response.assebly.impl;

import com.s4m1d.glw.authorization.service.business.logic.constant.AccountRemovalResult;
import com.s4m1d.glw.authorization.service.controller.response.assebly.api.AccountRemovalResponseAssembler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import static com.s4m1d.glw.authorization.service.business.logic.error.ErrorCode.*;
import static com.s4m1d.glw.authorization.service.controller.model.attribute.AccountRemovalResponseAttribute.*;

@Component
public class AccountRemovalResponseAssemblerImpl implements AccountRemovalResponseAssembler {
    @Override
    public void assemble(Model model, AccountRemovalResult accountRemovalResult) {
        switch (accountRemovalResult) {
            case REMOVAL_SUCCESS:
                model.addAttribute(SUCCESS, true);
                break;
            case ACCOUNT_NOT_FOUND:
                model.addAttribute(SUCCESS, false);
                model.addAttribute(ERROR_CODE, CRED_02.toString());
                model.addAttribute(MESSAGE, CRED_02.getMessage());
                break;
            case AUTHENTICATION_FAIL:
                model.addAttribute(SUCCESS, false);
                model.addAttribute(ERROR_CODE, AUTHENTICATION_01.toString());
                model.addAttribute(MESSAGE, AUTHENTICATION_01.getMessage());
                break;
            case OTHER_EXCEPTION:
                model.addAttribute(SUCCESS, false);
                model.addAttribute(ERROR_CODE, UDE.toString());
                model.addAttribute(MESSAGE, UDE.getMessage());
        }
    }
}
