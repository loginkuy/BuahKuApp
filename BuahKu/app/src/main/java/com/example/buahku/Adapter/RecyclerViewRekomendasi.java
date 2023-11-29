package com.example.buahku.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
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
import com.example.buahku.View.DetailProduk;
import com.example.buahku.View.Login;
import com.example.buahku.helper.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewRekomendasi extends RecyclerView.Adapter<RecyclerViewRekomendasi.ViewHolder> {
    private ArrayList<ProdukModels> dataProduk;
    private Context context;
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textHarga;
        ImageView image;
        Button btnAddKeranjang;
        RelativeLayout parrent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.namaProduk);
            textHarga = itemView.findViewById(R.id.hargaProduk);
            image = itemView.findViewById(R.id.imageProduk);
            btnAddKeranjang = itemView.findViewById(R.id.buttonAddKeranjang);
            parrent = itemView.findViewById(R.id.relativeLayoutProduk);
        }
    }

    public RecyclerViewRekomendasi(Context context, ArrayList<ProdukModels> dataProduk) {
        this.context = context;
        this.dataProduk = dataProduk;
    }

    @NonNull
    @Override
    public RecyclerViewRekomendasi.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_produk, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewRekomendasi.ViewHolder holder, int position) {

        TextView textName = holder.textName;
        TextView textHarga = holder.textHarga;
        ImageView image = holder.image;

        textName.setText(dataProduk.get(position).getNama());
        String h = Helper.numberWithCommas(dataProduk.get(position).getHarga());
        textHarga.setText("Rp"+h+"/kg");
        String imageUrl = ("https://web-buahku.pegelinux.my.id/assets/images/produk/"+dataProduk.get(position).getFoto());
        Picasso.get().load(imageUrl).resize(180, 180).placeholder(R.drawable.logo).into(image);

        holder.parrent.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailProduk.class);
            intent.putExtra("id", dataProduk.get(position).getId());

            context.startActivity(intent);
        });

        Button btnAddKeranjang = holder.btnAddKeranjang;
        btnAddKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences user = context.getSharedPreferences("user", 0);
                String username = user.getString("username", null);

                if (username == "") {
                    Toast.makeText(context, "Anda belum login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, Login.class);
                    context.startActivity(intent);
                }


                AddKeranjangData addKeranjangData = new AddKeranjangData(
                        dataProduk.get(position).getId(),
                        dataProduk.get(position).getNama(),
                        username,
                        dataProduk.get(position).getHarga(),
                        1,
                        dataProduk.get(position).getHarga()
                );
                addKeranjangData.execute();

                Toast.makeText(context, "Berhasil menambah keranjang", Toast.LENGTH_SHORT).show();

            }
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
