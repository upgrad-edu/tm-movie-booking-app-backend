package com.upgrad.mtb;

import com.upgrad.mtb.beans.City;
import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.beans.Status;
import com.upgrad.mtb.beans.UserType;
import com.upgrad.mtb.daos.CityDAO;
import com.upgrad.mtb.daos.LanguageDAO;
import com.upgrad.mtb.daos.StatusDAO;
import com.upgrad.mtb.daos.UserTypeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MovieTicketBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieTicketBookingApplication.class, args);

	}

	@Bean
	CommandLineRunner init (@Qualifier("cityDAO")CityDAO cityDAO, @Qualifier("userTypeDAO")UserTypeDAO userTypeDAO ,
							@Qualifier("languageDAO")LanguageDAO languageDAO, @Qualifier("statusDAO")StatusDAO statusDAO){
		return args -> {
			List<City> cities = Arrays.asList(new City("Patna"), new City("Mumbai"), new City("Kolkata"), new City("Bangalore"));
			List<UserType> userTypes = Arrays.asList(new UserType("Customer"), new UserType("Admin"));
			List<Language> languages = Arrays.asList(new Language("English"), new Language("Hindi"), new Language("Bengali"));
			List<Status> statuses = Arrays.asList(new Status("Upcoming"), new Status("Released"), new Status("Blocked"));
			cities.forEach(city -> cityDAO.save(city));
			userTypes.forEach(userType -> userTypeDAO.save(userType));
			languages.forEach(language -> languageDAO.save(language));
			statuses.forEach(status -> statusDAO.save(status));
		};
	}

}
