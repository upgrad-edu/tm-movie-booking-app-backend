package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.Status;
import com.upgrad.mtb.daos.StatusDAO;
import com.upgrad.mtb.exceptions.LanguageDetailsNotFoundException;
import com.upgrad.mtb.exceptions.MovieDetailsNotFoundException;
import com.upgrad.mtb.exceptions.StatusDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("statusService")
public class StatusServiceImpl implements StatusService {
    @Autowired
    StatusDAO statusDAO;

    @Override
    public Status acceptStatusDetails(Status status) {
        return statusDAO.save(status);
    }

    @Override
    public Status getStatusDetails(int id) throws StatusDetailsNotFoundException {
        return statusDAO.findById(id).orElseThrow(
                ()->  new StatusDetailsNotFoundException("movie not found for " + id));
    }

    @Override
    public Status getStatusDetailsByStatusName(String statusName) throws StatusDetailsNotFoundException {
        Status myStatus = statusDAO.findDistinctByStatus(statusName);
        if(myStatus == null)
            throw new StatusDetailsNotFoundException("Details not found for :" + statusName);
        else
            return myStatus;
    }

    @Override
    public boolean deleteStatus(int id) throws StatusDetailsNotFoundException {
        Status status = getStatusDetails(id);
        statusDAO.delete(status);
        return true;
    }

    @Override
    public List<Status> getAllStatusDetails() {
        return statusDAO.findAll();
    }
}
