import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.daos.MovieDAO;
import com.upgrad.mtb.exceptions.MovieDetailsNotFoundException;
import com.upgrad.mtb.services.LanguageService;
import com.upgrad.mtb.services.MovieService;
import com.upgrad.mtb.services.StatusService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:mtbBeans.xml"})
public class MovieServiceTest {
    static Movie movie1;
    static Movie movie2;
    static int movie1Id;
    static int movie2Id;

    @BeforeAll
    public static void setUpTestEnv() {
    }
    @BeforeEach
    public void setUpTestMockData(){
         movie1 = new Movie("Movie1", "Movie1 Desc", new Date(2020,10,10), 180 ,"coverURL" , "trailerURL");
         movie2 = new Movie("Movie2", "Movie1 Desc", new Date(2020,10,10), 180 ,"coverURL" , "trailerURL");
         movie1Id = movieDAO.save(movie1).getId();
         movie2Id = movieDAO.save(movie2).getId();

    }
    @Test
    public void movieNotFoundTest(){
        Assertions.assertThrows(MovieDetailsNotFoundException.class,()->movieService.getMovieDetails(300));
    }
    @Test
    public void movieDetailsFoundTest() throws MovieDetailsNotFoundException {
        Movie foundMovie = movieService.getMovieDetails(movie1Id);
        Assertions.assertEquals(movie1.getDescription() , foundMovie.getDescription());
        Assertions.assertEquals(movie1.getName() , foundMovie.getName());
        Assertions.assertEquals(movie1.getCoverPhotoURL() , foundMovie.getCoverPhotoURL());
    }

    @AfterEach
    public void tearDownTestMockData(){
        movieDAO.delete(movie1);
        movieDAO.delete(movie2);

    }
    @AfterAll
    public static void tearDownTestEnv(){ }
    @Autowired
    MovieService movieService;
    @Autowired
    LanguageService languageService;
    @Autowired
    StatusService statusService;
    @Qualifier("movieDAO")
    @Autowired
    MovieDAO movieDAO;
}
