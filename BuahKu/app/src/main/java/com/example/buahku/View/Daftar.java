package com.example.buahku.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.buahku.R;
import com.example.buahku.helper.HashPassword;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Daftar extends AppCompatActivity {

    EditText user, pass1, pass2, nama, email, telepon, alamat;
    Button btndftr;

    private final String endpointdaftar = "https://asia-south1.gcp.data.mongodb-api.com/app/admin-ukadi/endpoint/daftarPengguna";

    private final String endpointcek = "https://asia-south1.gcp.data.mongodb-api.com/app/admin-ukadi/endpoint/getPenggunaByUsername";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        user = findViewById(R.id.user);
        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        telepon = findViewById(R.id.telepon);
        alamat = findViewById(R.id.alamat);
        pass1 = findViewById(R.id.password);
        pass2 = findViewById(R.id.password2);
        btndftr = findViewById(R.id.daftar);

        btndftr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "";
                String usernameKey = user.getText().toString();
                String namaKey = nama.getText().toString();
                String emailKey = email.getText().toString();
                String teleponKey = telepon.getText().toString();
                String alamatkey = alamat.getText().toString();
                String password1Key = pass1.getText().toString();
                String password2Key = pass2.getText().toString();
                String password = HashPassword.crypt(password2Key, "login");

                if (usernameKey.equals("") || namaKey.equals("") || emailKey.equals("") || teleponKey.equals("") || alamatkey.equals("") || password1Key.equals("") || password2Key.equals("")){
                    Toast.makeText(Daftar.this, "Lengkapi Data Anda!", Toast.LENGTH_SHORT).show();
                }else {
                    if(password1Key.equals(password2Key)){
                        url = endpointcek + "?username=" + usernameKey;
//                    Toast.makeText(Login.this, url, Toast.LENGTH_SHORT).show();
                        StringRequest stringrequest = new StringRequest(
                                Request.Method.GET,
                                url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
//                                    Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                                        try {
                                            JSONArray hasil = new JSONArray(response);
                                            if (hasil.length() == 0) {
//                                                Toast.makeText(Daftar.this, "Bisa", Toast.LENGTH_SHORT).show();
                                                String urlEndPoints = endpointdaftar +
                                                        "?username=" + usernameKey +
                                                        "&nama=" + namaKey +
                                                        "&telepon=" + teleponKey +
                                                        "&email=" + emailKey +
                                                        "&alamat=" + alamatkey +
                                                        "&password=" + password;

                                                StringRequest sr = new StringRequest(
                                                        Request.Method.POST,
                                                        urlEndPoints,
                                                        new Response.Listener<String>() {
                                                            @Override
                                                            public void onResponse(String response) {
                                                                try {
                                                                    JSONObject jsonResponse = new JSONObject(response);
                                                                    if (jsonResponse.has("error")) {
                                                                        String errorMessage = jsonResponse.getString("error");
                                                                        Toast.makeText(Daftar.this, errorMessage, Toast.LENGTH_SHORT).show();
                                                                    } else {
                                                                        Toast.makeText(Daftar.this, "Berhasil Buat Akun", Toast.LENGTH_SHORT).show();
                                                                        Intent loginIntent = new Intent(Daftar.this, Login.class);
                                                                        startActivity(loginIntent);
                                                                    }
                                                                } catch (JSONException e) {
                                                                    e.printStackTrace();
                                                                    Toast.makeText(Daftar.this, "Gagal Buat Akun", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        },
                                                        new Response.ErrorListener() {
                                                            @Override
                                                            public void onErrorResponse(VolleyError error) {
                                                                Toast.makeText(Daftar.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                );
                                                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                requestQueue.add(sr);

                                            } else {
                                                Toast.makeText(Daftar.this, "Username Sudah Tersedia!", Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(Daftar.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                        RequestQueue requestqueue = Volley.newRequestQueue(getApplicationContext());
                        requestqueue.add(stringrequest);
                    }else{
                        Toast.makeText(Daftar.this, "Password Anda Berbeda!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void aksi_login(View v){
        Intent i;
        i = new Intent(this, Login.class);
        startActivity(i);
    }
}