
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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SimpleTest {

    @Mock
    ArrayList arrayList;

    @Mock
    HashMap<String,Integer>hashMap;

    @BeforeEach
    public void setUpTestStubBehaviour(){
        Mockito.when(arrayList.size()).thenReturn(5).thenReturn(10).thenReturn(100);
    }

    @Test
    public void test1(){
        int expectedSize = 5;
        int actualSize = arrayList.size();
        Assertions.assertEquals(expectedSize,actualSize);
        Mockito.verify(arrayList).size();
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

        Mockito.verify(hashMap, Mockito.times(1)).put("A", 10);
        Mockito.verify(hashMap, Mockito.times(0)).get("A");

       Assertions.assertEquals(1, hashMap.size());
        Assertions.assertEquals(10, (Integer) hashMap.get("A"));
    }
}
