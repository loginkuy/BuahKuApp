package com.example.buahku.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buahku.Models.TipsModels;
import com.example.buahku.R;
import com.example.buahku.View.DetailTipsActivity;

import java.util.ArrayList;

public class RecyclerViewTipsTrikBudaya extends RecyclerView.Adapter<RecyclerViewTipsTrikBudaya.ViewHolder>{
    private ArrayList<TipsModels> data;
    private Context context;
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        RelativeLayout parrent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.namaTIpsBudaya);
            parrent = itemView.findViewById(R.id.listTipsBudidaya);
        }
    }


    public RecyclerViewTipsTrikBudaya(ArrayList<TipsModels> data, Context context) {
        this.data = data;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerViewTipsTrikBudaya.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tips_trik_budaya, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView textName = holder.textName;
        if(!data.get(position).getJudul().isEmpty()){
            textName.setText(data.get(position).getJudul());
        }

        holder.parrent.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailTipsActivity.class);
            intent.putExtra("id", data.get(position).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }


}
