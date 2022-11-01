package com.s4m1d.glw.authorization.service.dba.repository;

import com.s4m1d.glw.authorization.service.dba.datamodel.UserCredentials;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Repository
public class UserCredentialsRepositoryImpl implements UserCredentialsRepository {
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    @Override
    @Transactional
    public void insert(UserCredentials userCredentials) {
        entityManager.persist(userCredentials);
    }
}
