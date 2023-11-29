package com.example.buahku.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buahku.Api.AddKeranjangData;
import com.example.buahku.Models.ProdukModels;
import com.example.buahku.R;
import com.example.buahku.View.DetailBudidayaActivity;
import com.example.buahku.View.DetailProduk;
import com.example.buahku.View.Login;
import com.example.buahku.helper.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewRek extends RecyclerView.Adapter<RecyclerViewRek.ViewHolder> {
    private ArrayList<ProdukModels> dataProduk;
    private Context context;
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        ImageView image;
        RelativeLayout parrent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.namaBudidaya);
            image = itemView.findViewById(R.id.imageBudidaya);
            parrent = itemView.findViewById(R.id.relativeLayoutBudidaya);
        }
    }

    public RecyclerViewRek(Context context, ArrayList<ProdukModels> dataProduk) {
        this.context = context;
        this.dataProduk = dataProduk;
    }

    @NonNull
    @Override
    public RecyclerViewRek.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_budidaya_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewRek.ViewHolder holder, int position) {

        TextView textName = holder.textName;
        ImageView image = holder.image;

        textName.setText(dataProduk.get(position).getNama());
        String h = Helper.numberWithCommas(dataProduk.get(position).getHarga());
        String imageUrl = ("https://web-buahku.pegelinux.my.id/assets/images/produk/"+dataProduk.get(position).getFoto());
        Picasso.get().load(imageUrl).resize(180, 180).placeholder(R.drawable.logo).into(image);

        holder.parrent.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailBudidayaActivity.class);
            intent.putExtra("id", dataProduk.get(position).getId());

            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        if (dataProduk == null) {
            return 0;
        }

        return dataProduk.size();
    }
}
