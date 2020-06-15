package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.daos.LanguageDAO;
import com.upgrad.mtb.exceptions.BookingDetailsNotFoundException;
import com.upgrad.mtb.exceptions.LanguageDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
