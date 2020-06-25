import com.upgrad.mtb.beans.*;
import com.upgrad.mtb.exceptions.*;
import com.upgrad.mtb.services.*;
import org.junit.*;
import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.Date;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:mtbBeans.xml"})
public class MovieServiceTest {
    @BeforeAll
    public static void setUpTestEnv() {
    }
    @BeforeEach
    public void setUpTestMockData(){

    }
    @Test
    public void test1(){
        Assertions.assertThrows(MovieDetailsNotFoundException.class,()->movieService.getMovieDetails(3));
    }
    @Test
    public void test2(){ }
    @AfterEach
    public void tearDownTestMockData(){ }
    @AfterAll
    public static void tearDownTestEnv(){ }
    @Autowired
    MovieService movieService;
    @Autowired
    LanguageService languageService;
    @Autowired
    StatusService statusService;
}
