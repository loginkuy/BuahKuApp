package com.example.buahku.Models;

public class ProdukModels {
    private String id;
    private String nama;
    private int harga;
    private int stok;
    private String namaLatin;
    private String deskripsi;
    private String khasiat;
    private String budidaya;
    private String tanah;
    private String suhu;
    private String foto;
    private String gizi;

    public ProdukModels(String id, String nama, int harga, int stok, String namaLatin, String deskripsi, String khasiat, String budidaya, String tanah, String suhu, String foto, String gizi) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        this.namaLatin = namaLatin;
        this.deskripsi = deskripsi;
        this.khasiat = khasiat;
        this.budidaya = budidaya;
        this.tanah = tanah;
        this.suhu = suhu;
        this.foto = foto;
        this.gizi = gizi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public String getNamaLatin() {
        return namaLatin;
    }

    public void setNamaLatin(String namaLatin) {
        this.namaLatin = namaLatin;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKhasiat() {
        return khasiat;
    }

    public void setKhasiat(String khasiat) {
        this.khasiat = khasiat;
    }

    public String getBudidaya() {
        return budidaya;
    }

    public void setBudidaya(String budidaya) {
        this.budidaya = budidaya;
    }

    public String getTanah() {
        return tanah;
    }

    public void setTanah(String tanah) {
        this.tanah = tanah;
    }

    public String getSuhu() {
        return suhu;
    }

    public void setSuhu(String suhu) {
        this.suhu = suhu;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getGizi() {
        return gizi;
    }

    public void setGizi(String gizi) {
        this.gizi = gizi;
    }
}


