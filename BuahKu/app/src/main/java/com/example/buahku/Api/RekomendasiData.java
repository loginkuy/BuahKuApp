package com.example.buahku.Api;

import android.os.AsyncTask;

import com.example.buahku.Interface.ProdukListener;
import com.example.buahku.Models.ProdukModels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class RekomendasiData extends AsyncTask<Void, Void, ArrayList<ProdukModels>> {

    private ArrayList<ProdukModels> produkModelsArrayList;
    private ProdukListener listener;
    private String message;
    private String suhu;
    private String tanah;

    public RekomendasiData(ProdukListener listener, String suhu, String tanah) {
        this.listener = listener;
        this.suhu = suhu;
        this.tanah = tanah;
    }
    @Override
    protected ArrayList<ProdukModels> doInBackground(Void... voids) {
        try {
            ApiClient apiClient = new ApiClient();
            String response = apiClient.getRekomendasi(suhu, tanah);
            JSONArray jsonArrayData = new JSONArray(response);
            produkModelsArrayList = new ArrayList<>();

            if (jsonArrayData.length() == 0) {
                message = "Mohon Maaf, Tidak Ada Rekomendasi Budidaya Yang Cocok Di Kriteria Tersebut";
                return null;
            }

            for (int i = 0; i < jsonArrayData.length(); i++) {
                JSONObject data = new JSONObject(jsonArrayData.getString(i));
                produkModelsArrayList.add(i, new ProdukModels(
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
                ));
            }
            return produkModelsArrayList;
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
    protected void onPostExecute(ArrayList<ProdukModels> produkModels) {
        super.onPostExecute(produkModels);

        if (listener != null) {
            listener.onProdukDataReceived(produkModels, this.message);
        }
    }
}
