package com.s4m1d.glw.authorization.service.controller.response.assebly.impl;

import com.s4m1d.glw.authorization.service.business.logic.constant.AccountRemovalResult;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.s4m1d.glw.authorization.service.business.logic.error.ErrorCode.CRED_02;
import static com.s4m1d.glw.authorization.service.business.logic.error.ErrorCode.UDE;
import static com.s4m1d.glw.authorization.service.controller.model.attribute.AccountRemovalResponseAttribute.*;

public class AccountRemovalAssemblerImplTest {
    AccountRemovalResponseAssemblerImpl accountRemovalResponseAssembler;

    @BeforeMethod
    public void setup() {
        accountRemovalResponseAssembler = new AccountRemovalResponseAssemblerImpl();
    }

    @Test
    public void assemble_removal_success_test() {
        Model model = new ExtendedModelMap();

        accountRemovalResponseAssembler.assemble(model, AccountRemovalResult.REMOVAL_SUCCESS);

        Assert.assertNotNull(model.getAttribute(SUCCESS));
        Assert.assertTrue((Boolean) model.getAttribute(SUCCESS));
        Assert.assertNull(model.getAttribute(ERROR_CODE));
        Assert.assertNull(model.getAttribute(MESSAGE));
    }

    @Test
    public void assemble_no_such_account_test() {
        Model model = new ExtendedModelMap();

        accountRemovalResponseAssembler.assemble(model, AccountRemovalResult.ACCOUNT_NOT_FOUND);

        Assert.assertNotNull(model.getAttribute(SUCCESS));
        Assert.assertFalse((Boolean) model.getAttribute(SUCCESS));
        Assert.assertNotNull(model.getAttribute(ERROR_CODE));
        Assert.assertEquals(model.getAttribute(ERROR_CODE), CRED_02.toString());
        Assert.assertNotNull(model.getAttribute(MESSAGE));
        Assert.assertNotNull(model.getAttribute(MESSAGE), CRED_02.getMessage());
    }

    @Test
    public void assemble_other_exception_test() {
        Model model = new ExtendedModelMap();

        accountRemovalResponseAssembler.assemble(model, AccountRemovalResult.OTHER_EXCEPTION);

        Assert.assertNotNull(model.getAttribute(SUCCESS));
        Assert.assertFalse((Boolean) model.getAttribute(SUCCESS));
        Assert.assertNotNull(model.getAttribute(ERROR_CODE));
        Assert.assertEquals(model.getAttribute(ERROR_CODE), UDE.toString());
        Assert.assertNotNull(model.getAttribute(MESSAGE));
        Assert.assertNotNull(model.getAttribute(MESSAGE), UDE.getMessage());
    }

}
