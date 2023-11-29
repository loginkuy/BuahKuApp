package com.example.buahku.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.buahku.Adapter.RecyclerViewRek;
import com.example.buahku.Api.RekomendasiData;
import com.example.buahku.Interface.ProdukListener;
import com.example.buahku.Models.ProdukModels;
import com.example.buahku.R;

import java.util.ArrayList;

public class RekomendasiActivity extends AppCompatActivity {
    private String suhuValue;
    private String tanahValue;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recycleViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekomendasi);
        Spinner spinnerSuhu = findViewById(R.id.spinerSuhu);
        Spinner spinnerTanah = findViewById(R.id.spinerTanah);
        Button btnCari = findViewById(R.id.btnRekomendasi);
        recyclerView = findViewById(R.id.recyclerRekomendasi);
        recyclerView.setHasFixedSize(true);

        spinnerSuhu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                suhuValue = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerTanah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tanahValue = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RekomendasiData rekomendasiData = new RekomendasiData(new ProdukListener() {
                    @Override

                    public void onProdukDataReceived(ArrayList<ProdukModels> produkModels, String message) {
                        if (message != null) {
                            if (message.equals("Unable to resolve host \"asia-south1.gcp.data.mongodb-api.com\": No address associated with hostname")) {
                                Toast.makeText(RekomendasiActivity.this, "Tolong hubungkan koneksi internet", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RekomendasiActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
//                        recycleViewLayoutManager = new LinearLayoutManager(RekomendasiActivity.this);
                        GridLayoutManager manager = new GridLayoutManager(RekomendasiActivity.this, 2);
                        recyclerViewAdapter = new RecyclerViewRek(RekomendasiActivity.this, produkModels);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(recyclerViewAdapter);
                    }
                }, suhuValue, tanahValue);
                rekomendasiData.execute();
            }
        });

        ArrayList<String> suhuList = new ArrayList<>();
        suhuList.add("15 - 20");
        suhuList.add("21 - 25");
        suhuList.add("26 - 30");
        suhuList.add("31 - 35");

        ArrayAdapter<String> adapterSuhu = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, suhuList);
        adapterSuhu.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerSuhu.setAdapter(adapterSuhu);

        ArrayList<String> tanahList = new ArrayList<>();
        tanahList.add("Tanah Latosol");
        tanahList.add("Tanah Podsolik Merah Kuning");
        tanahList.add("Tanah Mediteran");
        tanahList.add("Tanah Aluvial");
        tanahList.add("Tanah Andosol");
        tanahList.add("Tanah Podsol");
        tanahList.add("Tanah Regosol");
        tanahList.add("Tanah Grumosol");
        tanahList.add("Tanah Rensina");
        tanahList.add("Tanah Gambut (Organol)");
        tanahList.add("Tanah Glei Humus");
        tanahList.add("Tanah Litosol");
        tanahList.add("Tanah Hidromorf Kelabu");
        tanahList.add("Tanah Planosol");

        ArrayAdapter<String> adapterTanah = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tanahList);
        adapterTanah.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerTanah.setAdapter(adapterTanah);

    }
}