package com.example.piotr.sumitotest;

import retrofit2.http.GET;
import retrofit2.Call;

/**
 * Created by Piotr on 29.11.2016.
 */
public interface RequestInterface {

    @GET("employees?projectId=6")
    Call<EntryResponse> getJSON();
}
