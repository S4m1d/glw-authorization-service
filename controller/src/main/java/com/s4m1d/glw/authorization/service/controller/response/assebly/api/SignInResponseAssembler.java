package com.s4m1d.glw.authorization.service.controller.response.assebly.api;

import com.s4m1d.glw.authorization.service.business.logic.constant.SignInResult;
import org.springframework.ui.Model;

public interface SignInResponseAssembler {
    public void assemble(Model model, SignInResult signInResult);
}
