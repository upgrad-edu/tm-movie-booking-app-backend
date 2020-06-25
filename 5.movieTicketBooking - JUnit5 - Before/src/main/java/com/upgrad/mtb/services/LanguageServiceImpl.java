package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.daos.LanguageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("languageService")
public class LanguageServiceImpl implements  LanguageService {

    @Autowired
    LanguageDAO languageDAO;

    @Override
    public Language addNewLanguage(Language language) {
        return languageDAO.save(language);
    }
}
