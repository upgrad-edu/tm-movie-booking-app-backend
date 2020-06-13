package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Status;

import java.util.List;

public interface StatusDAO {
    public Status acceptStatusDetails(Status status);
    public Status acceptStatusDetailsTransactional(Status status);
    public Status getStatusDetails(int id);
    public boolean deleteStatus(int id);
    public boolean deleteStatusTransactional(int id);
    public List<Status> getAllStatusDetails();
}
