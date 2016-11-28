package com.example.piotr.sumitotest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Piotr on 28.11.2016.
 */

public class EmployeesListActivity extends AppCompatActivity {

    ArrayList<Entry> entries;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycled_view_core);

        EmployeesAdapter adapter = new EmployeesAdapter(this, entries);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
