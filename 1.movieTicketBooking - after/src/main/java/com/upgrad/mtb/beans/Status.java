package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Status {
    private int id;
    private String status;

    public Status(){
    }

    public Status(int id, String status) {
        this.id = id;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
