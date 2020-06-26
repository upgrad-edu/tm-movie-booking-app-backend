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

import java.util.Arrays;
import java.util.Optional;
import static  org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LanguageServiceTest {


    @Mock
    LanguageDAO languageDAO;

    @InjectMocks
    LanguageServiceImpl languageService;       // make sure default constructor must be there in the LanguageServiceImpl


    @BeforeEach
    public void setUPMockObjectStub(){
        Language marathiLanguage = new Language(111,"Marathi");
        Language hindiLanguage = new Language(222,"Hindi");

        Optional<Language> optionalMarathiLanguage = Optional.ofNullable(marathiLanguage);

        when(languageDAO.save(marathiLanguage)).thenReturn(marathiLanguage);
        when(languageDAO.findByLanguage("Marathi")).thenReturn(optionalMarathiLanguage);
        when(languageDAO.findById(111)).thenReturn(optionalMarathiLanguage);
        when(languageDAO.findAll()).thenReturn(Arrays.asList(marathiLanguage));

        Optional<Language>optionalHindiLanguage = Optional.ofNullable(hindiLanguage);

        Mockito.when(languageDAO.save(hindiLanguage)).thenReturn(hindiLanguage);
        Mockito.when(languageDAO.findByLanguage("Marathi")).thenReturn(optionalHindiLanguage);
        Mockito.when(languageDAO.findById(222)).thenReturn(optionalHindiLanguage);

        Optional<Language> otherLanguage = Optional.ofNullable(null);
        when(languageDAO.findByLanguage("English")).thenReturn(otherLanguage);
        when(languageDAO.findById(6352)).thenReturn(otherLanguage);
    }

    @DisplayName("testGetLanguageDetailsForInvalidLanguageName")
    @Test
    public void test1(){
        Assertions.assertThrows(LanguageDetailsNotFoundException.class,()->languageService.getLanguageDetails("English"));
        verify(languageDAO,Mockito.times(1)).findByLanguage("English");
    }

    @DisplayName("testGetLanguageDetailsForValidLanguageName")
    @Test
    public void test2() throws LanguageDetailsNotFoundException {
        languageService.getLanguageDetails("Marathi");
        verify(languageDAO,Mockito.times(1)).findByLanguage("Marathi");
    }

    @DisplayName("testAddNewLanguageForValidData")
    @Test
    public void test3(){
        Language expectedLanguage = new Language(111,"Marathi");
        Language actualLanguage = languageService.addNewLanguage(new Language(111,"Marathi"));
        Assertions.assertEquals(expectedLanguage,actualLanguage);
        verify(languageDAO,Mockito.times(1)).save(new Language(111,"Marathi"));
    }


    @AfterEach
    public void tearDownUPMockObjectStub(){

    }
    @AfterAll
    public static void tearDownTestEnv(){ }
}
