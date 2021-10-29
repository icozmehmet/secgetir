package com.example.secgetir;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

public class ListorderAcivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listorder);
        String baseURL = "http://secgetir.live";
        SharedPreferences sp = getSharedPreferences("secGetirAyarlar",MODE_PRIVATE);
        String userid = sp.getString("userid","");
        LinearLayout container =findViewById(R.id.twtutucu);
        new GetRequest(resultStr->{
            JSONArray result = new JSONArray(resultStr);
            for (int i = 0;i<result.length();i++){
                JSONObject urun = result.getJSONObject(i);

                String faturaid = (String)urun.get("fatura_no");
                String urunadi = (String)urun.get("urun_adi");
                String fiyat = (String)urun.get("urun_maliyeti");


                TextView textView = new TextView(this);
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setPadding(5,5,5,5);
                textView.setTextSize(20);
                textView.setTypeface(Typeface.DEFAULT_BOLD);
                textView.setGravity(View.TEXT_ALIGNMENT_CENTER);

                textView.setTag(faturaid);
                textView.setText(urunadi + ": " + fiyat);
                container.addView(textView);

                textView.setOnClickListener(view -> {
                    Intent bk=new Intent(ListorderAcivity.this, MyorderAcivity.class);
                    String tag = (String) view.getTag();
                    bk.putExtra("faturaid",tag);
                    startActivity(bk);
                });
            }

        }).execute(baseURL + "/admin/api/fatura/orders.php?id="+userid);
        Button geri=findViewById(R.id.gerrii);
        geri.setOnClickListener(view -> {
            Intent gr=new Intent(ListorderAcivity.this,LoginActivity.class);
            startActivity(gr);
        });
    }
}