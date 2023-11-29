package com.example.buahku.Interface;

import com.example.buahku.Models.ProdukModels;

import java.util.ArrayList;

public interface ProdukByIDListener {
    void onProdukDataReceived(ProdukModels produkModels, String message);
}
