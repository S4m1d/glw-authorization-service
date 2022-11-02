package com.s4m1d.glw.authorization.service.controller.response.assebly.impl;

import com.s4m1d.glw.authorization.service.business.logic.constant.SignInResult;
import com.s4m1d.glw.authorization.service.controller.response.assebly.api.SignInResponseAssembler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import static com.s4m1d.glw.authorization.service.controller.model.attribute.SignInResponseAttribute.*;

@Component
public class SignInResponseAssemblerImpl implements SignInResponseAssembler {
    @Override
    public void assemble(Model model, SignInResult signInResult) {
        if (signInResult == SignInResult.SIGN_IN_SUCCESS) {
            model.addAttribute(SUCCESS, true);
            model.addAttribute(TOKEN, signInResult.getToken());
        } else {
            model.addAttribute(SUCCESS, false);
            model.addAttribute(ERROR_CODE, signInResult.getErrorCode().toString());
            model.addAttribute(MESSAGE, signInResult.getErrorCode().getMessage());
        }
    }
}
