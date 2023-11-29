package com.example.buahku.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.buahku.Api.ProdukData;
import com.example.buahku.Interface.ProdukByIDListener;
import com.example.buahku.Models.ProdukModels;
import com.example.buahku.R;

public class DetailBudidayaActivity extends AppCompatActivity implements ProdukByIDListener {
    private TextView judul, deskripsi, namalatin, jenistanah, suhu, langkah;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_budidaya);
        judul = findViewById(R.id.judulBudidayaDetail);
        deskripsi = findViewById(R.id.deskripsiBudidayaDetail);
        namalatin = findViewById(R.id.namaLatinBudidayaDetail);
        jenistanah = findViewById(R.id.jenisTanahBudidayaDetail);
        suhu = findViewById(R.id.suhuBudidayaDetail);
        langkah = findViewById(R.id.langkahBudidayaDetail);

        this.id = getIntent().getStringExtra("id");
        ProdukData produkData = new ProdukData(this.id, this);
        produkData.execute();
    }

    @Override
    public void onProdukDataReceived(ProdukModels produkModels, String message) {
        judul.setText(produkModels.getNama());
        deskripsi.setText(produkModels.getDeskripsi());
        namalatin.setText("Nama Latin : "+produkModels.getNamaLatin());
        jenistanah.setText("Jenis Tanah Yang Tepat : "+produkModels.getTanah());
        suhu.setText("Suhu Yang Tepat : "+produkModels.getSuhu());
        CharSequence budidaya = Html.fromHtml(produkModels.getBudidaya());
        langkah.setText(budidaya);
    }
}