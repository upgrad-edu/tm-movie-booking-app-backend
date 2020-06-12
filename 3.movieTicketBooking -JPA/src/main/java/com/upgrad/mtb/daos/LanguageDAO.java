package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageDAO extends JpaRepository<Language, Integer> {
}
