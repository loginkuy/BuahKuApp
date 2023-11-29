package com.example.buahku.Api;

import android.os.AsyncTask;
import android.util.Log;

import com.example.buahku.Interface.KeranjangListener;
import com.example.buahku.Models.KeranjangModels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class KeranjangData extends AsyncTask<Void, Void, ArrayList<KeranjangModels>> {
    private ArrayList<KeranjangModels> keranjangModelsArrayList;
    private KeranjangListener listener;
    private String username;
    private String message;

    public KeranjangData(KeranjangListener listener, String username) {
        this.listener = listener;
        this.username = username;
    }

    @Override
    protected ArrayList<KeranjangModels> doInBackground(Void... voids) {
        try {
            ApiClient apiClient = new ApiClient();
            String response = apiClient.getKeranjang(username);
            JSONArray jsonArray = new JSONArray(response);
            keranjangModelsArrayList = new ArrayList<>();
            if (jsonArray.length() == 0) {
                message = "Keranjang kosong";
                return null;
            }

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject data = new JSONObject(jsonArray.getString(i));
                JSONObject produk = data.getJSONObject("produk");
                keranjangModelsArrayList.add(i, new KeranjangModels(
                        data.getString("_id"),
                        data.getString("nama_produk"),
                        data.getInt("harga"),
                        data.getInt("qty"),
                        data.getInt("subtotal"),
                        produk.getString("foto")
                ));
            }
            return keranjangModelsArrayList;
        } catch (IOException e) {
            e.printStackTrace();
            message = e.getMessage();
        } catch (JSONException e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<KeranjangModels> keranjangModels) {
        super.onPostExecute(keranjangModels);

        if (listener != null) {
            listener.onProdukDataReceived(keranjangModels, this.message);
        }
    }
}
