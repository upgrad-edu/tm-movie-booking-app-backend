import com.upgrad.mtb.beans.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Date;

public class Main {
    public static void main(String[] args){
        Customer customer1 = new Customer();
        customer1.setFirstName("Ram");
        customer1.setLastName("Kumar");
        customer1.setUsername("mohanKumar");
        customer1.setPassword("password");
        customer1.setPhoneNumber("1234567890");
        customer1.setDateOfBirth(new Date("22/10/1996"));

       /* ApplicationContext context = new ClassPathXmlApplicationContext("mtbBeans.xml");
        CustomerService customerService = (CustomerService) context.getBean("customerService");
        customerService.acceptCustomerDetails(customer1);*/
    }
}
