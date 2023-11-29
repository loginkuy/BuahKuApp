package com.example.buahku.Api;

import android.os.AsyncTask;

import com.example.buahku.Interface.TipsByIDListener;
import com.example.buahku.Models.TipsModels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class TipsByIdData extends AsyncTask<Void, Void, TipsModels> {
    private String id;
    private String message;
    private TipsByIDListener listener;
    private TipsModels dataTips;

    public TipsByIdData(String id, TipsByIDListener listener) {
        this.id = id;
        this.listener = listener;
    }

    @Override
    protected TipsModels doInBackground(Void... voids) {
        try {
            ApiClient apiClient = new ApiClient();
            String response = apiClient.getTipsByID(this.id);
            JSONArray jsonArray = new JSONArray(response);
            JSONObject data = new JSONObject(jsonArray.getString(0));
            dataTips = new TipsModels(
                    data.getString("_id"),
                    data.getString("tanggal"),
                    data.getString("judul"),
                    data.getString("isi"),
                    data.getString("penulis")
            );
            return dataTips;
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
    protected void onPostExecute(TipsModels tipsModels) {
        super.onPostExecute(tipsModels);
        if (listener != null) {
            listener.onTipsDataReceived(tipsModels, this.message);
        }
    }
}
