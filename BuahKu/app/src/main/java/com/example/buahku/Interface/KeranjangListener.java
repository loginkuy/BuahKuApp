package com.example.buahku.Interface;

import com.example.buahku.Models.KeranjangModels;

import java.util.ArrayList;

public interface KeranjangListener {
    void onProdukDataReceived(ArrayList<KeranjangModels> dataKeranjang, String message);
}
