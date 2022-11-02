package com.s4m1d.glw.authorization.service.dba.repository;

import com.s4m1d.glw.authorization.service.dba.datamodel.UserCredentials;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

import static com.s4m1d.glw.authorization.service.dba.constant.UserCredentialsQueryName.*;

@Repository
public class UserCredentialsRepositoryImpl implements UserCredentialsRepository {
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    @Override
    @Transactional
    public void insert(UserCredentials userCredentials) {
        entityManager.persist(userCredentials);
    }

    @Override
    @Transactional
    public void delete(String userName) {
        Query query = entityManager.createNamedQuery(DELETE_BY_LOGIN);
        query.setParameter(USER_NAME_PARAMETER, userName);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public boolean checkIfExists(String userName) {
        Query query = entityManager.createNamedQuery(FIND_BY_LOGIN);
        query.setParameter(USER_NAME_PARAMETER, userName);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    @Transactional
    public String getUserPassword(String userName) {
        Query query = entityManager.createNamedQuery(GET_PASSWORD_BY_LOGIN);
        query.setParameter(USER_NAME_PARAMETER, userName);
        return (String)query.getSingleResult();
    }
}
