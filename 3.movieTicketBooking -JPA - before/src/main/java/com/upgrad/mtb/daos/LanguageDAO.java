package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.beans.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("LanguageDAO")
public interface LanguageDAO extends JpaRepository<Language, Integer> {
}
