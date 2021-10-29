package com.example.secgetir;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager2.widget.ViewPager2;

public class LoginActivity extends AppCompatActivity{
CardView urun1,urun2,urun3,urun4;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        ViewFlipper flipper=findViewById(R.id.flip);

        urun1=findViewById(R.id.giyim);
        urun2=findViewById(R.id.yiyecek);
        urun3=findViewById(R.id.icecek);
        urun4=findViewById(R.id.teknoloji);

        urun1.setOnClickListener(view -> {
            Intent git=new Intent(LoginActivity.this,ClothesActivity.class);
            startActivity(git);
        });

        urun2.setOnClickListener(view -> {
            Intent git=new Intent(LoginActivity.this,FoodActivity.class);
            startActivity(git);
        });

        urun3.setOnClickListener(view -> {
            Intent git=new Intent(LoginActivity.this,DrinksActivity.class);
            startActivity(git);
        });

        urun4.setOnClickListener(view -> {
            Intent git=new Intent(LoginActivity.this,TecActivity.class);
            startActivity(git);
        });
        flipper.setAutoStart(true);

        Button ctgr= findViewById(R.id.category);
        ctgr.setOnClickListener(view -> {
            Intent ger=new Intent(LoginActivity.this,CategoryyActivity.class);
            startActivity(ger);
        });
        Button sipras=findViewById(R.id.siparis);
        sipras.setOnClickListener(view -> {
            Intent spr=new Intent(LoginActivity.this,ListorderAcivity.class);
            startActivity(spr);
        });
    }
}
