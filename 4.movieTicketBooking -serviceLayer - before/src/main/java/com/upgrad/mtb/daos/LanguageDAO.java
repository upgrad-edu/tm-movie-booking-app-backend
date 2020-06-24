package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("LanguageDAO")
public interface LanguageDAO extends JpaRepository<Language, Integer> {
    Language findByLanguage(String language);
}
