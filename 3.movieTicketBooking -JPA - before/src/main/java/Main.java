import com.upgrad.mtb.beans.*;
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

        MovieDAO movieDAO = (MovieDAO) context.getBean("movieDAO");

        //Movie CRUD operation using Spring Data

        /*  //insert
        Movie movie1 = new Movie("Dhoom","Movie about bike racing", new Date("22/10/2020"), 180, "coverPhotoURL" , "trailerURL");
        Movie insertedMovie = movieDAO.save(movie1);
        System.out.println("Inserted Movie :-"+movie1);

        // select
        Movie serachdMovie = movieDAO.findById(1).get();
        System.out.println("Searched Movie :-"+serachdMovie);

        Movie movieByName1 = movieDAO.findByName("Dhoom");
        System.out.println("Searched Movie byName :-"+movieByName1);

        List<Movie> moviesByDuration = movieDAO.findByDuration(180);
        System.out.println("Searched Movies By Duration "+moviesByDuration);

        Movie movieByName2 = movieDAO.getMovieDetails("Dhoom");
        System.out.println("Searched Movie byName :-"+movieByName2);*/


        //Pagination


        Movie movie1 = new Movie("Dhoom","Movie about bike racing", new Date("22/10/2020"), 180, "coverPhotoURL" , "trailerURL");
        Movie movie2 = new Movie("Shole","Movie about bike racing", new Date("22/10/2020"), 180, "coverPhotoURL" , "trailerURL");
        Movie movie3 = new Movie("Dum","Movie about bike racing", new Date("22/10/2020"), 180, "coverPhotoURL" , "trailerURL");
        Movie movie4 = new Movie("Hum","Movie about bike racing", new Date("22/10/2020"), 180, "coverPhotoURL" , "trailerURL");
        Movie movie5 = new Movie("RoJA","Movie about bike racing", new Date("22/10/2020"), 180, "coverPhotoURL" , "trailerURL");




        movieDAO.save(movie1);
        movieDAO.save(movie2);
        movieDAO.save(movie3);
        movieDAO.save(movie4);
        movieDAO.save(movie5);



        Pageable pageable1 = PageRequest.of(0,2);

        Page<Movie>pageResult1= movieDAO.findAll(pageable1);
        List<Movie>list1 = pageResult1.getContent();
        System.out.println(pageResult1);                        // page 1    movie 1 ,2
        list1.forEach(movie -> System.out.println(movie));

        Pageable pageable2= pageResult1.nextPageable();         // page 2    movie 3, 4
        Page<Movie>pageResult2= movieDAO.findAll(pageable2);
        System.out.println(pageResult2);
        pageResult2.forEach(movie -> System.out.println(movie));


        Pageable pageable3 = pageResult2.nextPageable();        //page 3 movie 5
        Page<Movie>pageResult3= movieDAO.findAll(pageable3);
        System.out.println(pageResult3);
        pageResult3.forEach(movie -> System.out.println(movie));

    }
}
