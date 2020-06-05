package com.upgrad.util;

public class PhoneNumber {
    private String phoneNumber;

    public PhoneNumber(String phoneNumber){
        if(!phoneNumber.equals("") && phoneNumber != null && phoneNumber.matches("^[0-9]{10}$"))
            this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(!phoneNumber.equals("") && phoneNumber != null && phoneNumber.matches("^[0-9]{10}$"))
            this.phoneNumber = phoneNumber;
    }
}
