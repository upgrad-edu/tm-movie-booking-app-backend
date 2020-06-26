
import com.upgrad.mtb.beans.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
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

    @Spy
    HashMap<String,Integer> hashMap;    // orignal object

    @BeforeEach
    public void setUpStubCodeForMockedObject(){
        when(arrayList.size()).thenReturn(10667).thenReturn(500).thenReturn(100);      // stubbing
        when(arrayList.contains(577)).thenReturn(true);


    }

    @Test
    public void test1(){
        int firstCallSize = arrayList.size();
        System.out.println(firstCallSize );
        verify(arrayList ,atMost(2)).size();
    }
    @Test
    public void test2(){
        int expectedSize = 100;
        int actualSize1 = arrayList.size();
        System.out.println(arrayList.contains(12));
        System.out.println(actualSize1);
        Mockito.verify(arrayList).size();
        Mockito.verify(arrayList).contains(12);
    }
    @Test
    public void test3(){

        hashMap.put("A", 10);
        hashMap.put("B", 10);
        hashMap.put("C", 10);

        Assertions.assertEquals(4, hashMap.size());
        Assertions.assertEquals(10, (Integer) hashMap.get("A"));
        Mockito.verify(hashMap, times(1)).put("A", 10);
        Mockito.verify(hashMap, times(0)).get("A");

    }
}
