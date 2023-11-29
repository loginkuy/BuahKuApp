package com.example.buahku.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.buahku.Adapter.RecyclerViewRekomendasi;
import com.example.buahku.Api.ProduksData;
import com.example.buahku.Interface.ProdukListener;
import com.example.buahku.Models.ProdukModels;
import com.example.buahku.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopFragment extends Fragment implements ProdukListener {
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewManager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopFragment newInstance(String param1, String param2) {
        ShopFragment fragment = new ShopFragment();
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
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        recyclerView = view.findViewById(R.id.recyclerShop);
        recyclerView.setHasFixedSize(true);

        ProduksData produksData = new ProduksData(this);
        produksData.execute();

        Button btnKeranjang = view.findViewById(R.id.keranjangButton);
        btnKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toKeranjang();
            }
        });

        return view;
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
            GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
            recyclerView.setLayoutManager(manager);
            recyclerViewAdapter = new RecyclerViewRekomendasi(getContext(), produkModels);
            recyclerView.setAdapter(recyclerViewAdapter);
        }

    }
}