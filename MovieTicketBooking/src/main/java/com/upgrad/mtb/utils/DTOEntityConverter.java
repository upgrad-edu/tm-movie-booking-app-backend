package com.upgrad.mtb.utils;

import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.dto.BookingDTO;
import com.upgrad.mtb.dto.CustomerDTO;
import com.upgrad.mtb.dto.MovieDTO;
import com.upgrad.mtb.dto.TheatreDTO;
import com.upgrad.mtb.exceptions.*;
import com.upgrad.mtb.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DTOEntityConverter {

    @Autowired
    CustomerService customerService;
    @Autowired
    TheatreService theatreService;
    @Autowired
    CityService cityService;
    @Autowired
    MovieService movieService;
    @Autowired
    LanguageService languageService;
    @Autowired
    StatusService statusService;
    @Autowired
    UserTypeService userTypeService;

    public Movie convertToMovieEntity(MovieDTO movieDTO) throws StatusDetailsNotFoundException, LanguageDetailsNotFoundException, TheatreDetailsNotFoundException, CustomerDetailsNotFoundException, MovieDetailsNotFoundException {
        Movie movie = new Movie();
        movie.setCoverPhotoURL(movieDTO.getCoverURL());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setDuration(movieDTO.getDuration());
        movie.setName(movieDTO.getName());
        movie.setDescription(movieDTO.getDescription());
        movie.setTrailerURL(movieDTO.getTrailerURL());
        movie.setLanguage(languageService.getLanguageDetails(movieDTO.getLanguageId()));
        movie.setStatus(statusService.getStatusDetails(movieDTO.getStatusId()));
        List<TheatreDTO> theatreDTOList = movieDTO.getTheatres();
        List<Theatre> theatreList = new ArrayList<>();
        for(TheatreDTO theatreDTO : theatreDTOList){
            theatreList.add(convertToTheatreEntity(theatreDTO));
        }
        movie.setTheatres(theatreList);
        return movie;
    }

    public Theatre convertToTheatreEntity(TheatreDTO theatreDTO) throws TheatreDetailsNotFoundException, CustomerDetailsNotFoundException, MovieDetailsNotFoundException {
        Theatre theatre = new Theatre();
        theatre.setTicketPrice(theatreDTO.getTicketPrice());
        theatre.setNoOfSeats(theatreDTO.getNoOfSeats());
        theatre.setTheatreName(theatreDTO.getTheatreName());
        theatre.setCity(cityService.getCityDetails(theatreDTO.getCityId()));
        theatre.setMovie(movieService.getMovieDetails(theatreDTO.getMovieId()));
        List<BookingDTO> bookingDTOList = theatreDTO.getBookings();
        List<Booking> bookingList = new ArrayList<>();
        for(BookingDTO bookingDTO : bookingDTOList){
            bookingList.add(convertToBookingEntity(bookingDTO));
        }
        theatre.setBookings(bookingList);
        return theatre;
    }

    public Booking convertToBookingEntity(BookingDTO bookingDTO) throws CustomerDetailsNotFoundException, TheatreDetailsNotFoundException {
        Booking booking = new Booking();
        booking.setNoOfSeats(bookingDTO.getNoOfSeats());
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setCustomer(customerService.getCustomerDetails(bookingDTO.getCustomerId()));
        booking.setTheatre(theatreService.getTheatreDetails(bookingDTO.getTheatreId()));
        return booking;
    }

    public Customer convertToCustomerEntity(CustomerDTO customerDTO) throws UserTypeDetailsNotFoundException, TheatreDetailsNotFoundException, CustomerDetailsNotFoundException {
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setUsername(customerDTO.getUsername());
        customer.setPassword(customerDTO.getPassword());
        customer.setPhoneNumbers(customerDTO.getPhoneNumbers());
        customer.setUserType(userTypeService.getUserTypeDetails(customerDTO.getUserTypeId()));
        customer.setDateOfBirth(customerDTO.getDateOfBirth());
        List<BookingDTO> bookingDTOList = customerDTO.getBookings();
        List<Booking> bookings = new ArrayList<>();
        for(BookingDTO bookingDTO : bookingDTOList)
            bookings.add(convertToBookingEntity(bookingDTO));
        customer.setBookings(bookings);
        customer.setRefreshToken(customerDTO.getRefreshToken());
        customer.setJwtToken(customerDTO.getJwtToken());
        return customer;
    }
}
