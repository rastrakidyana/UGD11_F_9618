package com.pbp.gd11_f_9618.ui.download;

public class Download {
    private String nama;
    private String jenis;
    private String ekstensi;
    private double size;
    private String url;

    public Download(String nama, String jenis, String ekstensi, double size, String url) {
        this.nama = nama;
        this.jenis = jenis;
        this.ekstensi = ekstensi;
        this.size = size;
        this.url = url;
    }

    public String getNama() {
        return nama;
    }

    public String getJenis() {
        return jenis;
    }

    public String getEkstensi() {
        return ekstensi;
    }

    public double getSize() {
        return size;
    }

    public String getUrl() {
        return url;
    }
}
