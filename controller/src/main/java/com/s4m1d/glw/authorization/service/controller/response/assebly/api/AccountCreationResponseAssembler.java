package com.s4m1d.glw.authorization.service.controller.response.assebly.api;

import com.s4m1d.glw.authorization.service.business.logic.constant.AccountCreationResult;
import org.springframework.ui.Model;

public interface AccountCreationResponseAssembler {
    void assemble(Model model, AccountCreationResult accountCreationResult);
}
