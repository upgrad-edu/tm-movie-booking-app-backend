package com.upgrad.DAO;

import com.upgrad.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerDAO extends JpaRepository<Customer, Integer>{
  

}
