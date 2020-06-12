package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeDAO extends JpaRepository<UserType, Integer> {
}
