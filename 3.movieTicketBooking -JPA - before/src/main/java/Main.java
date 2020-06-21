import com.upgrad.mtb.beans.*;
import com.upgrad.mtb.daos.MovieDAO;
import com.upgrad.mtb.daos.TheatreDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
public class Main {

    public static void main(String[] args){


        ApplicationContext context = new ClassPathXmlApplicationContext("mtbBeans.xml");


        //Movie CRUD operation using Spring Data

        Movie movie1 = new Movie("Dhoom","Movie about bike racing", new Date("22/10/2020"), 180, "coverPhotoURL" , "trailerURL");

        MovieDAO movieDAO = (MovieDAO) context.getBean("movieDAO");

        //insert
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
        System.out.println("Searched Movie byName :-"+movieByName2);

    }
}
