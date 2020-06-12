package com.upgrad.mtb.DAO.impl;

import com.upgrad.mtb.DAO.BookingDAO;
import com.upgrad.mtb.beans.Booking;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class BookingDAOImpl implements BookingDAO {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.upgrad.hibernate.mtb.jpa");

    public Booking acceptBookingDetails(Booking booking) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(booking);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
        return booking;
    }

    public Booking getBookingDetails(int bookingId) {
        return entityManagerFactory.createEntityManager().find(Booking.class,bookingId);
    }

    public boolean deleteBooking(int bookingId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Booking booking = entityManager.find(Booking.class, bookingId);
        entityManager.getTransaction().begin();
        entityManager.remove(booking);
        entityManager.getTransaction().commit();
        return true;
    }

    public List<Booking> getAllBookingDetails() {
        Query query = entityManagerFactory.createEntityManager().createQuery("from Booking b",Booking.class);
        return (List<Booking>)query.getResultList();
    }
}
