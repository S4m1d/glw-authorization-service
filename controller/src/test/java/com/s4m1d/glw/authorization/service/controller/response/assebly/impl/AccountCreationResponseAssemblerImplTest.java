package com.s4m1d.glw.authorization.service.controller.response.assebly.impl;

import com.s4m1d.glw.authorization.service.business.logic.constant.AccountCreationResult;
import com.s4m1d.glw.authorization.service.business.logic.error.ErrorCode;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.s4m1d.glw.authorization.service.controller.model.attribute.AccountCreationResponseAttribute.*;

public class AccountCreationResponseAssemblerImplTest {
    private AccountCreationResponseAssemblerImpl accountCreationResponseAssembler;
    private Model model;

    @BeforeMethod
    public void setup() {
        accountCreationResponseAssembler = new AccountCreationResponseAssemblerImpl();
        model = new ExtendedModelMap();
    }

    @Test
    public void test_assemble_creation_success() {
        accountCreationResponseAssembler.assemble(model, AccountCreationResult.CREATION_SUCCESS);

        Assert.assertNotNull(model.getAttribute(SUCCESS));
        Assert.assertTrue((Boolean) model.getAttribute(SUCCESS));
        Assert.assertNull(model.getAttribute(ERROR_CODE));
        Assert.assertNull(model.getAttribute(MESSAGE));
    }

    @Test
    public void test_assemble_already_exists() {
        accountCreationResponseAssembler.assemble(model, AccountCreationResult.ALREADY_EXISTS);

        Assert.assertNotNull(model.getAttribute(SUCCESS));
        Assert.assertFalse((Boolean) model.getAttribute(SUCCESS));
        Assert.assertNotNull(model.getAttribute(ERROR_CODE));
        Assert.assertEquals(model.getAttribute(ERROR_CODE), ErrorCode.CRED_01.toString());
        Assert.assertNotNull(model.getAttribute(MESSAGE));
        Assert.assertEquals(model.getAttribute(MESSAGE), ErrorCode.CRED_01.getMessage());
    }

    @Test
    public void test_assemble_other_exception() {
        accountCreationResponseAssembler.assemble(model, AccountCreationResult.OTHER_EXCEPTION);

        Assert.assertNotNull(model.getAttribute(SUCCESS));
        Assert.assertFalse((Boolean) model.getAttribute(SUCCESS));
        Assert.assertNotNull(model.getAttribute(ERROR_CODE));
        Assert.assertEquals(model.getAttribute(ERROR_CODE), ErrorCode.UDE.toString());
        Assert.assertNotNull(model.getAttribute(MESSAGE));
        Assert.assertEquals(model.getAttribute(MESSAGE), ErrorCode.UDE.getMessage());
    }
}
