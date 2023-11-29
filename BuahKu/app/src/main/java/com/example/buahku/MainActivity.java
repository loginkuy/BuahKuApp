package com.example.buahku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.buahku.View.Beranda;
import com.example.buahku.View.Login;

public class MainActivity extends AppCompatActivity {
    private int waktu_loading=4000;

    SharedPreferences user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = getApplicationContext().getSharedPreferences("user",0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String hasil = user.getString("username",null);
                if (hasil != null){
                    Intent i = new Intent(MainActivity.this, Beranda.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Intent i = new Intent(MainActivity.this, Login.class);
                    startActivity(i);
                    finish();
                }
            }
        },waktu_loading);
    }
}