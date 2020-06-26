import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.Status;
import com.upgrad.mtb.daos.LanguageDAO;
import com.upgrad.mtb.daos.MovieDAO;
import com.upgrad.mtb.daos.StatusDAO;
import com.upgrad.mtb.exceptions.LanguageDetailsNotFoundException;
import com.upgrad.mtb.exceptions.StatusDetailsNotFoundException;
import com.upgrad.mtb.services.MovieServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MovieServiceTest {
    @Mock
    MovieDAO movieDAO;

    @Mock
    LanguageDAO languageDAO;

    @Mock
    StatusDAO statusDAO;

    @InjectMocks
    MovieServiceImpl movieService;

    @BeforeAll
    public static void setUpTestEnv() {
    }
    @BeforeEach
    public void setUpTestStubData(){
        //Language DAO  mocking
        Language marathiLanguage = new Language(111,"Marathi");

        Mockito.when(languageDAO.save(marathiLanguage)).thenReturn(marathiLanguage);
        Optional<Language>optionalMarathiLanguage = Optional.ofNullable(marathiLanguage);
        Mockito.when(languageDAO.findByLanguage("Marathi")).thenReturn(optionalMarathiLanguage);
        Mockito.when(languageDAO.findById(111)).thenReturn(optionalMarathiLanguage);


        Language hindiLanguage = new Language(222,"Hindi");
        Mockito.when(languageDAO.save(hindiLanguage)).thenReturn(hindiLanguage);
        Optional<Language>optionalHindiLanguage = Optional.ofNullable(hindiLanguage);
        Mockito.when(languageDAO.findByLanguage("Marathi")).thenReturn(optionalHindiLanguage);
        Mockito.when(languageDAO.findById(222)).thenReturn(optionalHindiLanguage);


        Optional<Language>nullLanguage = Optional.ofNullable(null);
        Mockito.when(languageDAO.findByLanguage("English")).thenReturn(nullLanguage);
        Mockito.when(languageDAO.findById(2444)).thenReturn( nullLanguage);

        // Status DAO mocking

        Status statusReleased = new Status(11,"Released");
        Mockito.when(statusDAO.save(statusReleased)).thenReturn(statusReleased);
        Optional<Status>optionalStatusReleased = Optional.ofNullable(statusReleased);
        Mockito.when(statusDAO.findById(11)).thenReturn(optionalStatusReleased);
        Mockito.when(statusDAO.findByName("Released")).thenReturn(optionalStatusReleased);

        Status statusNotReleased = new Status(12,"NotReleased");
        Mockito.when(statusDAO.save(statusNotReleased)).thenReturn(statusNotReleased);
        Optional<Status>optionalStatusNotReleased = Optional.ofNullable(statusNotReleased);
        Mockito.when(statusDAO.findById(12)).thenReturn(optionalStatusNotReleased);
        Mockito.when(statusDAO.findByName("NotReleased")).thenReturn(optionalStatusNotReleased);

        Optional<Status>nullStatus = Optional.ofNullable(null);
        Mockito.when(statusDAO.findById(124)).thenReturn(nullStatus);
        Mockito.when(statusDAO.findByName("SomeOtherStatus")).thenReturn(nullStatus);

        // Movie DAO mocking


        Movie movie1 = new Movie("Shole");
        Movie movie2 = new Movie("Dhoom");
        Mockito.when(movieDAO.save(movie1)).thenReturn(movie1);
        Mockito.when(movieDAO.save(movie2)).thenReturn(movie2);

        //========================================================

        Mockito.when(movieDAO.findAll()).thenReturn(Arrays.asList(movie1,movie2));

        //
    }

    @Test
    public void testAcceptMovieDetailsForInvalidLanguage() throws LanguageDetailsNotFoundException, StatusDetailsNotFoundException {
        Movie movie1 = new Movie("Shole");
        Assertions.assertThrows(LanguageDetailsNotFoundException.class,()->movieService.acceptMovieDetails(movie1,"English",133));
        Mockito.verify(languageDAO, Mockito.times(1)).findByLanguage("English");
    }

    @Test
    public void testAcceptMovieDetailsForInvalidStatus() throws LanguageDetailsNotFoundException, StatusDetailsNotFoundException {
        Movie movie1 = new Movie("Shole");
        Assertions.assertThrows(StatusDetailsNotFoundException.class,()->movieService.acceptMovieDetails(movie1,"Marathi",13333));
    }

    @Test
    public void testMovieDetailsForIn() throws LanguageDetailsNotFoundException, StatusDetailsNotFoundException {
    }

    @AfterEach
    public void tearDownTestMockData(){

    }
    @AfterAll
    public static void tearDownTestEnv(){ }

}
