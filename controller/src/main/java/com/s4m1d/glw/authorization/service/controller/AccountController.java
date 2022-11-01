package com.s4m1d.glw.authorization.service.controller;

import com.s4m1d.glw.authorization.service.business.logic.AccountCreationService;
import com.s4m1d.glw.authorization.service.business.logic.datamodel.AccountCreationInfo;
import com.s4m1d.glw.authorization.service.controller.datamodel.AccountCreationRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.s4m1d.glw.authorization.service.controller.model.attribute.AccountCreationResponseAttribute.SUCCESS;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountCreationService accountCreationService;

    @PostMapping("/create")
    public String create(@RequestBody AccountCreationRequestBody requestBody, Model model) {
        accountCreationService.createAccount(new AccountCreationInfo(requestBody.getUserName(), requestBody.getPwd()));
        model.addAttribute(SUCCESS, true);
        return "account_creation_response";
    }
}
