package com.s4m1d.glw.authorization.service.dba.repository;

import com.s4m1d.glw.authorization.service.dba.datamodel.UserCredentials;

public interface UserCredentialsRepository {
    void insert(UserCredentials userCredentials);
    void delete(String userName);
    boolean checkIfExists(String userName);
}
