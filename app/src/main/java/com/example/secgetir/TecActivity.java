package com.example.secgetir;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class TecActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.technology_activity);
        LinearLayout cont=findViewById(R.id.ttutucu);
        String baseURL = "http://secgetir.live";
        Button gr=findViewById(R.id.grr);
        Responsable response = resultStr -> {
            try {
                JSONArray result = new JSONArray(resultStr);
                for (int i = 0;i<result.length();i++) {
                    JSONObject urun = result.getJSONObject(i);
                    String kategori = (String) urun.get("urun_turu");
                    if (!kategori.equals("Teknoloji"))//Kategori kontrolü
                        continue;
                    String resim = (String) urun.get("urun_resmi");
                    String isim = (String) urun.get("urun_adi");

                    ImageView imageView = new ImageView(this);
                    imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    imageView.setPadding(5,50,50,5);
                    new DownloadImageTask(imageView).execute(baseURL + resim);
                    cont.addView(imageView);

                    TextView textView=new TextView(this);
                    textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                    textView.setPadding(5,5,5,5);
                    textView.setTextSize(20);
                    textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    textView.setTypeface(Typeface.DEFAULT_BOLD);
                    textView.setText("Ürün Adı= "+isim);
                    cont.addView(textView);


                    Button button=new Button(this);
                    button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
                    button.setOnClickListener(view -> {
                        Intent bk=new Intent(TecActivity.this, DetailAcivity.class);
                        bk.putExtra("urunno",(String)view.getTag());
                        startActivity(bk);
                    });
                    button.setPadding(5,5,5,5);
                    button.setBackgroundColor(ContextCompat.getColor(getApplicationContext (),R.color.buttoncolor));
                    button.setText("Ürün Detayları");
                    cont.addView(button);
                    button.setTag((urun.get("urun_no")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        };new GetRequest(response).execute(baseURL + "/admin/api/urun/urun-goster.php");

        gr.setOnClickListener(view -> {
            Intent gri=new Intent(TecActivity.this, CategoryyActivity.class);
            startActivity(gri);
        });
    }
}
