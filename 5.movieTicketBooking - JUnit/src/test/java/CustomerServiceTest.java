import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.beans.UserType;
import com.upgrad.mtb.exceptions.BookingDetailsNotFoundException;
import com.upgrad.mtb.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mtb.exceptions.TheatreDetailsNotFoundException;
import com.upgrad.mtb.exceptions.UserTypeDetailsNotFoundException;
import com.upgrad.mtb.services.CustomerService;
import com.upgrad.mtb.services.UserTypeService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:mtbBeans.xml"})
public class CustomerServiceTest {
    @Autowired
    CustomerService customerService;
    @Autowired
    UserTypeService userTypeService;

    public Customer customer = new Customer();

    @BeforeClass
    public static void setUpTestEnv(){

    }

    @Before
    public void setUpTestData() throws UserTypeDetailsNotFoundException {
        UserType userType = new UserType("Admin");
        userTypeService.acceptUserTypeDetails(userType);

        customer.setUsername("ramKumar");
        customer.setFirstName("Ram");
        customer.setLastName("Kumar");
        customer.setPassword("password");
        customer.setPhoneNumbers(new ArrayList<>());
        customer.setUserType(userTypeService.getUserTypeDetailsFromUserType("Admin"));
        customer.setDateOfBirth(new Date("22/12/1990"));
    }

    @Test
    public void testAcceptCustomerDetails(){
        Customer savedCustomer = customerService.acceptCustomerDetails(customer);
        Assert.assertEquals(customer,savedCustomer);
    }

    @Test
    public void testGetCustomerDetailsWithCorrectData() throws CustomerDetailsNotFoundException {
        Customer savedCustomer = customerService.getCustomerDetailsByUsername("ramKumar");
        int savedCustomerId = savedCustomer.getId();
        Customer testSavedCustomer = customerService.getCustomerDetails(savedCustomerId);
        Assert.assertEquals(savedCustomer , testSavedCustomer);
    }

    @Test(expected = CustomerDetailsNotFoundException.class)
    public void testGetCustomerDetailsWithInCorrectData() throws CustomerDetailsNotFoundException {
        Customer savedCustomer = customerService.getCustomerDetailsByUsername("ramKumar");
        int savedCustomerId = savedCustomer.getId();
        Customer testSavedCustomer = customerService.getCustomerDetails(savedCustomerId + 100);
        Assert.assertEquals(savedCustomer , testSavedCustomer);
    }

    @Test(expected = CustomerDetailsNotFoundException.class)
    public void testGetCustomerDetailsWithInCorrectUserName() throws CustomerDetailsNotFoundException {
        Customer savedCustomer = customerService.getCustomerDetailsByUsername("ramKumar");
        Customer testSavedCustomer = customerService.getCustomerDetailsByUsername("xyz");
        Assert.assertEquals(savedCustomer , testSavedCustomer);
    }

    @After
    public void tearDownTestMockData()   {

    }

    @AfterClass
    public static void tearDownTestEnv(){

    }

}
