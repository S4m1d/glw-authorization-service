package com.s4m1d.glw.authorization.service.controller;

import com.s4m1d.glw.authorization.service.business.logic.AccountCreationService;
import com.s4m1d.glw.authorization.service.business.logic.constant.AccountCreationResult;
import com.s4m1d.glw.authorization.service.business.logic.datamodel.AccountCreationInfo;
import com.s4m1d.glw.authorization.service.controller.datamodel.AccountCreationRequestBody;
import com.s4m1d.glw.authorization.service.controller.response.assebly.api.AccountCreationResponseAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountCreationService accountCreationService;
    private final AccountCreationResponseAssembler accountCreationResponseAssembler;

    @PostMapping("/create")
    public String create(@RequestBody AccountCreationRequestBody requestBody, Model model) {
        AccountCreationResult accountCreationResult = accountCreationService.createAccount(new AccountCreationInfo(requestBody.getUserName(), requestBody.getPwd()));
        accountCreationResponseAssembler.assemble(model, accountCreationResult);
        return "account_creation_response";
    }
}
