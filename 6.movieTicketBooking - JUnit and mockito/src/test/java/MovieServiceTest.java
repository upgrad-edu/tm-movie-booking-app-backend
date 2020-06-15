import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.Status;
import com.upgrad.mtb.daos.MovieDAO;
import com.upgrad.mtb.exceptions.MovieDetailsNotFoundException;
import com.upgrad.mtb.services.MovieServiceImpl;
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
public class MovieServiceTest {
    @InjectMocks
    MovieServiceImpl movieService;

    @Mock
    MovieDAO movieDAO;

    public static Movie movie;

    @BeforeClass
    public static void setUpTestEnv() throws ParseException {
        movie = new Movie("Dhoom 201", "description of movie" , new Date("20/12/2030"),180, "coverURL" , "trailerURL" , new Language("Hindi"), new Status("upcoming"));
    }

    @Before
    public void setUpTestMock() {
        when(movieDAO.save(movie)).thenReturn(movie);
        when(movieDAO.findById(1)).thenReturn(java.util.Optional.ofNullable(movie));
        when(movieDAO.findAll()).thenReturn(new ArrayList<>(Arrays.asList(movie)));
    }

    @Test
    public void testAcceptMovieDetails() throws ParseException {
        Assert.assertEquals(movie, movieService.acceptMovieDetails(movie));
    }

    @Test
    public void testGetMovieDetailsWithCorrectId() throws MovieDetailsNotFoundException {
        Assert.assertEquals(movie, movieService.getMovieDetails(1));
    }

    @Test(expected = MovieDetailsNotFoundException.class)
    public void testAcceptMovieDetailsWithInCorrectId() throws MovieDetailsNotFoundException {
        try {
            movieService.getMovieDetails(2);
        } catch (NullPointerException ne) {
            throw new MovieDetailsNotFoundException("Details not found");
        }
    }

    @Test
    public void testGetAllBookingDetails() {
        Assert.assertEquals(1, movieService.getAllMoviesDetails().size());
        Assert.assertEquals(movie,movieService.getAllMoviesDetails().get(0));
    }


    @After
    public void tearDownTestMockData() {

    }

    @AfterClass
    public static void tearDown() {
        movie = null;
    }

}
