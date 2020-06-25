package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusDAO extends JpaRepository<Status,Integer> {

}
