package com.example.buahku.Api;

import android.os.AsyncTask;

import com.example.buahku.Interface.TipsListener;
import com.example.buahku.Models.TipsModels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class TipsData  extends AsyncTask<Void, Void, ArrayList<TipsModels>> {
    private ArrayList<TipsModels> data;
    private String message;
    private TipsListener listener;

    public TipsData(TipsListener listener) {
        this.listener = listener;
    }

    @Override
    protected ArrayList<TipsModels> doInBackground(Void... voids) {
        try {
            ApiClient apiClient = new ApiClient();
            String response = apiClient.getTips();
            JSONArray jsonArray = new JSONArray(response);
            data = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject dat = new JSONObject(jsonArray.getString(i));
                data.add(i, new TipsModels(
                        dat.getString("_id"),
                        dat.getString("tanggal"),
                        dat.getString("judul"),
                        dat.getString("isi"),
                        dat.getString("penulis")
                ));
            }
            return data;

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
    protected void onPostExecute(ArrayList<TipsModels> tipsModels) {
        super.onPostExecute(tipsModels);

        if (listener != null) {
            listener.onTipsDataListener(data, this.message);
        }
    }
}
