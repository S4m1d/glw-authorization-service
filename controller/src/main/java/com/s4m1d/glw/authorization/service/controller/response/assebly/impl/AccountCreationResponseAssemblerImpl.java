package com.s4m1d.glw.authorization.service.controller.response.assebly.impl;

import com.s4m1d.glw.authorization.service.business.logic.constant.AccountCreationResult;
import com.s4m1d.glw.authorization.service.controller.response.assebly.api.AccountCreationResponseAssembler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import static com.s4m1d.glw.authorization.service.business.logic.error.ErrorCode.CRED_01;
import static com.s4m1d.glw.authorization.service.business.logic.error.ErrorCode.UDE;
import static com.s4m1d.glw.authorization.service.controller.model.attribute.AccountCreationResponseAttribute.*;

@Component
public class AccountCreationResponseAssemblerImpl implements AccountCreationResponseAssembler {
    @Override
    public void assemble(Model model, AccountCreationResult accountCreationResult) {
        switch (accountCreationResult) {
            case CREATION_SUCCESS:
                model.addAttribute(SUCCESS, true);
                break;
            case ALREADY_EXISTS:
                model.addAttribute(SUCCESS, false);
                model.addAttribute(ERROR_CODE, CRED_01.toString());
                model.addAttribute(MESSAGE, CRED_01.getMessage());
                break;
            case OTHER_EXCEPTION:
                model.addAttribute(SUCCESS, false);
                model.addAttribute(ERROR_CODE, UDE.toString());
                model.addAttribute(MESSAGE, UDE.getMessage());
                break;
        }
    }
}
