package com.upgrad.mtb.DAO;

import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.beans.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusDAO extends JpaRepository<Status, Integer> {
}
