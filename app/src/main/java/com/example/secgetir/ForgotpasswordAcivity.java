package com.example.secgetir;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotpasswordAcivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_main);

        String baseURL = "http://secgetir.live";

        //bir daha tanımlarsam kendimi sikicem
        EditText edit_mail = findViewById(R.id.edit_mail);

        Button go = findViewById(R.id.forgot_pass);
        go.setOnClickListener(view -> {
            String mail = edit_mail.getText().toString();
            new GetRequest(result -> {
                Log.e("res",result);

            }).execute(baseURL + "/admin/api/kullanici/forgot_pass.php?email="+mail);
        });
    }
}