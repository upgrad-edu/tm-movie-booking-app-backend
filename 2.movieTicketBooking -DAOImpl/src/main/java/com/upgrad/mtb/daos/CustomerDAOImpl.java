package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import java.util.List;

@Repository(value="customerDAO")
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Customer acceptCustomerDetails(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        entityManager.close();
        return customer;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Customer acceptCustomerDetailsTransactional(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.persist(customer);
        entityManager.close();
        return customer;
    }

    public Customer getCustomerDetails(int id) {
        return entityManagerFactory.createEntityManager().find(Customer.class,id);
    }

    public boolean deleteCustomer(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(customer);
        entityManager.getTransaction().commit();
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean deleteCustomerTransactional(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.remove(customer);
        entityManager.close();
        return true;
    }

    public List<Customer> getAllCustomerDetails() {
        Query query = entityManagerFactory.createEntityManager().createQuery("from Customer c",Customer.class);
        return (List<Customer>)query.getResultList();
    }
}
