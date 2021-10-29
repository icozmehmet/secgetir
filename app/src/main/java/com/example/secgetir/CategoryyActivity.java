package com.example.secgetir;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CategoryyActivity extends AppCompatActivity{
    Button fd,tec,clot,drinks;
    ImageView profile,main;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoryy_main);

        fd=findViewById(R.id.food);
        fd.setOnClickListener(view ->{
            Intent food=new Intent(CategoryyActivity.this,FoodActivity.class);
            startActivity(food);

        });

        tec=findViewById(R.id.tecnlgy);
        tec.setOnClickListener(view ->{
            Intent tech=new Intent(CategoryyActivity.this,TecActivity.class);
            startActivity(tech);

        });
        drinks=findViewById(R.id.drink);
        drinks.setOnClickListener(view ->{
            Intent drnk=new Intent(CategoryyActivity.this,DrinksActivity.class);
            startActivity(drnk);

        });
        clot=findViewById(R.id.clothes);
        clot.setOnClickListener(view ->{
            Intent clth=new Intent(CategoryyActivity.this,ClothesActivity.class);
            startActivity(clth);

        });

        profile=findViewById(R.id.prfl);
        profile.setOnClickListener(view -> {
            Intent prf=new Intent(CategoryyActivity.this,ProfileAcivity.class);
            startActivity(prf);
        });
        main=findViewById(R.id.imageView);
        main.setOnClickListener(view -> {
            Intent man=new Intent(CategoryyActivity.this,LoginActivity.class);
            startActivity(man);
        });
    }
}
