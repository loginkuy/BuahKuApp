package com.example.buahku.Models;

public class TipsModels {
    private String id;
    private String tanggal;
    private String judul;
    private String isi;
    private String penulis;

    public TipsModels(String id, String tanggal, String judul, String isi, String penulis) {
        this.id = id;
        this.tanggal = tanggal;
        this.judul = judul;
        this.isi = isi;
        this.penulis = penulis;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
