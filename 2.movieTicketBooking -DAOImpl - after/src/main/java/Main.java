import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.beans.UserType;
import com.upgrad.mtb.daos.CustomerDAO;
import com.upgrad.mtb.daos.MovieDAO;
import com.upgrad.mtb.daos.TheatreDAO;
import com.upgrad.mtb.daos.UserTypeDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args){
        Movie movie = new Movie("Dhoom","Movie about bike racing", new Date("22/10/2020"), 180, "coverPhotoURL" , "trailerURL");
        Theatre theatre = new Theatre("Eclipse Theatres" , 100, 150);
        ArrayList<String> phoneNumber = new ArrayList<String>(Arrays.asList("1234123412" , "6789012345"));
        Customer customer = new Customer("Ram", "Kumar", "ramKumar", "password", new Date("22/10/1990"), 1, phoneNumber);
        UserType userType = new UserType("Admin");

        ApplicationContext context = new ClassPathXmlApplicationContext("mtbBeans.xml");
        MovieDAO movieDAO = (MovieDAO) context.getBean("movieDAO");
        TheatreDAO theatreDAO = (TheatreDAO) context.getBean("theatreDAO");
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        UserTypeDAO userTypeDAO = (UserTypeDAO) context.getBean("userTypeDAO");

        //insert movie and theatre data
        Movie savedMovie = movieDAO.acceptMovieDetails(movie);
        Theatre savedTheatre = theatreDAO.acceptTheatreDetails(theatre);
        Customer savedCustomer = customerDAO.acceptCustomerDetails(customer);
        UserType savedUserType = userTypeDAO.acceptUserTypeDetails(userType);

        //delete movie and theatre data
        movieDAO.deleteMovie(savedMovie.getId());
        theatreDAO.deleteTheatre(savedTheatre.getId());
        customerDAO.deleteCustomer(savedCustomer.getId());
        userTypeDAO.deleteUserType(savedUserType.getId());

        //get list of all movies and theatres
        movieDAO.getAllMovieDetails();
        theatreDAO.getAllTheatreDetails();
        customerDAO.getAllCustomerDetails();
        userTypeDAO.getAllUserTypeDetails();
    }
}
