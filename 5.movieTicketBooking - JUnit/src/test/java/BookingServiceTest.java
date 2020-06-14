import com.upgrad.mtb.services.BookingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.text.ParseException;
import org.junit.Assert;
import java.text.SimpleDateFormat;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:mtbBeans.xml"})
public class BookingServiceTest {
    @Autowired
    BookingService bookingService;

    @Test
    public void bookingServiceAddition()  {
        Assert.assertEquals(5,bookingService.addTotalSeats(2,3));
    }
}
