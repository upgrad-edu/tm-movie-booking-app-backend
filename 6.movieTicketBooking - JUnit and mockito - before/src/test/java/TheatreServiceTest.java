import com.upgrad.mtb.beans.City;
import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.daos.TheatreDAO;
import com.upgrad.mtb.exceptions.TheatreDetailsNotFoundException;
import com.upgrad.mtb.services.TheatreServiceImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:mtbBeans.xml"})
public class TheatreServiceTest {
    @InjectMocks
    TheatreServiceImpl theatreService;

    @Mock
    TheatreDAO theatreDAO;

    public static Theatre theatre;

    @BeforeClass
    public static void setUpTestEnv() throws ParseException {
        theatre = new Theatre("Awesome theatre", 100 , 180, new City("Mumbai"),new ArrayList<>(),  new Movie());
    }

    @Before
    public void setUpTestMock() {
        when(theatreDAO.save(theatre)).thenReturn(theatre);
        when(theatreDAO.findById(1)).thenReturn(java.util.Optional.ofNullable(theatre));
        when(theatreDAO.findAll()).thenReturn(new ArrayList<>(Arrays.asList(theatre)));
    }

    @Test
    public void testAcceptTheatreDetails() throws ParseException {
        Assert.assertEquals(theatre, theatreService.acceptTheatreDetails(theatre));
    }

    @Test
    public void testGetTheatreDetailsWithCorrectId() throws TheatreDetailsNotFoundException {
        Assert.assertEquals(theatre, theatreService.getTheatreDetails(1));
    }

    @Test(expected = TheatreDetailsNotFoundException.class)
    public void testAcceptTheatreDetailsWithInCorrectId() throws TheatreDetailsNotFoundException {
        try {
            theatreService.getTheatreDetails(2);
        } catch (NullPointerException ne) {
            throw new TheatreDetailsNotFoundException("Details not found");
        }
    }

    @Test
    public void testGetAllBookingDetails() {
        Assert.assertEquals(1, theatreService.getAllTheatreDetails().size());
        Assert.assertEquals(theatre,theatreService.getAllTheatreDetails().get(0));
    }


    @After
    public void tearDownTestMockData() {

    }

    @AfterClass
    public static void tearDown() {
        theatre = null;
    }

}
