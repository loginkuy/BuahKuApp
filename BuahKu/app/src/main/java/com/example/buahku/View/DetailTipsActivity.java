package com.example.buahku.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.buahku.Api.TipsByIdData;
import com.example.buahku.Api.TipsData;
import com.example.buahku.Interface.TipsByIDListener;
import com.example.buahku.Models.TipsModels;
import com.example.buahku.R;

public class DetailTipsActivity extends AppCompatActivity implements TipsByIDListener {
    private String id;
    TextView judul, penulis, isi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tips);

        judul = findViewById(R.id.judulTipsDetail);
        penulis = findViewById(R.id.authorTips);
        isi = findViewById(R.id.isiTipsDetails);

        this.id = getIntent().getStringExtra("id");
        TipsByIdData tips = new TipsByIdData(id, this);
        tips.execute();
    }

    @Override
    public void onTipsDataReceived(TipsModels data, String message) {
        judul.setText(data.getJudul());
        penulis.setText(data.getPenulis());
        CharSequence isiText = Html.fromHtml(data.getIsi());
        isi.setText(isiText);
    }
}