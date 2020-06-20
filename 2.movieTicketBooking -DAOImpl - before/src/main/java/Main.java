

import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.daos.MovieDAO;
import com.upgrad.mtb.daos.TheatreDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class Main {
    public static void main(String[] args){
        Movie movie = new Movie("Dhoom","Movie about bike racing", new Date("22/10/2020"), 180, "coverPhotoURL" , "trailerURL");
        Theatre theatre = new Theatre("Eclipse Theatres" , 100, 150);

        ApplicationContext context = new ClassPathXmlApplicationContext("mtbBeans.xml");
        MovieDAO movieDAO = (MovieDAO) context.getBean("movieDAO");
        TheatreDAO theatreDAO = (TheatreDAO) context.getBean("theatreDAO");

        //insert movie and theatre data
        Movie savedMovie = movieDAO.acceptMovieDetails(movie);
        Theatre savedTheatre = theatreDAO.acceptTheatreDetailsTransactional(theatre);

        /*//delete movie and theatre data
        movieDAO.deleteMovie(savedMovie.getId());
        theatreDAO.deleteTheatre(savedTheatre.getId());

        //get list of all movies and theatres
        movieDAO.getAllMovieDetails();
        theatreDAO.getAllTheatreDetails();*/
        System.out.println(movieDAO.getMovieDetailsByName("Dhoom"));
    }
}
