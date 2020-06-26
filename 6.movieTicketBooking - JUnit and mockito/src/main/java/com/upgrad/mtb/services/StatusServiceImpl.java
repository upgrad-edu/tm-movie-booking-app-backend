package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Status;
import com.upgrad.mtb.daos.StatusDAO;
import com.upgrad.mtb.exceptions.StatusDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("statusService")
public class StatusServiceImpl implements StatusService {

       @Autowired
       StatusDAO statusDAO;

    @Override
    public Status acceptNewStatus(Status status) {
        return statusDAO.save(status);
    }

    @Override
    public Status getStatusDetails(String status) throws StatusDetailsNotFoundException {
        return null;
    }

    @Override
    public Status getStatusDetails(int id) throws StatusDetailsNotFoundException {
        return null;
    }
}
