package com.example.secgetir;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

public class MyorderAcivity extends AppCompatActivity {
TextView u_adi,u_adedi,u_maliyeti,t_adresi,u_durumu,musterii;
ImageView u_resim;
Button bac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myorder_main);
        String baseURL = "http://secgetir.live";

        Intent intt=getIntent();
        Bundle b=intt.getExtras();

        if(b==null)
            return;
        String faturaid =(String) b.get("faturaid");
        u_adi=findViewById(R.id.urunn_adi);
        u_maliyeti=findViewById(R.id.urun_nmaliyeti);
        musterii=findViewById(R.id.musteri);
        u_resim=findViewById(R.id.img22);
        u_adedi=findViewById(R.id.urunn_adedi);
        t_adresi=findViewById(R.id.teslimat_adresi);
        u_durumu=findViewById(R.id.urun_durumu);
        bac=findViewById(R.id.geriii);
        new GetRequest(resultStr->{
            try {
                JSONObject result = new JSONObject(resultStr);
                String mus_adi = (String)result.get("musteri_adi");
                String urun_adi = (String)result.get("urun_adi");
                String urun_maliyeti = (String)result.get("urun_maliyeti");
                String urun_resmi=(String)result.get("urun_resmi");
                String u_adedii=(String)result.get("urun_adedi");
                String il=(String)result.get("il");
                String ilce=(String)result.get("ilce");
                String koy=(String)result.get("koy");
                String u_durum=(String)result.get("durum");

                Log.e("musteri adı:",mus_adi);
                Log.e("urun_adi:",urun_adi);
                Log.e("urun_maliyeti",urun_maliyeti);
                Log.e("urun_resmi",urun_resmi);
                Log.e("u_adedii",u_adedii);
                Log.e("il",il);
                Log.e("ilce:",ilce);
                Log.e("koy",koy);
                Log.e("durum",u_durum);


                musterii.setText("Müsteri Adı: "+mus_adi);
                u_adi.setText("Ürün Adı: "+urun_adi);
                u_maliyeti.setText("Ürünün Fiyatı : "+urun_maliyeti);
                u_adedi.setText("Ürün Adedi: "+u_adedii);
                t_adresi.setText("İl: "+il+",   İlçe: "+ilce+",  Koy/Mahalle: "+koy);
                u_durumu.setText("Son Durumu: "+u_durum);
                new DownloadImageTask(u_resim).execute(baseURL+urun_resmi);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }).execute(baseURL + "/admin/api/fatura/info.php?id="+faturaid);

        bac.setOnClickListener(view -> {
            Intent geri=new Intent(MyorderAcivity.this,LoginActivity.class);
            startActivity(geri);
        });

    }
}