package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.UserType;
import com.upgrad.mtb.daos.UserTypeDAO;
import com.upgrad.mtb.exceptions.MovieDetailsNotFoundException;
import com.upgrad.mtb.exceptions.UserTypeDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("userTypeService")
public class UserTypeServiceImpl implements UserTypeService {
    @Autowired
    UserTypeDAO userTypeDAO;
    @Override
    public UserType acceptUserTypeDetails(UserType userType) {
        return userTypeDAO.save(userType);
    }

    @Override
    public UserType getUserTypeDetails(int id) throws UserTypeDetailsNotFoundException {
        return userTypeDAO.findById(id).orElseThrow(
                ()->  new UserTypeDetailsNotFoundException("movie not found for " + id));
    }

    @Override
    public UserType getUserTypeDetailsFromUserType(String userType) throws UserTypeDetailsNotFoundException {
        UserType userType1 = userTypeDAO.findDistinctByUserType(userType);
        if(userType1 == null)
            throw new UserTypeDetailsNotFoundException("Details not found for :" + userType);
        else
            return userType1;
    }

    @Override
    public boolean deleteUserType(int id) throws UserTypeDetailsNotFoundException {
        UserType userType = getUserTypeDetails(id);
        userTypeDAO.delete(userType);
        return true;
    }

    @Override
    public List<UserType> getAllUserTypeDetails() {
        return userTypeDAO.findAll();
    }
}
