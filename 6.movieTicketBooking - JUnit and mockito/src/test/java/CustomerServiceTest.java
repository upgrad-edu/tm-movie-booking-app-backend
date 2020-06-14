import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.beans.UserType;
import com.upgrad.mtb.daos.CustomerDAO;
import com.upgrad.mtb.services.CustomerService;
import com.upgrad.mtb.services.CustomerServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:mtbBeans.xml"})
public class CustomerServiceTest {

    @InjectMocks
    CustomerServiceImpl customerService ;

    @Mock
    CustomerDAO customerDAO;

    public static Customer customer ;

    @BeforeClass
    public static void setUpTestEnv() throws ParseException {
        customer = new Customer();
        customer.setUsername("ramKumar");
        customer.setPassword("password");
        customer.setFirstName("Ram");
        customer.setLastName("Kumar");
        customer.setId(1);
        customer.setPhoneNumber("9009345678");
        customer.setDateOfBirth(new Date("01/06/1992"));
    }

    @Before
    public void setUpTestMock(){
        when(customerDAO.save(customer)).thenReturn(
                new Customer(1,"Ram","Kumar","ramKumar","password",
                        new Date("01/06/1992"), "9009345678", new ArrayList<>(),
                        new UserType(1, "Admin")));
    }

    @Test
    public void createNewCustomer() throws ParseException {
        Assert.assertEquals(customer.getUsername(),customerService.acceptCustomerDetails(customer).getUsername());
    }

   /* @Test
    void getCustomer() throws CustomerNotFoundException {
        when(customerDAO.findById(1)).thenReturn(java.util.Optional.ofNullable(customer));
        assertEquals(customer.getUsername(),customerService.getCustomer(1));
    }

    @Test
    void deleteCustomer() throws CustomerNotFoundException {
    }

    @Test
    void getAllCustomer() {
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        customerArrayList.add(customer);
        when(customerDAO.findAll()).thenReturn(customerArrayList);
        assertEquals(customerArrayList,customerService.getAllCustomer());
    }

    @AfterClass
    void tearDown() {
        customer = null;
    }*/
}