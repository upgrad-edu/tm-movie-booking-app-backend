package com.upgrad.mtb.DAO;

import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.beans.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTypeDAO extends JpaRepository<UserType, Integer> {
}
