package com.example.piotr.sumitotest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Piotr on 28.11.2016.
 */

public class Entry {
    @SerializedName("id")
    private int employeeId;
    @SerializedName("uuid")
    private String uuid;

    public Entry (int employeeId, String uuid){
        this.uuid = uuid;
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getUuid() {
        return uuid;
    }
}
