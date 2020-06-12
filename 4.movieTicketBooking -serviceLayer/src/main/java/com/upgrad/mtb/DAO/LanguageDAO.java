package com.upgrad.mtb.DAO;

import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.beans.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageDAO extends JpaRepository<Language, Integer> {
}
