package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.exceptions.LanguageDetailsNotFoundException;

import java.util.List;

public interface LanguageService {
    public Language acceptLanguageDetails(Language language);
    public Language getLanguageDetails(int id) throws LanguageDetailsNotFoundException;
    public Language getLanguageDetaisByLanguageName(String languageName) throws LanguageDetailsNotFoundException;
    public boolean deleteLanguage(int id) throws LanguageDetailsNotFoundException;
    public List<Language> getAllLanguageDetails();
}
