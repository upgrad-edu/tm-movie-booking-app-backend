package com.upgrad.mtb.daos;

import com.upgrad.mtb.daos.LanguageDAO;
import com.upgrad.mtb.beans.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Repository(value="languageDAO")
public class LanguageDAOImpl implements LanguageDAO {
    @Autowired
    EntityManagerFactory entityManagerFactory;

    public Language acceptLanguageDetails(Language language) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(language);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
        return language;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Language acceptLanguageDetailsTransactional(Language language) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.persist(language);
        entityManagerFactory.close();
        return language;
    }

    public Language getLanguageDetails(int id) {
        return entityManagerFactory.createEntityManager().find(Language.class,id);
    }

    public boolean deleteLanguage(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Language language = entityManager.find(Language.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(language);
        entityManager.getTransaction().commit();
        return true;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean deleteLanguageTransactional(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Language language = entityManager.find(Language.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(language);
        entityManager.getTransaction().commit();
        return true;
    }

    public List<Language> getAllLanguageDetails() {
        Query query = entityManagerFactory.createEntityManager().createQuery("from Language l",Language.class);
        return (List<Language>)query.getResultList();
    }
}
