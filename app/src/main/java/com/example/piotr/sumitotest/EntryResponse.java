package com.example.piotr.sumitotest;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Piotr on 29.11.2016.
 */
public class EntryResponse {
    @SerializedName("entries")
    private ArrayList<Entry> entries;

    public ArrayList<Entry> getEntries() {
        return entries;
    }
}
