package com.example.secgetir;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

public class OrderAcivity extends AppCompatActivity {
Button bck, by;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_main);
        String baseURL = "http://secgetir.live";
        SharedPreferences sp = getSharedPreferences("secGetirAyarlar",MODE_WORLD_READABLE);
        String userid = sp.getString("userid","");
        Intent intt=getIntent();
        Bundle b=intt.getExtras();

        if(b==null)
            return;
        String urunno =(String) b.get("urunno");
        Toast.makeText(this,urunno,Toast.LENGTH_LONG).show();
        TextView u_adi=findViewById(R.id.urun_adi);
        TextView u_maliyeti=findViewById(R.id.urun_maliyeti);
        TextView m_adi=findViewById(R.id.musteri_adi);
        ImageView img=findViewById(R.id.img2);
        new GetRequest(resultStr->{
            try {
                JSONObject result =  new JSONArray(resultStr).getJSONObject(0);
                String mus_adi = (String)result.get("musteri_adi");
                String urun_adi = (String)result.get("urun_adi");
                String urun_maliyeti = (String)result.get("urun_maliyeti");
                String urun_resmi=(String)result.get("urun_resmi");


                m_adi.setText("Müsteri Adı: "+mus_adi);
                u_adi.setText("Ürün Adı: "+urun_adi);
                u_maliyeti.setText("Ürünün Fiyatı : "+urun_maliyeti);
                 new DownloadImageTask(img).execute(baseURL+urun_resmi);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }).execute(baseURL + "/admin/api/fatura/info.php?u_no="+urunno+"&m_no="+userid);

        bck=findViewById(R.id.backk);
        bck.setOnClickListener(view ->{
            Intent bk=new Intent(OrderAcivity.this, CategoryyActivity.class);
            startActivity(bk);
        });

        EditText mail=findViewById(R.id.edit_mail);
        EditText il=findViewById(R.id.edit_il);
        EditText ilce=findViewById(R.id.edit_ilce);
        EditText koy=findViewById(R.id.edit_koy);
        EditText krt=findViewById(R.id.edit_krt_no);
        EditText adet=findViewById(R.id.edit_adet);

        by=findViewById(R.id.buyy);
        by.setOnClickListener(view ->{
            String params = "?u_no="+urunno +
                    "&m_no=" + userid +
                    "&il=" + il.getText().toString() +
                    "&ilce=" + ilce.getText().toString()+
                    "&koy=" + koy.getText().toString()+
                    "&kart_no=" + krt.getText().toString() +
                    "&email=" + mail.getText().toString() +
                    "&u_adedi=" + adet.getText().toString();

             new GetRequest(result->{
                 Intent byy=new Intent(OrderAcivity.this,MailcheckAcivity.class);
                 byy.putExtra("faturaid",result);
                 Log.e("sonuç",result);
                 startActivity(byy);
             }).execute(baseURL + "/admin/api/fatura/fatura-ekle.php"+params);

        });
    }
}