import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.beans.UserType;
import com.upgrad.mtb.daos.BookingDAO;
import com.upgrad.mtb.daos.CustomerDAO;
import com.upgrad.mtb.exceptions.BookingDetailsNotFoundException;
import com.upgrad.mtb.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mtb.services.BookingService;
import com.upgrad.mtb.services.BookingServiceImpl;
import com.upgrad.mtb.services.CustomerServiceImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:mtbBeans.xml"})
public class BookingServiceTest {
    @InjectMocks
    BookingServiceImpl bookingService;

    @Mock
    BookingDAO bookingDAO;

    public static Booking booking;

    @BeforeClass
    public static void setUpTestEnv() throws ParseException {
        booking = new Booking(new Date("31/01/2020"),10, new Theatre(), new Customer());

    }

    @Before
    public void setUpTestMock() {
        when(bookingDAO.save(booking)).thenReturn(booking);
        when(bookingDAO.findById(1)).thenReturn(java.util.Optional.ofNullable(booking));

        Booking booking1 =  new Booking(new Date("21/01/2020"),20, new Theatre(), new Customer());

        when(bookingDAO.findAll()).thenReturn(new ArrayList<>(Arrays.asList(booking, booking1)));
    }

    @Test
    public void testAcceptBookingDetails() throws ParseException {
        Assert.assertEquals(booking, bookingService.acceptBookingDetails(booking));
    }

    @Test
    public void testGetCustomerDetailsWithCorrectCustomerId() throws BookingDetailsNotFoundException {
        Assert.assertEquals(booking, bookingService.getBookingDetails(1));
    }

    @Test(expected = BookingDetailsNotFoundException.class)
    public void testAcceptBookingDetailsWithInCorrectId() throws BookingDetailsNotFoundException {
        try {
            bookingService.getBookingDetails(2);
        } catch (NullPointerException ne) {
            throw new BookingDetailsNotFoundException("Details not found");
        }
    }

    @Test
    public void testGetAllBookingDetails() {
        Assert.assertEquals(2, bookingService.getAllBookingDetails().size());
        Assert.assertEquals(booking,bookingService.getAllBookingDetails().get(0));
    }


    @After
    public void tearDownTestMockData() {

    }

    @AfterClass
    public static void tearDown() {
        booking = null;
    }

}
