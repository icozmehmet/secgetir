package com.example.secgetir;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRequest extends AsyncTask<String,Integer,String> {
    private Responsable response;
    public GetRequest(Responsable response){
        this.response = response;
    }
    @Override
    protected String doInBackground(String[] urls) {
        for (int i = 0;i<urls.length;i++) {

            try {
                URL url = new URL(urls[i]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/json");
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                con.disconnect();
                return content.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            response.onResponse(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
