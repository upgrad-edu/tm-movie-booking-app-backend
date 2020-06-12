package com.upgrad.mtb.DAO.impl;

import com.upgrad.mtb.DAO.LanguageDAO;
import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.beans.Language;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class LanguageDAOImpl implements LanguageDAO {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.upgrad.hibernate.mtb.jpa");

    public Language acceptLanguageDetails(Language language) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(language);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
        return language;
    }

    public Language getLanguageDetails(int languageId) {
        return entityManagerFactory.createEntityManager().find(Language.class,languageId);
    }

    public boolean deleteLanguage(int languageId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Language language = entityManager.find(Language.class, languageId);
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
