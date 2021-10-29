package com.example.secgetir;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewActivity extends AppCompatActivity{
    Button save;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);
        String baseURL = "http://secgetir.live";

        save=findViewById(R.id.saved);
        EditText edit_kadi = findViewById(R.id.edit_kadi);
        EditText edit_sifre = findViewById(R.id.edit_pass);
        EditText edit_ad = findViewById(R.id.edit_name);
        EditText edit_mail = findViewById(R.id.edit_eposta);
        save.setOnClickListener(view -> {
            if (edit_ad.getText().toString().isEmpty()|| edit_sifre.getText().toString().isEmpty() || edit_mail.getText().toString().isEmpty()||
                    edit_kadi.getText().toString().isEmpty()) {
                Toast.makeText(this, "BOSLUKLARI DOLDURUN!!!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(edit_mail.getText().toString().contains("@")&& edit_mail.getText().toString().contains(".com")) {
                if (edit_sifre.getText().toString().length() < 7)
                    Toast.makeText(this, "Şifre 7 Karakterden Kücük Olamaz!!!", Toast.LENGTH_SHORT).show();
                else {
                    String params =
                            "kadi=" + edit_kadi.getText().toString() + "&" +
                                    "sifre=" + edit_sifre.getText().toString() + "&" +
                                    "ad=" + edit_ad.getText().toString() + "&" +
                                    "email=" + edit_mail.getText().toString();
                    new GetRequest(result -> {
                        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                        SharedPreferences sp = getSharedPreferences("secGetirAyarlar",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("userid",result);
                        editor.commit();
                    }).execute(baseURL + "/admin/api/kullanici/kaydol.php?" + params);

                    Intent gir = new Intent(NewActivity.this, LoginActivity.class);
                    startActivity(gir);

                }
            }
            else
                Toast.makeText(this, "Geçerli Bir Mail Adresi Giriniz!!!", Toast.LENGTH_SHORT).show();
            });
            }

    }

