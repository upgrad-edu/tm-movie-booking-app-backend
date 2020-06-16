package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.UserType;
import com.upgrad.mtb.exceptions.UserTypeDetailsNotFoundException;

import java.util.List;

public interface UserTypeService {
    public UserType acceptUserTypeDetails(UserType userType);
    public UserType getUserTypeDetails(int id) throws UserTypeDetailsNotFoundException;
    public UserType getUserTypeDetailsFromUserType(String userType) throws UserTypeDetailsNotFoundException;
    public boolean deleteUserType(int id) throws UserTypeDetailsNotFoundException;
    public List<UserType> getAllUserTypeDetails();

}
