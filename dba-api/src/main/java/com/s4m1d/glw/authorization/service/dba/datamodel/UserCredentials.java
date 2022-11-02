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
@NamedQuery(name = "UserCredentials.deleteByLogin", query = "delete from UserCredentials u where u.login=:userName")
@NamedQuery(name = "UserCredentials.findByLogin", query = "select u from UserCredentials u where u.login=:userName")
@NamedQuery(name = "UserCredentials.getPasswordByLogin", query = "select u.password from UserCredentials u where u.login=:userName")
public class UserCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String login;

    @Column(name = "pwd")
    private String password;
}
