package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Status;
import com.upgrad.mtb.exceptions.StatusDetailsNotFoundException;

import java.util.List;

public interface StatusService {
    public Status acceptStatusDetails(Status status);
    public Status getStatusDetails(int id) throws StatusDetailsNotFoundException;
    public Status getStatusDetailsByStatusName(String statusName) throws StatusDetailsNotFoundException;
    public boolean deleteStatus(int id) throws StatusDetailsNotFoundException;
    public List<Status> getAllStatusDetails();
}
