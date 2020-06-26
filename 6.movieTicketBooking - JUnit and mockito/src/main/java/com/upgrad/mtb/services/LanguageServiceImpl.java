package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.daos.LanguageDAO;
import com.upgrad.mtb.exceptions.LanguageDetailsNotFoundException;
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

    @Override
    public Language getLanguageDetails(String language) throws LanguageDetailsNotFoundException {
        throw new LanguageDetailsNotFoundException();
    }

    @Override
    public Language getLanguageDetails(int id) throws LanguageDetailsNotFoundException {
        return null;
    }
}
