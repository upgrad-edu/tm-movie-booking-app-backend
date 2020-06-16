import com.upgrad.mtb.beans.*;
import com.upgrad.mtb.exceptions.*;
import com.upgrad.mtb.services.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:mtbBeans.xml"})
public class MovieServiceTest {
    @Autowired
    MovieService movieService;
    @Autowired
    LanguageService languageService;
    @Autowired
    StatusService statusService;

    public Movie movie = new Movie();

    @BeforeClass
    public static void setUpTestEnv(){

    }

    @Before
    public void setUpTestData() throws UserTypeDetailsNotFoundException, LanguageDetailsNotFoundException, StatusDetailsNotFoundException {
        Language hindiLanguage = new Language("hindi");
        languageService.acceptLanguageDetails(hindiLanguage);

        Status adminStatus = new Status("Admin");
        statusService.acceptStatusDetails(adminStatus);

        movie.setName("Dhoom 200");
        movie.setCoverPhotoURL("coverURL");
        movie.setTrailerURL("trailerURL");
        movie.setDuration(180);
        movie.setDescription("description of movie");
        movie.setLanguage(languageService.getLanguageDetailsByLanguageName("hindi"));
        movie.setStatus(statusService.getStatusDetailsByStatusName("Admin"));
        movie.setReleaseDate(new Date("22/12/2020"));

    }

    @Test
    public void testAcceptMovieDetails(){
        Movie savedMovie = movieService.acceptMovieDetails(movie);
        Assert.assertEquals(movie,savedMovie);
    }

    @Test
    public void testGetMovieDetailsWithCorrectData() throws MovieDetailsNotFoundException {
        Movie savedMovie = movieService.acceptMovieDetails(movie);
        int savedMovieId = savedMovie.getId();
        Movie testSavedMovie =  movieService.getMovieDetails(savedMovieId);
        Assert.assertEquals(savedMovie.getName(), testSavedMovie.getName());
        Assert.assertEquals(savedMovie.getDuration(), testSavedMovie.getDuration());
        Assert.assertEquals(savedMovie.getDescription(), testSavedMovie.getDescription());
    }

    @Test(expected = MovieDetailsNotFoundException.class)
    public void testGetMovieDetailsWithInCorrectData() throws MovieDetailsNotFoundException {
        Movie savedMovie = movieService.getMovieDetailsByMovieName("Dhoom 200");
        int savedMovieId = savedMovie.getId();
        Movie testSavedMovie = movieService.getMovieDetails(savedMovieId + 100);
        Assert.assertEquals(savedMovie , testSavedMovie);
    }
    @Test(expected = MovieDetailsNotFoundException.class)
    public void testGetMovieDetailsWithInCorrectMovieName() throws MovieDetailsNotFoundException {
        Movie savedMovie =  movieService.getMovieDetailsByMovieName("XTZ 200");
        Assert.assertEquals(movie , savedMovie);
    }

    @After
    public void tearDownTestMockData() throws MovieDetailsNotFoundException {
        movieService.deleteMovie(movieService.getMovieDetailsByMovieName("Dhoom 200").getId());
    }

    @AfterClass
    public static void tearDownTestEnv(){

    }

}
