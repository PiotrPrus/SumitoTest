package com.example.piotr.sumitotest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TEST_URL = "http://officewise.sumito.uk:8081/admin/login?login=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void openMainActivity(View view) throws Exception {

        EditText logET = (EditText) findViewById(R.id.username_text_view);
        EditText passET = (EditText) findViewById(R.id.password_text_view);

        String login = logET.getText().toString();
        String password = passET.getText().toString();

        run(login, password);

    }

    private final OkHttpClient client = new OkHttpClient();

    public void run(String string1, String string2) throws Exception {

        Request request = new Request.Builder()
                .url(TEST_URL+string1+"&password="+string2)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(LoginActivity.this, "Username or password are not correct",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}


