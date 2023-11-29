package com.example.buahku.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buahku.Adapter.RecyclerViewRekomendasi;
import com.example.buahku.Api.ProdukData;
import com.example.buahku.Interface.ProdukByIDListener;
import com.example.buahku.Models.ProdukModels;
import com.example.buahku.R;
import com.squareup.picasso.Picasso;

public class DetailProduk extends AppCompatActivity implements ProdukByIDListener {
    private TextView nama, namaLatin, deskripsi, gizi, khasiat;
    private ImageView image;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        nama = findViewById(R.id.namaDetailProduk);
        namaLatin =findViewById(R.id.namaLatinProduk);
        deskripsi = findViewById(R.id.detailDescriptionProduct);
        gizi = findViewById(R.id.detailGiziProduk);
        image = findViewById(R.id.imageDetailProduk);
        khasiat = findViewById(R.id.detailKhasiat);

        this.id = getIntent().getStringExtra("id");
        ProdukData produkData = new ProdukData(this.id, this);
        produkData.execute();
    }

    @Override
    public void onProdukDataReceived(ProdukModels produkModels, String message) {
        nama.setText(produkModels.getNama());
        namaLatin.setText(produkModels.getNamaLatin());
        deskripsi.setText(produkModels.getDeskripsi());
        khasiat.setText(produkModels.getKhasiat());
        CharSequence giziText = Html.fromHtml(produkModels.getGizi());
        gizi.setText(giziText);
        String imageUrl = ("https://web-buahku.pegelinux.my.id/assets/images/produk/"+produkModels.getFoto());
        Picasso.get().load(imageUrl).resize(512, 512).placeholder(R.drawable.logo).into(image);
    }
}