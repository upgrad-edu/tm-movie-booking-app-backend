package com.upgrad.mtb.DAO;

import com.upgrad.mtb.beans.Status;

import java.util.List;

public interface StatusDAO {
    public Status acceptStatusDetails(Status status);
    public Status getStatusDetails(int statusId);
    public boolean deleteStatus(int statusId);
    public List<Status> getAllStatusDetails();
}
