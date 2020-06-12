package com.upgrad.mtb.DAO.impl;

import com.upgrad.mtb.DAO.MovieDAO;
import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.beans.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class MovieDAOImpl implements MovieDAO {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.upgrad.hibernate.mtb.jpa");

    public Movie acceptMovieDetails(Movie movie) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(movie);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
        return movie;
    }

    public Movie getMovieDetails(int movieId) {
        return entityManagerFactory.createEntityManager().find(Movie.class,movieId);
    }

    public boolean deleteMovie(int movieId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Movie movie = entityManager.find(Movie.class, movieId);
        entityManager.getTransaction().begin();
        entityManager.remove(movie);
        entityManager.getTransaction().commit();
        return true;
    }

    public List<Movie> getAllMovieDetails() {
        Query query = entityManagerFactory.createEntityManager().createQuery("from Movie m",Movie.class);
        return (List<Movie>)query.getResultList();
    }
}
