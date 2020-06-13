package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.Status;
import com.upgrad.mtb.beans.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository(value="theatreDAO")
public class TheatreDAOImpl implements TheatreDAO{
    @Autowired
    EntityManagerFactory entityManagerFactory;

    public Theatre acceptTheatreDetails(Theatre theatre) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(theatre);
        entityManager.getTransaction().commit();
        entityManager.close();
        return theatre;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Theatre acceptTheatreDetailsTransactional(Theatre theatre) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.persist(theatre);
        entityManager.close();
        return theatre;
    }

    public Theatre getTheatreDetails(int id) {
        return entityManagerFactory.createEntityManager().find(Theatre.class,id);
    }

    public boolean deleteTheatre(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Theatre theatre = entityManager.find(Theatre.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(theatre);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean deleteTheatreTransactional(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Theatre theatre = entityManager.find(Theatre.class, id);
        entityManager.remove(theatre);
        entityManager.close();
        return true;
    }

    public List<Theatre> getAllTheatreDetails() {
        Query query = entityManagerFactory.createEntityManager().createQuery("from Theatre t",Theatre.class);
        return (List<Theatre>)query.getResultList();
    }
}
