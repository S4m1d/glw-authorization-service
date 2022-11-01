package com.s4m1d.glw.authorization.service.dba.datamodel;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_credentials")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String login;

    @Column(name = "pwd")
    private String password;
}
