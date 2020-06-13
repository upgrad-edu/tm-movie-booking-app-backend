package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Language;

import java.util.List;

public interface LanguageDAO {
    public Language acceptLanguageDetails(Language language);
    public Language acceptLanguageDetailsTransactional(Language language);
    public Language getLanguageDetails(int id);
    public boolean deleteLanguage(int id);
    public boolean deleteLanguageTransactional(int id);
    public List<Language> getAllLanguageDetails();
}
