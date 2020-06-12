package com.upgrad.mtb.DAO;
import com.upgrad.mtb.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerDAO extends JpaRepository<Customer, Integer> {

}
