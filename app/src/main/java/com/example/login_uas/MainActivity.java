
package com.example.login_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.login_uas.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView txtJSON;
    EditText etNama,etPassword;
    private String nama,password ;
    Button login;
    private RequestQueue mQueue;
    private String url = "http://192.168.5.201/UAS/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nama = password = "";
        etNama = findViewById(R.id.etNama);
        etPassword = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    public void login() {
        nama = etNama.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if (!nama.equals("") && !password.equals("")) {
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.contains("berhasil")) {
                        startActivity(new Intent(getApplicationContext(),hobbyActivity.class));
                    }else {
                        pesan("Nama atau password salah");
//                        pesan(response);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    pesan(error.toString().trim());
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("nama", nama);
                    data.put("password", password);

                    return data;
                }
            };
            Volley.newRequestQueue(this).add(request);
        } else {
            pesan("fields tidak boleh kosong");
        }


    }
    public void pesan(String isi) {
        Toast.makeText(this, isi, Toast.LENGTH_SHORT).show();
    }
}