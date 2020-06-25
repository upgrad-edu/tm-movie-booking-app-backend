import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.Status;
import com.upgrad.mtb.daos.LanguageDAO;
import com.upgrad.mtb.daos.MovieDAO;
import com.upgrad.mtb.daos.StatusDAO;
import com.upgrad.mtb.exceptions.LanguageDetailsNotFoundException;
import com.upgrad.mtb.exceptions.MovieDetailsNotFoundException;
import com.upgrad.mtb.exceptions.StatusDetailsNotFoundException;
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
public class LanguageServiceTest {
    static Language hindi;
    static Language english;
    static int hindiId;
    static int englishId;

    @BeforeAll
    public static void setUpTestEnv() {
    }
    @BeforeEach
    public void setUpTestMockData(){
        hindi = new Language("Hindi");
        english = new Language("English");
        hindiId = languageDAO.save(hindi).getId();
        englishId = languageDAO.save(english).getId();
    }
    @Test
    public void statusNotFoundTest(){
        Assertions.assertThrows(LanguageDetailsNotFoundException.class,()->languageService.getLanguageDetails(300));
    }
    @Test
    public void languageDetailsFoundTest() throws LanguageDetailsNotFoundException {
        Language hindiLanguage = languageService.getLanguageDetails(hindiId);
        Language englishLanguage = languageService.getLanguageDetails(englishId);
        Assertions.assertEquals(englishLanguage.getLanguage() , english.getLanguage());
        Assertions.assertEquals(hindiLanguage.getLanguage() , hindi.getLanguage());
    }

    @AfterEach
    public void tearDownTestMockData(){
        languageDAO.delete(hindi);
        languageDAO.delete(english);
    }
    @AfterAll
    public static void tearDownTestEnv(){ }
    @Autowired
    MovieService movieService;
    @Autowired
    LanguageService languageService;
    @Autowired
    StatusService statusService;
    @Qualifier("languageDAO")
    @Autowired
    LanguageDAO languageDAO;
}
