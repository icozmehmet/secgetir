package com.example.secgetir;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UsersActivity extends AppCompatActivity{
    Button go;
    TextView uye,pass;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userss_activity);
        EditText kadi = findViewById(R.id.edit_kadi);
        EditText sifre = findViewById(R.id.edit_giris_sifre);
        String baseURL = "http://secgetir.live";

        go=findViewById(R.id.goo);
        go.setOnClickListener(view -> {
            String params = "k_adi="+kadi.getText().toString()+"&sifre="+sifre.getText().toString();

            new GetRequest(result->{
                Log.e("sonuc",result);
                Log.e("sonucparams",params);
                Toast.makeText(this,result,Toast.LENGTH_LONG).show();
                SharedPreferences sp = getSharedPreferences("secGetirAyarlar",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("userid",result);
                editor.commit();
            }).execute(baseURL+"/admin/api/kullanici/giris.php?"+params);


            Intent gooo=new Intent(UsersActivity.this, LoginActivity.class);
            startActivity(gooo);
            Toast.makeText(this, "Hosgeldin!!!", Toast.LENGTH_SHORT).show();
        });

        uye=findViewById(R.id.uyeol);
        uye.setOnClickListener(view -> {
            Intent gooo=new Intent(UsersActivity.this,NewActivity.class);
            startActivity(gooo);
        });
        pass=findViewById(R.id.sifreunuttum);
        pass.setOnClickListener(view -> {
            Intent gooo=new Intent(UsersActivity.this,ForgotpasswordAcivity.class);
            startActivity(gooo);
        });
    }
}
