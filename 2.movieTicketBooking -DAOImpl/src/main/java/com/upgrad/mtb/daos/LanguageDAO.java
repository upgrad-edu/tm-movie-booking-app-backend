package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Language;

import java.util.List;

public interface LanguageDAO {
    public Language acceptLanguageDetails(Language language);
    public Language getLanguageDetails(int languageId);
    public boolean deleteLanguage(int languageId);
    public List<Language> getAllLanguageDetails();
}
