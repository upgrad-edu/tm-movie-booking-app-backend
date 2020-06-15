import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.beans.UserType;
import com.upgrad.mtb.daos.CustomerDAO;
import com.upgrad.mtb.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mtb.services.CustomerServiceImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
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
        customer.setPhoneNumbers(new ArrayList<>(Arrays.asList("9009345678")));
        customer.setDateOfBirth(new Date("01/06/1992"));
    }

    @Before
    public void setUpTestMock(){
        when(customerDAO.save(customer)).thenReturn(customer);
        when(customerDAO.findById(1)).thenReturn(java.util.Optional.ofNullable(customer));

        Customer customer1 = new Customer("Mohan", "Kumar," ,"mohanKumar" ,"password" , new Date("22/10/1996") , new ArrayList<String>() , new ArrayList<>() , new UserType("Admin") );

        when(customerDAO.findAll()).thenReturn(new ArrayList<>(Arrays.asList(customer,customer1)));
    }

    @Test
    public void testAcceptCustomerDetails() throws ParseException {
        Assert.assertEquals(customer,customerService.acceptCustomerDetails(customer));
    }

    @Test
    public void testGetCustomerDetailsWithCorrectCustomerId() throws CustomerDetailsNotFoundException {
        Assert.assertEquals(customer , customerService.getCustomerDetails(1));
    }

    @Test(expected = CustomerDetailsNotFoundException.class)
    public void testGetCustomerDetailsWithInCorrectCustomerId() throws CustomerDetailsNotFoundException {
        try {
            customerService.getCustomerDetails(2);
        }catch (NullPointerException ne){
            throw new CustomerDetailsNotFoundException("Details not found");
        }
    }

    @Test
    public void testGetAllCustomerDetails(){
        Assert.assertEquals(2, customerService.getAllCustomerDetails().size());
        Assert.assertEquals(customer, customerService.getAllCustomerDetails().get(0));
    }

    @After
    public void tearDownTestMockData(){

    }

    @AfterClass
    public static void tearDown() {
        customer = null;
    }
}