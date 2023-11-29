package com.example.buahku.Api;

import android.os.AsyncTask;

import java.io.IOException;

public class DeleteKeranjangData extends AsyncTask<Void, Void, Void> {
    private String id;

    public DeleteKeranjangData(String id) {
        this.id = id;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            ApiClient apiClient = new ApiClient();
            apiClient.deleteKeranjang(this.id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
