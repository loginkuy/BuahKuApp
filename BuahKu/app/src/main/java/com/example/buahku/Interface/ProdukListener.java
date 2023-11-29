package com.example.buahku.Interface;

import com.example.buahku.Models.ProdukModels;

import java.util.ArrayList;

public interface ProdukListener {
    void onProdukDataReceived(ArrayList<ProdukModels> produkModels, String message);
}
