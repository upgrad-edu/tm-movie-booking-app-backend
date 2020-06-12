package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.UserType;

import java.util.List;

public interface UserTypeDAO {
    public UserType acceptUserTypeDetails(UserType userType);
    public UserType getUserTypeDetails(int userTypeId);
    public boolean deleteUserType(int userTypeId);
    public List<UserType> getAllUserTypeDetails();
}
