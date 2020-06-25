import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.Status;
import com.upgrad.mtb.daos.MovieDAO;
import com.upgrad.mtb.daos.StatusDAO;
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
public class StatusServiceTest {
    static Status released;
    static Status upcoming;
    static Status blocked;
    static int releasedId;
    static int upcomingId;
    static int blockedId;

    @BeforeAll
    public static void setUpTestEnv() {
    }
    @BeforeEach
    public void setUpTestMockData(){
        released = new Status("Released");
        upcoming = new Status("Upcoming");
        blocked = new Status("Blocked");
        releasedId = statusDAO.save(released).getId();
        upcomingId = statusDAO.save(upcoming).getId();
        blockedId = statusDAO.save(blocked).getId();
    }
    @Test
    public void statusNotFoundTest(){
        Assertions.assertThrows(StatusDetailsNotFoundException.class,()->statusService.getStatusDetails(300));
    }
    @Test
    public void statusDetailsFoundTest() throws MovieDetailsNotFoundException, StatusDetailsNotFoundException {
        Status releasedStatus = statusService.getStatusDetails(releasedId);
        Status blockedStatus = statusService.getStatusDetails(blockedId);
        Assertions.assertEquals(releasedStatus.getStatus() , released.getStatus());
        Assertions.assertEquals(blockedStatus.getStatus() , blocked.getStatus());
    }

    @AfterEach
    public void tearDownTestMockData(){
        statusDAO.delete(released);
        statusDAO.delete(upcoming);
        statusDAO.delete(blocked);
    }
    @AfterAll
    public static void tearDownTestEnv(){ }
    @Autowired
    MovieService movieService;
    @Autowired
    LanguageService languageService;
    @Autowired
    StatusService statusService;
    @Qualifier("statusDAO")
    @Autowired
    StatusDAO statusDAO;
}
