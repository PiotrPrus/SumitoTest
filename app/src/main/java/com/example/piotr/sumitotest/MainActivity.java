package com.example.piotr.sumitotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String CLIENT_URL = "http://officewise.sumito.uk:8081/admin/clients";
    private static final String PROJECT_URL = "http://officewise.sumito.uk:8081/admin/projects?clientId=13";

    final OkHttpClient client = new OkHttpClient();

    ArrayList<String> clientArrayList = new ArrayList<>();

    public static ArrayList<String> getNames(String jsonData) throws JSONException {

        JSONArray jsonArray = new JSONArray(jsonData);
        final ArrayList<String> clientName = new ArrayList<>();
        for (int n=0; n < jsonArray.length(); n++) {
            final JSONObject jsonObject = jsonArray.getJSONObject(n);

        clientName.add(jsonObject.getString("name"));
        }
        return clientName;
    }

    ArrayList<Integer> idArrayList = new ArrayList<>();

    public static ArrayList<String> getProject(String projectData) throws JSONException {

        JSONArray jsonArray = new JSONArray(projectData);
        final ArrayList<String> project = new ArrayList<>();
        for (int n=0; n < jsonArray.length(); n++) {
            final JSONObject jsonObject = jsonArray.getJSONObject(n);

            project.add(jsonObject.getString("name"));
        }
        return project;
    }

    ArrayList<String> projectArrayList = new ArrayList<>();

    public static ArrayList<Integer> getId(String jsonData) throws JSONException {

        JSONArray jsonArray = new JSONArray(jsonData);
        final ArrayList<Integer> clientId = new ArrayList<>();
        for (int n=0; n < jsonArray.length(); n++) {
            final JSONObject jsonObject = jsonArray.getJSONObject(n);

            clientId.add(jsonObject.getInt("id"));
        }
        return clientId;
    }

    Spinner clientListSpinner;
    Spinner projectListSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clientListSpinner = (Spinner) findViewById(R.id.client_list_spinner);
        projectListSpinner = (Spinner) findViewById(R.id.project_list_spinner);

            parseJsonData();
            parseProjectData();

    }

        public void parseJsonData() {
            final Request request = new Request.Builder()
                    .url(CLIENT_URL)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("CALLBACK", "Request " + request + " failed");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try{
                        if (response.isSuccessful()) {
                            final String jsonData = response.body().string();
                            clientArrayList.clear();
                            clientArrayList.addAll(getNames(jsonData));
                            idArrayList.clear();
                            idArrayList.addAll(getId(jsonData));
                        } else {
                            Toast.makeText(getApplicationContext(), "There is an error. Sorry.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }catch (IOException | JSONException e) {
                        Log.e("Response", "Response" + response + "failed");
                    }
                }
            });

            callClientSpinner();
        }

    public void parseProjectData() {

        final Request request = new Request.Builder()
                .url(PROJECT_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("CALLBACK", "Request " + request + " failed");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    if (response.isSuccessful()) {
                        final String projectData = response.body().string();
                        projectArrayList.clear();
                        projectArrayList.addAll(getProject(projectData));
                    } else {
                        Toast.makeText(getApplicationContext(), "There is an error. Sorry.",
                                Toast.LENGTH_SHORT).show();
                    }
                }catch (IOException | JSONException e) {
                    Log.e("Response", "Response" + response + "failed");
                }

            }
        });

        callProjectSpinner();
    }


    private void callClientSpinner() {

        ArrayAdapter clientArrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, clientArrayList);
        clientArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clientListSpinner.setAdapter(clientArrayAdapter);
        clientListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            //TODO This method is not working at ALL. It need to be checked later on.
                // After clicking on spinner no1 the second one will turn active . Additionally the Id will be send to build proper URL.
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //dummy
            }
        });

    }

    private void callProjectSpinner() {

        ArrayAdapter projectArrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, projectArrayList);
        projectArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        projectListSpinner.setAdapter(projectArrayAdapter);

    }

}
