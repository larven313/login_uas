package com.example.login_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class hobbyActivity extends AppCompatActivity {

    private TextView txtJSON;
    private RequestQueue mQueue;
    private Button btnJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobby);

        mQueue = Volley.newRequestQueue(this);
        txtJSON = findViewById(R.id.txtJSON);
        btnJson = findViewById(R.id.btnJson);


    }
    private void hobby() {
        String url = "http://192.168.5.201/UAS/kegemaran.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("kegemaran");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject mahasantri = jsonArray.getJSONObject(i);
                                String nama = mahasantri.getString("nama");
                                String hobby = mahasantri.getString("kegemaran");

                                txtJSON.append(nama+", "+hobby+"\n\n");
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(hobbyActivity.this, "", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(hobbyActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        mQueue.add(request);
    }


}