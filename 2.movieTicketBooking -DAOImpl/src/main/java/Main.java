import com.upgrad.mtb.daos.CustomerDAO;
import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.daos.MovieDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Customer customer1 = new Customer();
        customer1.setFirstName("Ram");
        customer1.setLastName("Kumar");
        customer1.setUsername("mohanKumar");
        customer1.setPassword("password");
        customer1.setPhoneNumber("1234567890");
        customer1.setDateOfBirth(new Date("22/10/1996"));

        Customer customer2 = new Customer();
        customer2.setFirstName("Shyam");
        customer2.setLastName("Kumar");
        customer2.setUsername("mohanKumar");
        customer2.setPassword("password");
        customer2.setPhoneNumber("1234567890");
        customer2.setDateOfBirth(new Date("22/10/1996"));

        Movie movie1 = new Movie();
        movie1.setCoverPhotoURL("URLString");
        movie1.setDuration(100);
        movie1.setLanguageId(1);
        movie1.setStatusId(1);
        movie1.setDescription("desc of movie");
        movie1.setName("Dhoom 213");
        movie1.setReleaseDate(new Date("23/10/2040"));
        movie1.setTrailerURL("trailerURL");

        ApplicationContext context = new ClassPathXmlApplicationContext("mtbBeans.xml");
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        customerDAO.acceptCustomerDetails(customer1);
        customerDAO.acceptCustomerDetailsTransactional(customer2);
    }
}
