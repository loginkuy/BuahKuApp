package com.example.buahku.Api;

import android.os.AsyncTask;

import com.example.buahku.Interface.ProdukByIDListener;
import com.example.buahku.Models.ProdukModels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ProdukData extends AsyncTask<Void, Void, ProdukModels> {
    private String id;
    private String message;

    private ProdukByIDListener listener;
    private ProdukModels produkModels;

    public ProdukData(String id, ProdukByIDListener listener) {
        this.id = id;
        this.listener = listener;
    }

    @Override
    protected ProdukModels doInBackground(Void... voids) {
        try {
            ApiClient apiClient = new ApiClient();
            String response = apiClient.getProdukByID(this.id);
            JSONArray jsonArrayData = new JSONArray(response);
            JSONObject data = new JSONObject(jsonArrayData.getString(0));
            produkModels = new ProdukModels(
                    data.getString("_id"),
                    data.getString("nama_produk"),
                    data.getInt("harga"),
                    data.getInt("stok"),
                    data.getString("nama_latin"),
                    data.getString("deskripsi"),
                    data.getString("khasiat"),
                    data.getString("budidaya"),
                    data.getString("tanah"),
                    data.getString("suhu"),
                    data.getString("foto"),
                    data.getString("gizi")
            );
            return produkModels;

        } catch (IOException e) {
            e.printStackTrace();
            this.message = e.getMessage();
        } catch (JSONException e) {
            e.printStackTrace();
            this.message = e.getMessage();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ProdukModels produkModels) {
        super.onPostExecute(produkModels);
        if (listener != null) {
            listener.onProdukDataReceived(produkModels, this.message);
        }
    }
}
