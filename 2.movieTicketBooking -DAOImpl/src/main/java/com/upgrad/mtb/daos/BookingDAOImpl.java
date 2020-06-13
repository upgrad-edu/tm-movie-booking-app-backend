package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository(value="bookingDAO")
public class BookingDAOImpl implements BookingDAO {
    @Autowired
    EntityManagerFactory entityManagerFactory;

    public Booking acceptBookingDetails(Booking booking) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(booking);
        entityManager.getTransaction().commit();
        entityManager.close();
        return booking;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Booking acceptBookingDetailsTransactional(Booking booking) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.persist(booking);
        entityManager.close();
        return booking;
    }

    public Booking getBookingDetails(int id) {
        return entityManagerFactory.createEntityManager().find(Booking.class,id);
    }

    public boolean deleteBooking(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Booking booking = entityManager.find(Booking.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(booking);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean deleteBookingTransactional(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Booking booking = entityManager.find(Booking.class, id);
        entityManager.remove(booking);
        entityManager.close();
        return true;
    }

    public List<Booking> getAllBookingDetails() {
        Query query = entityManagerFactory.createEntityManager().createQuery("from Booking b",Booking.class);
        return (List<Booking>)query.getResultList();
    }
}
