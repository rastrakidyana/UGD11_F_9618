package com.pbp.gd11_f_9618.ui.pdf;

public class Buku {
    private int idBuku;
    private String namaBuku, pengarang, gambar;
    private Double harga;

    public Buku (int idBuku, String namaBuku, String pengarang, Double harga, String gambar) {
        this.idBuku = idBuku;
        this.namaBuku = namaBuku;
        this.pengarang = pengarang;
        this.harga = harga;
        this.gambar = gambar;
    }

    public Buku (String namaBuku, String pengarang, Double harga, String gambar) {
        this.namaBuku = namaBuku;
        this.pengarang = pengarang;
        this.harga = harga;
        this.gambar = gambar;
    }

    public Double getHarga() {
        return harga;
    }

    public int getIdBuku() {
        return idBuku;
    }

    public String getNamaBuku() {
        return namaBuku;
    }

    public String getPengarang() {
        return pengarang;
    }

    public String getGambar() {
        return gambar;
    }
}
