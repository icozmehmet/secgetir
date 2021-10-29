package com.example.secgetir;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class DetailAcivity extends AppCompatActivity {
    Button back,by;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_main);
        String baseURL = "http://secgetir.live";

        Intent intt=getIntent();
        Bundle b=intt.getExtras();

        if(b==null)
            return;

        String urunno =(String) b.get("urunno");

        new GetRequest(resultStr -> {

            JSONObject result =  new JSONArray(resultStr).getJSONObject(0);
            for (int i = 0;i<result.length();i++) {
                String isim = (String) result.get("urun_adi");
                String fiyat = (String) result.get("urun_maliyeti");
                String resim = (String) result.get("urun_resmi");

                ImageView imgg = findViewById(R.id.img);
                new DownloadImageTask(imgg).execute(baseURL + resim);

                TextView uad = findViewById(R.id.u_adi);
                uad.setText("Ürün Adı:" + isim);

                TextView ufiyat = findViewById(R.id.u_fiyati);
                ufiyat.setText("Ürünün Fiyatı:" + fiyat);
            }
        }).execute(baseURL + "/admin/api/urun/urun-goster.php?u_id=" + urunno);
        back=findViewById(R.id.bck);
        back.setOnClickListener(view -> {
            Intent bk=new Intent(DetailAcivity.this, CategoryyActivity.class);
            startActivity(bk);
        });

        by=findViewById(R.id.buy);
        by.setOnClickListener(view ->{
            Intent buyy=new Intent(DetailAcivity.this,OrderAcivity.class);
            buyy.putExtra("urunno",urunno);
            startActivity(buyy);
        });
    }
}