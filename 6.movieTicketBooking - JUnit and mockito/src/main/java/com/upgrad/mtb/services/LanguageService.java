package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.exceptions.LanguageDetailsNotFoundException;

public interface LanguageService {
    Language addNewLanguage (Language language);
    Language getLanguageDetails(String language)throws LanguageDetailsNotFoundException;
    Language getLanguageDetails(int id)throws LanguageDetailsNotFoundException;

}
