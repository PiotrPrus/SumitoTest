package com.example.piotr.sumitotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity {

    private static final String EMPLOYEES_URL = "http://officewise.sumito.uk:8081/admin/";

    private RecyclerView recyclerView;
    private ArrayList<Entry> data;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initializeViews();
    }

    private void initializeViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycled_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        loadJsonEmployees();

    }

    private void loadJsonEmployees() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EMPLOYEES_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<EntryResponse> call = request.getJSON();
        call.enqueue(new Callback<EntryResponse>() {
            @Override
            public void onResponse(Call<EntryResponse> call, Response<EntryResponse> response) {

                EntryResponse jsonResponse = response.body();

                recyclerView.setAdapter(new ListAdapter
                        (new ArrayList<>(jsonResponse.getEntries())));
            }

            @Override
            public void onFailure(Call<EntryResponse> call, Throwable t) {

            }
        });

    }
}
