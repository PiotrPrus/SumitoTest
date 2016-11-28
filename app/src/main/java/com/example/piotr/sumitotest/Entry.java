package com.example.piotr.sumitotest;

/**
 * Created by Piotr on 28.11.2016.
 */

public class Entry {

    private int mEmployeeId;
    private String mUuid;

    public Entry(int employeeId, String uuid){

        mEmployeeId = employeeId;
        mUuid = uuid;
    }

    public int getEmployeeId() {
        return mEmployeeId;
    }

    public String getUuid() {
        return mUuid;
    }
}
