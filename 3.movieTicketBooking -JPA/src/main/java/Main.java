import com.upgrad.mtb.beans.*;
import com.upgrad.mtb.daos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args){
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



        ApplicationContext context = new ClassPathXmlApplicationContext("mtbBeans.xml");
        StatusDAO statusDAO = (StatusDAO) context.getBean("statusDAO");
        UserTypeDAO userTypeDAO = (UserTypeDAO) context.getBean("userTypeDAO");
        LanguageDAO languageDAO = (LanguageDAO) context.getBean("languageDAO");

        statusDAO.save(upcomingStatus);
        statusDAO.save(releasedStatus);
        statusDAO.save(blockedStatus);

        userTypeDAO.save(admin);
        userTypeDAO.save(customer);

        languageDAO.save(english);
        languageDAO.save(hindi);

        Customer customer1 = new Customer();
        customer1.setFirstName("Ram");
        customer1.setLastName("Kumar");
        customer1.setUsername("ramKumar");
        customer1.setPassword("pass");
        customer1.setPhoneNumbers(new ArrayList<>());
        customer1.setDateOfBirth(new Date("22/10/1996"));
        customer1.setUserType(userTypeDAO.findDistinctByUserType("Customer"));
        List<String> phoneNumber = new ArrayList<>();
        phoneNumber.add("1234567890");
        phoneNumber.add("1234567891");
        customer1.setPhoneNumbers(phoneNumber);

        Movie movie = new Movie("Dhoom 213", " Desc of movie" , new Date("23/12/2020"), 180 ,"coverURL" , "trailerURL" ,
                languageDAO.findDistinctByLanguage("English") , statusDAO.findDistinctByStatus("Upcoming") );

        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        MovieDAO movieDAO = (MovieDAO) context.getBean("movieDAO");
        customerDAO.save(customer1);
        movieDAO.save(movie);



    }
}
