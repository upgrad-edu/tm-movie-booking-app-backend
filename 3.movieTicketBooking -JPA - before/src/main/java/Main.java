import com.upgrad.mtb.beans.*;
import com.upgrad.mtb.daos.CustomerDAO;
import com.upgrad.mtb.daos.MovieDAO;
import com.upgrad.mtb.daos.TheatreDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
public class Main {

    public static void main(String[] args){


        ApplicationContext context = new ClassPathXmlApplicationContext("mtbBeans.xml");

       /* MovieDAO movieDAO = (MovieDAO) context.getBean("movieDAO");

       // Movie CRUD operation using Spring Data

        //insert
        Movie movie1 = new Movie("Dhoom","Movie about bike racing", new Date("2/9/2015"), 180, "coverPhotoURL" , "trailerURL");
        Movie movie2 = new Movie("Madari","Movie child death revenge", new Date("12/3/2017"), 180, "coverPhotoURL" , "trailerURL");
        Movie movie3 = new Movie("URI","Movie about URI attack", new Date("4/7/2018"), 150, "coverPhotoURL" , "trailerURL");
        Movie movie4 = new Movie("Shole","Movie about gabbar singh daku", new Date("22/6/1986"), 210, "coverPhotoURL" , "trailerURL");
        Movie movie5 = new Movie("Dhoni the untold story","Movie about Cricketer Mahendra singh dhoni", new Date("14/5/2016"), 210, "coverPhotoURL" , "trailerURL");
        Movie movie6 = new Movie("Bahubali","Movie about bike racing", new Date("10/7/2015"), 150, "coverPhotoURL" , "trailerURL");

        //Movie insertedMovie = movieDAO.save(movie1);

        System.out.println("Inserted Movie :-"+movieDAO.save(movie1));
        System.out.println("Inserted Movie :-"+movieDAO.save(movie2));
        System.out.println("Inserted Movie :-"+movieDAO.save(movie3));
        System.out.println("Inserted Movie :-"+movieDAO.save(movie4));
        System.out.println("Inserted Movie :-"+movieDAO.save(movie5));
        System.out.println("Inserted Movie :-"+movieDAO.save(movie6));


        // select
       *//* Movie serachdMovie = movieDAO.findById(1).get();
        System.out.println("Searched Movie :-"+serachdMovie);

        Movie movieByName1 = movieDAO.findByName("Dhoom");
        System.out.println("Searched Movie byName :-"+movieByName1);

        List<Movie> moviesByDuration = movieDAO.findByDuration(180);
        System.out.println("Searched Movies By Duration "+moviesByDuration);

        Movie movieByName2 = movieDAO.getMovieDetails("Dhoom");
        System.out.println("Searched Movie byName :-"+movieByName2);*//*

        List<Movie> moviesByDurationBetween = movieDAO.findByDurationBetween(130,240);
        System.out.println("\n\nSearched Movies By DurationBetween");
        moviesByDurationBetween.forEach(System.out::println);*/



    }
}
