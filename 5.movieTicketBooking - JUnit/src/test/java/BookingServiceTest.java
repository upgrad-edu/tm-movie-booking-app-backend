import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.beans.City;
import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.exceptions.BookingDetailsNotFoundException;
import com.upgrad.mtb.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mtb.exceptions.TheatreDetailsNotFoundException;
import com.upgrad.mtb.services.BookingService;
import com.upgrad.mtb.services.CityService;
import com.upgrad.mtb.services.CustomerService;
import com.upgrad.mtb.services.TheatreService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:mtbBeans.xml"})
public class BookingServiceTest {
    @Autowired
    BookingService bookingService;
    @Autowired
    CustomerService customerService;
    @Autowired
    TheatreService theatreService;
    @Autowired
    CityService cityService;

    static Booking booking = new Booking();
    int customerId, theatreId, cityId;

    @BeforeClass
    public static void setUpTestEnv(){
        booking.setBookingDate(new Date("23/12/2020"));
        booking.setNoOfSeats(12);
    }

    @Before
    public void setUpTestMockData(){
        Customer customer = new Customer();
        customer.setFirstName("Ram");
        customer.setLastName("Kumar");
        customer.setUsername("mohanKumar");
        customer.setPassword("password");
        customer.setPhoneNumbers(new ArrayList<>());
        customer.setDateOfBirth(new Date("22/10/1996"));

        City city = new City("Patna");

        Theatre theatre = new Theatre();
        theatre.setCity(new City("patna"));
        theatre.setTheatreName("TheatreName");
        theatre.setNoOfSeats(100);
        theatre.setTicketPrice(100);

        customerId = customerService.acceptCustomerDetails(customer).getId();
        theatreId = theatreService.acceptTheatreDetails(theatre).getId();
        cityId = cityService.acceptCityDetails(city).getId();

        booking.setCustomer(customer);
        booking.setTheatre(theatre);
    }

    @Test
    public void testAcceptBookingDetails(){
        Booking savedBooking = bookingService.acceptBookingDetails(booking);
        Assert.assertEquals(booking,savedBooking);
    }

    @Test
    public void testGetBookingDetailsWithCorrectData() throws BookingDetailsNotFoundException {
        Booking savedBooking = bookingService.acceptBookingDetails(booking);
        int savedBookingId = savedBooking.getId();
        Booking testSavedBooking = bookingService.getBookingDetails(savedBookingId);
        Assert.assertEquals(savedBooking , testSavedBooking);
    }

    @After
    public void tearDownTestMockData() throws CustomerDetailsNotFoundException, TheatreDetailsNotFoundException {

    }

    @AfterClass
    public static void tearDownTestEnv(){

    }


}
