package com.s4m1d.glw.authorization.service.controller.response.assebly.api;

import com.s4m1d.glw.authorization.service.business.logic.constant.AccountRemovalResult;
import org.springframework.ui.Model;

public interface AccountRemovalResponseAssembler {
    void assemble(Model model, AccountRemovalResult accountRemovalResult);
}
