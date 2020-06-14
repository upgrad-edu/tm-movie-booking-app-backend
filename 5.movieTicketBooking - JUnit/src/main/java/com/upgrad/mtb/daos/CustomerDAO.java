package com.upgrad.mtb.daos;
import com.upgrad.mtb.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("customerDAO")
public interface CustomerDAO extends JpaRepository<Customer, Integer> {

}
