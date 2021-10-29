package com.example.secgetir;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

public class MailcheckAcivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mailcheck_main);
        String baseURL = "http://secgetir.live";

        Intent innt=getIntent();
        Bundle bu=innt.getExtras();

        if(bu==null)
            return;

        String maill=innt.getStringExtra("mail");
        TextView mal=findViewById(R.id.mail);
        mal.setText(maill);

        Intent intt=getIntent();
        Bundle b=intt.getExtras();

        if(b==null)
            return;

        String faturaid = intt.getStringExtra("faturaid");

        EditText cd=findViewById(R.id.edit_code);

        Button kntrl=findViewById(R.id.conrol);
        TextView y_n=findViewById(R.id.y_n);

        SharedPreferences sp = getSharedPreferences("secGetirAyarlar",MODE_WORLD_READABLE);
        String userid = sp.getString("userid","");

        new GetRequest(resultStr->{
            JSONObject result = new JSONObject(resultStr);
            String mail = (String) result.get("musteri_mail");
        }).execute(baseURL + "/admin/api/kullanici/info.php?userid = "+userid);

        kntrl.setOnClickListener(view->{
            new GetRequest(result->{
                Log.e("sonuç",result);
                y_n.setText(result);
                Log.e("sonuç",baseURL + "/admin/api/fatura/kontrol.php?id="+faturaid + "&code=" + cd.getText().toString());
            }).execute(baseURL + "/admin/api/fatura/kontrol.php?id="+faturaid + "&code=" + cd.getText().toString());
            Toast.makeText(this, "Siparişiniz Onaylandı!!!", Toast.LENGTH_SHORT).show();
            Intent geri=new Intent(MailcheckAcivity.this,LoginActivity.class);
            startActivity(geri);
        });



    }
}