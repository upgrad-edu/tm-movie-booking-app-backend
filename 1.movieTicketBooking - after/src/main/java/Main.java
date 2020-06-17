import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.beans.UserType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("mtbBeans.xml");
        Movie movie = (Movie) context.getBean("movie");
        System.out.println(movie.toString());

        Theatre theatre =(Theatre) context.getBean("theatre");
        System.out.println(theatre.toString());

        Customer customer = (Customer) context.getBean("customer");
        System.out.println(customer.toString());

        UserType userType = (UserType) context.getBean("userType");
        System.out.println(userType.toString());
    }
}
