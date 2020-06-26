import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@SelectClasses({MovieServiceTest.class})
public class MTBTestSuite {
}
