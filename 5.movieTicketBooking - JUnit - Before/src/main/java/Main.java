import com.upgrad.mtb.beans.*;
import com.upgrad.mtb.exceptions.LanguageDetailsNotFoundException;
import com.upgrad.mtb.exceptions.MovieDetailsNotFoundException;
import com.upgrad.mtb.exceptions.StatusDetailsNotFoundException;
import com.upgrad.mtb.exceptions.UserTypeDetailsNotFoundException;
import com.upgrad.mtb.services.StatusService;
import com.upgrad.mtb.services.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws UserTypeDetailsNotFoundException, LanguageDetailsNotFoundException, StatusDetailsNotFoundException, MovieDetailsNotFoundException {
        //adding status data
        Status upcomingStatus = new Status("Upcoming");
        Status releasedStatus = new Status("Released");
        Status blockedStatus = new Status("Blocked");
        //adding user types
        UserType admin = new UserType("Admin");
        UserType customer = new UserType("Customer");
        //adding language data
        Language english = new Language("English");
        Language hindi = new Language("Hindi");
        //adding City data
        City mumbaiCity = new City("Mumbai");
        City patnaCity = new City("patna");
        City kolkataCity = new City("Kolkata");



        ApplicationContext context = new ClassPathXmlApplicationContext("mtbBeans.xml");
        StatusService statusService = (StatusService) context.getBean("statusService");
        UserTypeService userTypeService = (UserTypeService) context.getBean("userTypeService");
        LanguageService languageService = (LanguageService) context.getBean("languageService");
        CityService cityService = (CityService) context.getBean("cityService");
        TheatreService theatreService = (TheatreService) context.getBean("theatreService");

        statusService.acceptStatusDetails(upcomingStatus);
        statusService.acceptStatusDetails(releasedStatus);
        statusService.acceptStatusDetails(blockedStatus);

        userTypeService.acceptUserTypeDetails(admin);
        userTypeService.acceptUserTypeDetails(customer);

        languageService.acceptLanguageDetails(english);
        languageService.acceptLanguageDetails(hindi);

        cityService.acceptCityDetails(mumbaiCity);
        cityService.acceptCityDetails(patnaCity);
        cityService.acceptCityDetails(kolkataCity);

        Customer customer1 = new Customer();
        customer1.setFirstName("Ram");
        customer1.setLastName("Kumar");
        customer1.setUsername("ramKumar");
        customer1.setPassword("pass");
        customer1.setPhoneNumbers(new ArrayList<>());
        customer1.setDateOfBirth(new Date("22/10/1996"));
        customer1.setUserType(userTypeService.getUserTypeDetailsFromUserType("Customer"));
        List<String> phoneNumber = new ArrayList<>();
        phoneNumber.add("1234567890");
        phoneNumber.add("1234567891");
        customer1.setPhoneNumbers(phoneNumber);

        Movie movie = new Movie("Dhoom 200", " Desc of movie" , new Date("23/12/2020"), 180 ,"coverURL" , "trailerURL" ,
                languageService.getLanguageDetailsByLanguageName("English") , statusService.getStatusDetailsByStatusName("Upcoming") );

        CustomerService customerService = (CustomerService) context.getBean("customerService");
        MovieService movieService = (MovieService) context.getBean("movieService");
        customerService.acceptCustomerDetails(customer1);
        movieService.acceptMovieDetails(movie);

        Theatre theatre = new Theatre("Epic Theatres", 100 , 150, cityService.getCityDetailsByCityName("Mumbai"), new ArrayList<>(), movieService.getMovieDetailsByMovieName("Dhoom 200"));
        theatreService.acceptTheatreDetails(theatre);
    }
}
