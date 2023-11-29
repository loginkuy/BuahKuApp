package com.example.buahku.Api;

import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiClient {
    private static final String BASE_URL = "https://asia-south1.gcp.data.mongodb-api.com/app/admin-ukadi/endpoint";
    private static final OkHttpClient httpClient = new OkHttpClient();

    public static String getProdukTersedia() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "/getAllProduk")
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();
        String responseData = response.body().string();
        response.close();

        return responseData;
    }

    public static String getProdukByID(String id) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "/getProdukById?id="+id)
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();
        String responseData = response.body().string();
        response.close();

        return responseData;
    }

    public static void addKeranjang(String idProduk, String namaProduk, int harga, int qty, int subtotal, String username) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        String requestBody = String.format("");
        RequestBody body = RequestBody.create(mediaType, requestBody);
        Request request = new Request.Builder()
                .url(BASE_URL+"/addKeranjang?id_produk="+idProduk+"&produk="+namaProduk+"&harga="+harga+"&qty="+qty+"&subtotal="+subtotal+"&username="+username)
                .post(body)
                .build();
        Response response = httpClient.newCall(request).execute();
        response.close();
    }

    public static String getKeranjang(String username) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "/getKeranjangByUsername?username="+username)
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();
        String responseData = response.body().string();
        response.close();

        return responseData;
    }

    public  static void  deleteKeranjang(String id) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL+"/deleteProdukKeranjangById?id="+id)
                .delete()
                .build();
        Response response = httpClient.newCall(request).execute();
        response.close();
    }

    public static void updateKeranjang(String id, int qty, int subtotal) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        String requestBody = String.format("");
        RequestBody body = RequestBody.create(mediaType, requestBody);
        Request request = new Request.Builder()
                .url(BASE_URL+"/editProdukKeranjang?id="+id+"&qty="+qty+"&subtotal="+subtotal)
                .put(body)
                .build();
        Response response = httpClient.newCall(request).execute();
        response.close();
    }

    public static String getTips() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL+"/getAllTips")
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();
        String responseData = response.body().string();
        response.close();

        return responseData;
    }

    public static String getRekomendasi(String suhu, String tanah) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL+"/getRekomendasi?suhu="+suhu+"&tanah="+tanah)
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();
        String responseData = response.body().string();
        response.close();

        return responseData;
    }
    public static String getTipsByID(String id) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL+"/getTipsById?id="+id)
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();
        String responseData = response.body().string();
        response.close();

        return responseData;
    }
}
