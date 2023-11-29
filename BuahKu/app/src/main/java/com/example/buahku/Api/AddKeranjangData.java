package com.example.buahku.Api;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

//String idProduk, String namaProduk, int harga, int qty, int subtotal, String username
public class AddKeranjangData extends AsyncTask<Void, Void, Void> {
    private String idProduk, namaProduk, username;
    private int harga, qty, subtotal;

    public AddKeranjangData(String idProduk, String namaProduk, String username, int harga, int qty, int subtotal) {
        this.idProduk = idProduk;
        this.namaProduk = namaProduk;
        this.username = username;
        this.harga = harga;
        this.qty = qty;
        this.subtotal = subtotal;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            ApiClient apiClient = new ApiClient();
            apiClient.addKeranjang(
                    idProduk, namaProduk, harga, qty, subtotal, username
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
    }
}
