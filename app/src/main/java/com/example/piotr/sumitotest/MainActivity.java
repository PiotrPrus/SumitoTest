package com.example.piotr.sumitotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    final OkHttpClient client = new OkHttpClient();

    ArrayList<String> namesArrayList = new ArrayList<>();

    public static ArrayList<String> getNames(String jsonData) throws JSONException {

        JSONArray jsonArray = new JSONArray(jsonData);
        final ArrayList<String> clientName = new ArrayList<>();
        for (int n=0; n < jsonArray.length(); n++) {
            final JSONObject jsonObject = jsonArray.getJSONObject(n);

        clientName.add(jsonObject.getString("name"));
        }
        return clientName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            parseJsonData();

        Spinner clientListSpinner = (Spinner) findViewById(R.id.client_list_spinner);

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, namesArrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clientListSpinner.setAdapter(arrayAdapter);
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
                            namesArrayList.clear();
                            namesArrayList.addAll(getNames(jsonData));
                        } else {
                            Toast.makeText(getApplicationContext(), "There is an error. Sorry.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }catch (IOException | JSONException e) {
                        Log.e("Response", "Response" + response + "failed");
                    }
                }
            });

        }
}
