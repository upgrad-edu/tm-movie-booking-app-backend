import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.daos.LanguageDAO;
import com.upgrad.mtb.exceptions.LanguageDetailsNotFoundException;
import com.upgrad.mtb.services.LanguageServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LanguageServiceTest {


    @Mock
    LanguageDAO languageDAO;

    @InjectMocks
    LanguageServiceImpl languageService;

    @BeforeEach
    public void setUpTest(){
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

    }

    @Test
    @DisplayName("testGetLanguageDetailsForInvalidLanguageName")
    public void test1(){
        Assertions.assertThrows(LanguageDetailsNotFoundException.class,()->languageService.getLanguageDetails("English"));
        Mockito.verify(languageDAO, Mockito.times(1)).findByLanguage("English");
    }


    @Test
    @DisplayName("testGetLanguageDetailsForValidLanguageName")
    public void test2(){
        Language expectedLanguage = new Language(111,"Marathi");
        Language actualLanguage= languageService.addNewLanguage(new Language(111,"Marathi"));
        Assertions.assertEquals(expectedLanguage,actualLanguage);
        Mockito.verify(languageDAO, Mockito.times(1)).save(new Language(111,"Marathi"));
    }

    @AfterEach
    public void tearDownTestMockData(){

    }
    @AfterAll
    public static void tearDownTestEnv(){ }
}
