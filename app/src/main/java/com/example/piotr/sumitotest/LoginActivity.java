package com.example.piotr.sumitotest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void openMainActivity(View view) {

        EditText logET = (EditText) findViewById(R.id.username_text_view);
        EditText passET = (EditText) findViewById(R.id.password_text_view);

        if (logET.getText().toString().equals("Sumito") && passET.getText().toString().equals("lubieplacki")) {
            //TODO open the Main activity after click the button
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            //TODO Toast the message that username or password are incorrect
            Toast.makeText(LoginActivity.this, "Username or password are not correct", Toast.LENGTH_LONG).show();
        }

    }
}


