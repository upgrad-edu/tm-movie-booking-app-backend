package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Status;
import com.upgrad.mtb.exceptions.StatusDetailsNotFoundException;

public interface StatusService {
    Status acceptNewStatus(Status status);
    Status getStatusDetails(String status)throws StatusDetailsNotFoundException;
    Status getStatusDetails(int id)throws StatusDetailsNotFoundException;
}
