package com.upgrad.mtb.DAO.impl;

import com.upgrad.mtb.DAO.CustomerDAO;
import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.beans.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.upgrad.hibernate.mtb.jpa");

    public Customer acceptCustomerDetails(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
        return customer;
    }

    public Customer getCustomerDetails(int customerId) {
        return entityManagerFactory.createEntityManager().find(Customer.class,customerId);
    }

    public boolean deleteCustomer(int customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Customer customer = entityManager.find(Customer.class, customerId);
        entityManager.getTransaction().begin();
        entityManager.remove(customer);
        entityManager.getTransaction().commit();
        return true;
    }

    public List<Customer> getAllCustomerDetails() {
        Query query = entityManagerFactory.createEntityManager().createQuery("from Customer c",Customer.class);
        return (List<Customer>)query.getResultList();
    }
}
