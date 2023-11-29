package com.example.buahku.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

public class Login extends AppCompatActivity {

    SharedPreferences sfnya;
    SharedPreferences.Editor editor;
    EditText user1, pass1;
    Button btnlgn;

    private final String endpoint = "https://asia-south1.gcp.data.mongodb-api.com/app/admin-ukadi/endpoint/getPenggunaByUsernamePassword";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user1 = findViewById(R.id.user);
        pass1 = findViewById(R.id.password);
        btnlgn = findViewById(R.id.login);

        sfnya = getApplicationContext().getSharedPreferences("user",0);
        editor = sfnya.edit();

        btnlgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "";
                String usernameKey = user1.getText().toString();
                String passwordKey = pass1.getText().toString();
                String password = HashPassword.crypt(passwordKey, "login");

                if (usernameKey.equals("") || passwordKey.equals("")){
                    Toast.makeText(Login.this, "Masukkan Username Dan Password Anda!", Toast.LENGTH_SHORT).show();
                }else {
                    url = endpoint+"?username="+usernameKey+"&password="+password;
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
                                        Log.d("TEST", "onResponse: "+ hasil + "password" + password);
                                        if (hasil.length() == 0){
                                            Toast.makeText(Login.this, "Username Atau Password Anda Salah", Toast.LENGTH_SHORT).show();
                                        }else {
                                            JSONObject data = hasil.getJSONObject(0);
                                            String namanya = data.getString("username");
                                            String nama = data.getString("nama");
                                            String email = data.getString("email");
                                            String alamat = data.getString("alamat");
                                            String telephone = data.getString("telepon");
//                                            Toast.makeText(Login.this, namanya, Toast.LENGTH_SHORT).show();
                                            editor.putString("username",namanya);
                                            editor.putString("nama", nama);
                                            editor.putString("email", email);
                                            editor.putString("alamat", alamat);
                                            editor.putString("telephone", telephone);
                                            editor.commit();
                                            Toast.makeText(getApplicationContext(), "LOGIN SUKSES", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Login.this, Beranda.class);
//                                            intent.putExtra("username",usernameKey);
                                            Login.this.startActivity(intent);
                                            finish();
                                        }
                                    }catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(Login.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
                    RequestQueue requestqueue = Volley.newRequestQueue(getApplicationContext());
                    requestqueue.add(stringrequest);
                }
            }
        });
    }

    public void aksi_daftar(View v){
        Intent i;
        i = new Intent(this, Daftar.class);
        startActivity(i);
    }
}