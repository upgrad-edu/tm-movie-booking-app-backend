package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDAO  extends JpaRepository<Booking, Integer> {
}
