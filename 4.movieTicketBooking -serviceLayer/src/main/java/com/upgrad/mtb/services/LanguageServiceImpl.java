package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.beans.UserType;
import com.upgrad.mtb.daos.LanguageDAO;
import com.upgrad.mtb.exceptions.BookingDetailsNotFoundException;
import com.upgrad.mtb.exceptions.LanguageDetailsNotFoundException;
import com.upgrad.mtb.exceptions.UserTypeDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("languageService")
public class LanguageServiceImpl implements LanguageService {
    @Autowired
    LanguageDAO languageDAO;

    @Override
    public Language acceptLanguageDetails(Language language) {
        return languageDAO.save(language);
    }

    @Override
    public Language getLanguageDetails(int id) throws LanguageDetailsNotFoundException {
        return languageDAO.findById(id).orElseThrow(
                ()->  new LanguageDetailsNotFoundException("Details not found for id : " + id));
    }

    @Override
    public Language getLanguageDetaisByLanguageName(String languageName) throws LanguageDetailsNotFoundException {
        Language myLanguage = languageDAO.findDistinctByLanguage(languageName);
        if(myLanguage == null)
            throw new LanguageDetailsNotFoundException("Details not found for :" + languageName);
        else
            return myLanguage;
    }

    @Override
    public boolean deleteLanguage(int id) throws LanguageDetailsNotFoundException {
        Language language = getLanguageDetails(id);
        languageDAO.delete(language);
        return true;
    }

    @Override
    public List<Language> getAllLanguageDetails() {
        return languageDAO.findAll();
    }
}
