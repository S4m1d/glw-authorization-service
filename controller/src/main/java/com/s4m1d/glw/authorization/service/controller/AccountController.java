package com.s4m1d.glw.authorization.service.controller;

import com.s4m1d.glw.authorization.service.business.logic.AccountService;
import com.s4m1d.glw.authorization.service.business.logic.constant.AccountCreationResult;
import com.s4m1d.glw.authorization.service.business.logic.constant.AccountRemovalResult;
import com.s4m1d.glw.authorization.service.business.logic.constant.SignInResult;
import com.s4m1d.glw.authorization.service.business.logic.datamodel.CredentialsInfo;
import com.s4m1d.glw.authorization.service.business.logic.datamodel.AccountRemovalInfo;
import com.s4m1d.glw.authorization.service.controller.datamodel.AccountCreationRequestBody;
import com.s4m1d.glw.authorization.service.controller.datamodel.AccountRemovalRequestBody;
import com.s4m1d.glw.authorization.service.controller.datamodel.SignInRequestBody;
import com.s4m1d.glw.authorization.service.controller.response.assebly.api.AccountCreationResponseAssembler;
import com.s4m1d.glw.authorization.service.controller.response.assebly.api.AccountRemovalResponseAssembler;
import com.s4m1d.glw.authorization.service.controller.response.assebly.api.SignInResponseAssembler;
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
    private final AccountService accountService;
    private final AccountCreationResponseAssembler accountCreationResponseAssembler;
    private final AccountRemovalResponseAssembler accountRemovalResponseAssembler;
    private final SignInResponseAssembler signInResponseAssembler;

    @PostMapping("/create")
    public String create(@RequestBody AccountCreationRequestBody requestBody, Model model) {
        AccountCreationResult accountCreationResult = accountService.createAccount(new CredentialsInfo(requestBody.getUserName(), requestBody.getPassword()));
        accountCreationResponseAssembler.assemble(model, accountCreationResult);
        return "account_creation_response";
    }

    @PostMapping("/remove")
    public String remove(@RequestBody AccountRemovalRequestBody accountRemovalRequestBody, Model model) {
        AccountRemovalResult accountRemovalResult = accountService.removeAccount(new AccountRemovalInfo(accountRemovalRequestBody.getToken()));
        accountRemovalResponseAssembler.assemble(model, accountRemovalResult);
        return "account_removal_response";
    }

    @PostMapping("/signIn")
    public String remove(@RequestBody SignInRequestBody signInRequestBody, Model model) {
        SignInResult signInResult = accountService.signIn(new CredentialsInfo(signInRequestBody.getUserName(), signInRequestBody.getPassword()));
        signInResponseAssembler.assemble(model, signInResult);
        return "sign_in_response";
    }
}
