package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.UserType;

import java.util.List;

public interface UserTypeDAO {
    public UserType acceptUserTypeDetails(UserType userType);
    public UserType acceptUserTypeDetailsTransactional(UserType userType);
    public UserType getUserTypeDetails(int id);
    public boolean deleteUserType(int id);
    public boolean deleteUserTypeTransactional(int id);
    public List<UserType> getAllUserTypeDetails();
}
