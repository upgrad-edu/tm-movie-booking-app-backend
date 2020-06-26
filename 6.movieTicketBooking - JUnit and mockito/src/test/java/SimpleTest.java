
import com.upgrad.mtb.beans.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.HashMap;

import static  org.mockito.Mockito.*;
import static  org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SimpleTest {

    @Mock
    ArrayList<Integer> arrayList;    // mocked object dummy  object


    @BeforeEach
    public void setUpStubCodeForMockedObject(){
        when(arrayList.size()).thenReturn(10667).thenReturn(500).thenReturn(100);      // stubbing
    }

    @Test
    public void test1(){

        int firstCallSize = arrayList.size();
        System.out.println(firstCallSize );
        verify(arrayList ,atMost(2)).size();
    }
}
