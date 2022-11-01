package com.s4m1d.glw.authorization.service.business.logic.datamodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreationInfo {
    private String userName;
    private String pwd;
}
