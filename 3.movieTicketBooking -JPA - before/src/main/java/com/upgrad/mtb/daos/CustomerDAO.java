package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerDAO extends JpaRepository<Customer,Integer> {

}
