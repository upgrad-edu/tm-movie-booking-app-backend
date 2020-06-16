package com.upgrad.mtb.daos;

import com.upgrad.mtb.daos.MovieDAO;
import com.upgrad.mtb.beans.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Repository(value="movieDAO")
public class MovieDAOImpl implements MovieDAO {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    public Movie acceptMovieDetails(Movie movie) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(movie);
        entityManager.getTransaction().commit();
        entityManager.close();
        return movie;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Movie acceptMovieDetailsTransactional(Movie movie) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.persist(movie);
        entityManager.close();
        return movie;
    }

    public Movie getMovieDetails(int id) {
        return entityManagerFactory.createEntityManager().find(Movie.class,id);
    }

    public boolean deleteMovie(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Movie movie = entityManager.find(Movie.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(movie);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    public boolean deleteMovieTransactional(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Movie movie = entityManager.find(Movie.class, id);
        entityManager.remove(movie);
        entityManager.close();
        return true;
    }

    public List<Movie> getAllMovieDetails() {
        Query query = entityManagerFactory.createEntityManager().createQuery("from Movie m",Movie.class);
        return (List<Movie>)query.getResultList();
    }
}
