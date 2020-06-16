package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.beans.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository(value="userTypeDAO")
public class UserTypeDAOImpl implements UserTypeDAO{
    @Autowired
    EntityManagerFactory entityManagerFactory;

    public UserType acceptUserTypeDetails(UserType userType) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(userType);
        entityManager.getTransaction().commit();
        entityManager.close();
        return userType;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserType acceptUserTypeDetailsTransactional(UserType userType) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(userType);
        entityManager.getTransaction().commit();
        entityManager.close();
        return userType;
    }

    public UserType getUserTypeDetails(int id) {
        return entityManagerFactory.createEntityManager().find(UserType.class,id);
    }

    public boolean deleteUserType(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UserType userType = entityManager.find(UserType.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(userType);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean deleteUserTypeTransactional(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UserType userType = entityManager.find(UserType.class, id);
        entityManager.remove(userType);
        entityManager.close();
        return true;
    }

    public List<UserType> getAllUserTypeDetails() {
        Query query = entityManagerFactory.createEntityManager().createQuery("from UserType u",UserType.class);
        return (List<UserType>)query.getResultList();
    }
}
