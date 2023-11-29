package com.example.buahku.Interface;

import com.example.buahku.Api.TipsData;
import com.example.buahku.Models.ProdukModels;
import com.example.buahku.Models.TipsModels;

import java.util.ArrayList;

public interface TipsListener {
    void onTipsDataListener(ArrayList<TipsModels> data, String message);
}
