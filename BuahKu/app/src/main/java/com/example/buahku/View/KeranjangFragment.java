package com.example.buahku.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buahku.Adapter.RecyclerViewKeranjang;
import com.example.buahku.Adapter.RecyclerViewRekomendasi;
import com.example.buahku.Api.KeranjangData;
import com.example.buahku.Interface.KeranjangListener;
import com.example.buahku.Models.KeranjangModels;
import com.example.buahku.R;
import com.example.buahku.helper.Helper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KeranjangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KeranjangFragment extends Fragment implements KeranjangListener, RecyclerViewKeranjang.OnItemDeletedListener {
    RecyclerView recyclerView;
    RecyclerViewKeranjang recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewManager;
    TextView totalHargaKeranjang;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public KeranjangFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KeranjangFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KeranjangFragment newInstance(String param1, String param2) {
        KeranjangFragment fragment = new KeranjangFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_keranjang, container, false);
        recyclerView = view.findViewById(R.id.recycleKeranjang);
        recyclerView.setHasFixedSize(true);

        RecyclerView recyclerView = view.findViewById(R.id.recycleKeranjang);
        recyclerViewManager = new LinearLayoutManager(getContext());
        recyclerViewAdapter = new RecyclerViewKeranjang(getContext(), null);

        if (recyclerViewAdapter != null) {
            recyclerViewAdapter.setOnItemDeletedListener(this);
        }

        recyclerView.setLayoutManager(recyclerViewManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setOnItemDeletedListener(this);

        SharedPreferences user = getContext().getSharedPreferences("user", 0);
        String username = user.getString("username", null);

        if (username == "") {
            Toast.makeText(getContext(), "Anda belum login", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), Login.class);
            startActivity(intent);
        }

        KeranjangData keranjangData = new KeranjangData(this, username);
        keranjangData.execute();

        totalHargaKeranjang = view.findViewById(R.id.totalHargaKeranjang);

        return view;
    }

    @Override
    public void onProdukDataReceived(ArrayList<KeranjangModels> dataKeranjang, String message) {
        if ( isAdded() && getContext() != null) {
            if (message != null) {
                if (message.equals("Unable to resolve host \"asia-south1.gcp.data.mongodb-api.com\": No address associated with hostname")) {
                    Toast.makeText(getContext(), "Tolong hubungkan koneksi internet", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                }
            }
            if (recyclerViewManager == null || recyclerViewAdapter == null) {
                recyclerViewManager = new LinearLayoutManager(getContext());
                recyclerViewAdapter = new RecyclerViewKeranjang(getContext(), dataKeranjang);
                recyclerView.setLayoutManager(recyclerViewManager);
                recyclerView.setAdapter(recyclerViewAdapter);
            } else {
                recyclerViewAdapter.setData(dataKeranjang);
                recyclerViewAdapter.notifyDataSetChanged();
            }
            int currentHarga = 0;
            for (int i = 0; i < dataKeranjang.size(); i++) {
                currentHarga += dataKeranjang.get(i).getSubTotal();
            }
            String h = Helper.numberWithCommas(currentHarga);

            totalHargaKeranjang.setText("Rp"+h);
        }
    }

    @Override
    public void onItemDeleted(int position) {
        SharedPreferences user = getContext().getSharedPreferences("user", 0);
        String username = user.getString("username", null);

        if (username == "") {
            Toast.makeText(getContext(), "Anda belum login", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), Login.class);
            startActivity(intent);
        }
        KeranjangData keranjangData = new KeranjangData(this, username);
        keranjangData.execute();
    }
}