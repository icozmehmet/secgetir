package com.example.secgetir;

import org.json.JSONException;
import org.json.JSONObject;

public interface Responsable {
    void onResponse(String result) throws JSONException;
}
