package com.example.buahku.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.buahku.Adapter.RecyclerViewRekomendasi;
import com.example.buahku.Adapter.RecyclerViewTipsTrikBudaya;
import com.example.buahku.Api.ProduksData;
import com.example.buahku.Api.TipsData;
import com.example.buahku.Interface.ProdukListener;
import com.example.buahku.Interface.TipsListener;
import com.example.buahku.Models.ProdukModels;
import com.example.buahku.Models.TipsModels;
import com.example.buahku.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements ProdukListener, TipsListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerViewRekomendasi;
    private RecyclerView recyclerViewTips;
    private RecyclerViewTipsTrikBudaya adapterTips;
    private LinearLayoutManager linearLayoutManagerTips;

    private RecyclerViewRekomendasi adapterRekomendasi;
    private Button buttonRekomendasi;

    private LinearLayoutManager linearLayoutManagerRekomendasi;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerViewRekomendasi = view.findViewById(R.id.recyclerRekomendasi);
        ProduksData produksData = new ProduksData(this);
        produksData.execute();

        recyclerViewTips = view.findViewById(R.id.recyclerBudidaya);
        TipsData tipsData = new TipsData(this);
        tipsData.execute();

        Button btnShopSelengkapnya = view.findViewById(R.id.rekomendasiSelengkapnya);
        btnShopSelengkapnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toShop();
            }
        });

        Button btnKeranjang = view.findViewById(R.id.keranjangButton);
        btnKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toKeranjang();
            }
        });
        // Inflate the layout for this fragment
        Button buttonRekomendasi = view.findViewById(R.id.buttonRekomendasi);
        buttonRekomendasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RekomendasiActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }

    private void toShop() {
        Fragment newFragment = new ShopFragment();

        if (getContext() != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, newFragment)
                    .addToBackStack(null)
                    .commit();
            BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
            Menu menu = bottomNavigationView.getMenu();
            MenuItem menuItem = menu.findItem(R.id.nav_shop);

            menuItem.setChecked(true);
        }
    }

    private void toKeranjang() {
        Fragment newFragment = new KeranjangFragment();

        if (getContext() != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, newFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onProdukDataReceived(ArrayList<ProdukModels> produkModels, String message) {
        if ( isAdded() && getContext() != null) {
            if (message != null) {
                if (message.equals("Unable to resolve host \"asia-south1.gcp.data.mongodb-api.com\": No address associated with hostname")) {
                    Toast.makeText(getContext(), "Tolong hubungkan koneksi internet", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                }
            }
            linearLayoutManagerRekomendasi = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            adapterRekomendasi = new RecyclerViewRekomendasi(getContext(), produkModels);

            recyclerViewRekomendasi.setLayoutManager(linearLayoutManagerRekomendasi);
            recyclerViewRekomendasi.setAdapter(adapterRekomendasi);
        }
    }

    @Override
    public void onTipsDataListener(ArrayList<TipsModels> data, String message) {
        if ( isAdded() && getContext() != null) {
            if (message != null) {
                if (message.equals("Unable to resolve host \"asia-south1.gcp.data.mongodb-api.com\": No address associated with hostname")) {
                    Toast.makeText(getContext(), "Tolong hubungkan koneksi internet", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                }
            }
            linearLayoutManagerTips = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            adapterTips = new RecyclerViewTipsTrikBudaya(data, getContext());

            recyclerViewTips.setLayoutManager(linearLayoutManagerTips);
            recyclerViewTips.setAdapter(adapterTips);

        }
    }
}