package com.example.secgetir;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileAcivity extends AppCompatActivity {
Button back,update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_main);
        String baseURL = "http://secgetir.live";

        SharedPreferences sp = getSharedPreferences("secGetirAyarlar",MODE_WORLD_READABLE);
        String userid = sp.getString("userid","");
        EditText mad=findViewById(R.id.edit_adi);
        EditText mkad=findViewById(R.id.eddit_kadi);
        EditText msfre=findViewById(R.id.edit_pas);
        EditText mmail=findViewById(R.id.eddit_mail);

        new GetRequest(res->{
            JSONObject result = null;
            try {
                result = new JSONObject(res);
                String m_adi = (String) result.get("musteri_adi");
                String m_kadi = (String) result.get("musteri_kadi");
                String m_sifre = (String) result.get("musteri_sifre");
                String m_mail = (String) result.get("musteri_mail");
                mad.setText(m_adi);
                mkad.setText(m_kadi);
                msfre.setText(m_sifre);
                mmail.setText(m_mail);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.e("cvp",res);
        }).execute(baseURL + "/admin/api/kullanici/info.php?userid="+userid);

        back=findViewById(R.id.gerii);
        back.setOnClickListener(view -> {
            Intent bck=new Intent(ProfileAcivity.this, CategoryyActivity.class);
            startActivity(bck);
        });

        update=findViewById(R.id.Guncelle);
        update.setOnClickListener(view->{
            if (mad.getText().toString().isEmpty()|| msfre.getText().toString().isEmpty() || mmail.getText().toString().isEmpty()||
                mkad.getText().toString().isEmpty())
            {
            Toast.makeText(this, "BOSLUKLARI DOLDURUN!!!", Toast.LENGTH_SHORT).show();
            return;
        }
            if(mmail.getText().toString().contains("@")&& mmail.getText().toString().contains(".com")) {
                if (msfre.getText().toString().length() < 7)
                    Toast.makeText(this, "Şifre 7 Karakterden Kücük Olamaz!!!", Toast.LENGTH_SHORT).show();
                else {
                    String params =
                            "?userid="+userid +
                                "&kadi=" + mkad.getText().toString() +
                                "&sifre=" + msfre.getText().toString() +
                                "&ad=" + mad.getText().toString() +
                                "&email=" + mmail.getText().toString();

                    new GetRequest(result -> {
                        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                    }).execute(baseURL + "/admin/api/kullanici/kaydol.php" + params);
                }
            }
            else
                Toast.makeText(this, "Geçerli Bir Mail Adresi Giriniz!!!!", Toast.LENGTH_SHORT).show();
        });
    }
}