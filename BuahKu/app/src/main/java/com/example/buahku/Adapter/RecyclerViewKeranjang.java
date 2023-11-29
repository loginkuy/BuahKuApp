package com.example.buahku.Adapter;

import android.content.Context;
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

import com.example.buahku.Api.DeleteKeranjangData;
import com.example.buahku.Api.UpdateKeranjangData;
import com.example.buahku.Models.KeranjangModels;
import com.example.buahku.R;
import com.example.buahku.helper.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewKeranjang extends RecyclerView.Adapter<RecyclerViewKeranjang.ViewHolder> {
    private ArrayList<KeranjangModels> dataProduk;
    private Context context;

    private OnItemDeletedListener onItemDeletedListener;
    public void setOnItemDeletedListener(OnItemDeletedListener listener) {
        this.onItemDeletedListener = listener;
    }
    public interface OnItemDeletedListener {
        void onItemDeleted(int position);
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textHarga, textSubtotal;
        ImageView image;
        TextView qty;
        Button btnDelete, btnKurang, btnTambah;
        RelativeLayout parrent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.namaProdukKeranjang);
            textHarga = itemView.findViewById(R.id.hargaProdukKeranjang);
            image = itemView.findViewById(R.id.imageProdukKeranjang);
            qty = itemView.findViewById(R.id.qty);
            btnDelete = itemView.findViewById(R.id.deleteKeranjang);
            btnKurang = itemView.findViewById(R.id.kurangqty);
            btnTambah = itemView.findViewById(R.id.tambahqty);
            textSubtotal = itemView.findViewById(R.id.subTotal);
            parrent = itemView.findViewById(R.id.listKeranjangLayout);
        }
    }

    public RecyclerViewKeranjang(Context context, ArrayList<KeranjangModels> dataProduk) {
        this.context = context;
        this.dataProduk = dataProduk;
    }

    @NonNull
    @Override
    public RecyclerViewKeranjang.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_keranjang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewKeranjang.ViewHolder holder, int position) {

        TextView textName = holder.textName;
        TextView textHarga = holder.textHarga;
        ImageView image = holder.image;
        TextView qty = holder.qty;
        TextView subTotal = holder.textSubtotal;

        if (!dataProduk.get(position).getNama().isEmpty()) {
            textName.setText(dataProduk.get(position).getNama());
        }

        String h = Helper.numberWithCommas(dataProduk.get(position).getHarga());
        textHarga.setText("Rp"+h+"/kg");

        String i = Helper.numberWithCommas(dataProduk.get(position).getSubTotal());
        subTotal.setText("Rp"+i);

        String imageUrl = ("https://web-buahku.pegelinux.my.id/assets/images/produk/"+dataProduk.get(position).getImage());
        Picasso.get().load(imageUrl).resize(180, 180).placeholder(R.drawable.logo).into(image);
        qty.setText(String.valueOf(dataProduk.get(position).getQty()));

        Button btnDelete = holder.btnDelete;
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteKeranjangData deleteKeranjangData = new DeleteKeranjangData(dataProduk.get(position).getId());
                deleteKeranjangData.execute();
                if (onItemDeletedListener != null) {
                    onItemDeletedListener.onItemDeleted(position);
                }
            }
        });

        Button btnTambah = holder.btnTambah;
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateKeranjangData updateKeranjangData = new UpdateKeranjangData(dataProduk.get(position).getId(), dataProduk.get(position).getQty() + 1, dataProduk.get(position).getSubTotal() + dataProduk.get(position).getHarga());
                updateKeranjangData.execute();
                if (onItemDeletedListener != null) {
                    onItemDeletedListener.onItemDeleted(position);
                }
            }
        });

        Button btnKurang = holder.btnKurang;
        btnKurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dataProduk.get(position).getQty() == 1) {
                    DeleteKeranjangData deleteKeranjangData = new DeleteKeranjangData(dataProduk.get(position).getId());
                    deleteKeranjangData.execute();
                    if (onItemDeletedListener != null) {
                        onItemDeletedListener.onItemDeleted(position);
                    }
                } else {
                    UpdateKeranjangData updateKeranjangData = new UpdateKeranjangData(dataProduk.get(position).getId(), dataProduk.get(position).getQty() - 1, dataProduk.get(position).getSubTotal() - dataProduk.get(position).getHarga());
                    updateKeranjangData.execute();
                    if (onItemDeletedListener != null) {
                        onItemDeletedListener.onItemDeleted(position);
                    }
                }
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

    public void setData(ArrayList<KeranjangModels> newData) {
        this.dataProduk = newData;
        notifyDataSetChanged();
    }
}
