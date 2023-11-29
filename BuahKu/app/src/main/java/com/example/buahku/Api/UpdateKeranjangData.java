package com.example.buahku.Api;

import android.os.AsyncTask;

import java.io.IOException;

public class UpdateKeranjangData extends AsyncTask<Void, Void, Void> {
    private String id;
    private int qty;
    private int subtotal;

    public UpdateKeranjangData(String id, int qty, int subtotal) {
        this.id = id;
        this.qty = qty;
        this.subtotal = subtotal;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            ApiClient apiClient = new ApiClient();
            apiClient.updateKeranjang(id, qty, subtotal);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
