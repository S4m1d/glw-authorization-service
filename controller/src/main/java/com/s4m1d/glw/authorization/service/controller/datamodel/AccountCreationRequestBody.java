package com.s4m1d.glw.authorization.service.controller.datamodel;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreationRequestBody {
    private String userName;
    private String pwd;
}
