import com.upgrad.mtb.beans.*;
import com.upgrad.mtb.daos.MovieDAO;
import com.upgrad.mtb.daos.TheatreDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args){
        //adding City data
        City mumbaiCity = new City("Mumbai");
        City patnaCity = new City("patna");
        City kolkataCity = new City("Kolkata");

        //Theatre theatre = new Theatre("Epic Theatres", 100 , 150, cityDAO.findDistinctByCity("Mumbai"), new ArrayList<>(), movieDAO.findByName("Dhoom 213"));

        ApplicationContext context = new ClassPathXmlApplicationContext("mtbBeans.xml");


    }
}
