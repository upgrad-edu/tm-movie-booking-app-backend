import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.Status;
import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.daos.LanguageDAO;
import com.upgrad.mtb.daos.MovieDAO;
import com.upgrad.mtb.daos.StatusDAO;
import com.upgrad.mtb.daos.TheatreDAO;
import com.upgrad.mtb.exceptions.StatusDetailsNotFoundException;
import com.upgrad.mtb.services.LanguageService;
import com.upgrad.mtb.services.MovieService;
import com.upgrad.mtb.services.MovieServiceImpl;
import com.upgrad.mtb.services.StatusService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("mtbBeans.xml");
       /* Language marathiLanguage = new Language("Marathi");
        Language hindiLanguage = new Language("Hindi");

        LanguageDAO languageDAO =(LanguageDAO)context.getBean("LanguageDAO") ;

        Language insertMarathi= languageDAO.save(marathiLanguage);
        Language insertedHindi = languageDAO.save(hindiLanguage);

        StatusDAO statusDAO = (StatusDAO) context.getBean("statusDAO");
        Status releaseStatus =new Status("Released");
        releaseStatus = statusDAO.save(releaseStatus);


        TheatreDAO theatreDAO = (TheatreDAO)context.getBean("theatreDAO") ;
        Theatre insertedTheatre  = theatreDAO.save(new Theatre("IMAX",250,300));


        MovieDAO movieDAO = (MovieDAO) context.getBean("movieDAO");
        // Movie CRUD operation using Spring Data

        //insert
        Movie movie1 = new Movie("Dhoom","Movie about bike racing", new Date("2/9/2015"), 180, "coverPhotoURL" , "trailerURL");
        Movie movie2 = new Movie("Madari","Movie child death revenge", new Date("12/3/2017"), 180, "coverPhotoURL" , "trailerURL");
        Movie movie3 = new Movie("URI","Movie about URI attack", new Date("4/7/2018"), 150, "coverPhotoURL" , "trailerURL");
        Movie movie4 = new Movie("Shole","Movie about gabbar singh daku", new Date("22/6/1986"), 210, "coverPhotoURL" , "trailerURL");
        Movie movie5 = new Movie("Dhoni the untold story","Movie about Cricketer Mahendra singh dhoni", new Date("14/5/2016"), 210, "coverPhotoURL" , "trailerURL");
        Movie movie6 = new Movie("Bahubali","Movie about bike racing", new Date("10/7/2015"), 150, "coverPhotoURL" , "trailerURL");

        movie4.setLanguage(insertedHindi);
        movie2.setLanguage(insertMarathi);
        movie2.setLanguage(insertedHindi);
        movie3.setLanguage(insertedHindi);
        movie5.setLanguage(insertedHindi);


        movie1.setStatus(releaseStatus);
        movie2.setStatus(releaseStatus);
        movie3.setStatus(releaseStatus);
        movie4.setStatus(releaseStatus);
        movie5.setStatus(releaseStatus);
        movie6.setStatus(releaseStatus);

        movie1= movieDAO.save(movie1);
        movie2=movieDAO.save(movie2);
        movie2=movieDAO.save(movie3);
        movie3= movieDAO.save(movie4);
        movie4=movieDAO.save(movie5);
        movie5= movieDAO.save(movie6);

        List<Theatre> insertedTheatreList= new ArrayList<>();
        insertedTheatreList.add(insertedTheatre);   // IMAX
        movie1.setTheatres(insertedTheatreList);     // list

        List<Movie> insertedMOvies =new ArrayList<>();
        insertedMOvies.add(movie1);

        insertedTheatre.setMovies(insertedMOvies);
        movieDAO.save(movie1);                  //update
        theatreDAO.save(insertedTheatre);       //update


        // select
       *//* Movie serachdMovie = movieDAO.findById(1).get();
        System.out.println("Searched Movie :-"+serachdMovie);

        Movie movieByName1 = movieDAO.findByName("Dhoom");
        System.out.println("Searched Movie byName :-"+movieByName1);

        List<Movie> moviesByDuration = movieDAO.findByDuration(180);
        System.out.println("Searched Movies By Duration "+moviesByDuration);

        Movie movieByName2 = movieDAO.getMovieDetails("Dhoom");
        System.out.println("Searched Movie byName :-"+movieByName2);*//**//*

        List<Movie> moviesByDurationBetween = movieDAO.findByDurationBetween(130,240);
        System.out.println("\n\nSearched Movies By DurationBetween");
        moviesByDurationBetween.forEach(System.out::println);*//*

        System.out.println("====================================================================================");
*//*

        List<Movie> moviesByName = movieDAO.getMovieDetailsName("URI");
        moviesByName.forEach(movie -> System.out.println(movie));

        List<Movie> moviesByDuration = movieDAO.getMovieDetailsDuration(180);
        moviesByDuration.forEach(movie -> System.out.println(movie));
*//*

        System.out.println("====================================================================================");

        List<Movie> moviesByName = movieDAO.findByName("URI");
        moviesByName.forEach(movie -> System.out.println(movie));

        List<Movie> moviesByDuration = movieDAO.findByDuration(180);
        moviesByDuration.forEach(movie -> System.out.println(movie));

        System.out.println("====================================================================================");
        List<Movie> moviesByDurationBetween = movieDAO.findByDurationBetween(150,230);
        moviesByDurationBetween.forEach(movie -> System.out.println(movie));

        System.out.println("====================================================================================");
        List<Movie> moviesByNameLike = movieDAO.findByNameLike("%o%");
        moviesByDurationBetween.forEach(movie -> System.out.println(movie));

        System.out.println("====================================================================================");

        List<Integer> durationValues = new ArrayList<>();
        durationValues.add(180);
        durationValues.add(150);
        List<Movie> moviesByDurationIn = movieDAO.findByDurationIn(durationValues);
        moviesByDurationBetween.forEach(movie -> System.out.println(movie));*/

        // Admin of application wants to execute below services for software requirment
        try {

            StatusService statusService = (StatusService) context.getBean("statusService");

            Status releaseStatus = new Status("Released");
            Status notReleasedStatus = new Status("NotReleased");

            statusService.acceptNewStatus(releaseStatus);
            statusService.acceptNewStatus(notReleasedStatus);

            LanguageService languageService = (LanguageService) context.getBean("languageService");

            languageService.addNewLanguage(new Language("Marathi"));

            MovieService movieService = (MovieService) context.getBean("movieService");

            // adding new in application movie

            //accept Movie details
            Movie movie1 = new Movie("Dhoom", "Movie about bike racing", new Date("2/9/2015"), 180, "coverPhotoURL", "trailerURL");
            Movie movie2 = new Movie("Madari", "Movie child death revenge", new Date("6/8/2020"), 180, "coverPhotoURL", "trailerURL");


            movie1 = movieService.acceptMovieDetails(movie1, "Marathi", 1);
            movie2 = movieService.acceptMovieDetails(movie2, "Marathi", 3);


            List<Movie> movies = movieService.getAllMoviesDetails();
            movies.forEach(movie -> System.out.println(movie));

        }catch (StatusDetailsNotFoundException e){
            e.printStackTrace();
        }
    }
}
