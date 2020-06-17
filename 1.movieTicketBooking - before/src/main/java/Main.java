import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.Theatre;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("mtbBeans.xml");
        /*Movie movie = (Movie) context.getBean("movie");
        System.out.println(movie);*/

        Theatre theatre =(Theatre) context.getBean("theatre");
        System.out.println(theatre);
    }
}
