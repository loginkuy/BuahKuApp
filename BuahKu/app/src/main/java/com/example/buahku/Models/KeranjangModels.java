package com.example.buahku.Models;

public class KeranjangModels {
    private String id;
    private String nama;
    private int harga;
    private int qty;
    private int subTotal;
    private String image;

    public KeranjangModels(String id, String nama, int harga, int qty, int subTotal, String image) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.qty = qty;
        this.subTotal = subTotal;
        this.image = image;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
