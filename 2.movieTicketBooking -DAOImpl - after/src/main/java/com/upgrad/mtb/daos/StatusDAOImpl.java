package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository(value="statusDAO")
public class StatusDAOImpl implements StatusDAO{

    @Autowired
    EntityManagerFactory entityManagerFactory;

    public Status acceptStatusDetails(Status status) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(status);
        entityManager.getTransaction().commit();
        entityManager.close();
        return status;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Status acceptStatusDetailsTransactional(Status status) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.persist(status);
        entityManager.close();
        return status;
    }

    public Status getStatusDetails(int id) {
        return entityManagerFactory.createEntityManager().find(Status.class,id);
    }

    public boolean deleteStatus(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Status status = entityManager.find(Status.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(status);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean deleteStatusTransactional(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Status status = entityManager.find(Status.class, id);
        entityManager.remove(status);
        entityManager.close();
        return true;
    }

    public List<Status> getAllStatusDetails() {
        Query query = entityManagerFactory.createEntityManager().createQuery("from Status s",Status.class);
        return (List<Status>)query.getResultList();
    }
}
