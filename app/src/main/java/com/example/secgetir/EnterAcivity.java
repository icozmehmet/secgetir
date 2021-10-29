package com.example.secgetir;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class EnterAcivity extends AppCompatActivity {
Button news,user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_main);

        news=findViewById(R.id.new_record);
        news.setOnClickListener(view -> {
            Intent trans=new Intent(EnterAcivity.this,NewActivity.class);
            startActivity(trans);
        });

        user=findViewById(R.id.users);
            user.setOnClickListener(view -> {
                Intent trans2=new Intent(EnterAcivity.this,UsersActivity.class);
                startActivity(trans2);
            });
    }
}