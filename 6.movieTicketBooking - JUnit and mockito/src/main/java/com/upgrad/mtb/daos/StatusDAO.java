package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusDAO extends JpaRepository<Status,Integer> {
    Optional<Status> findByName(String name);

}
