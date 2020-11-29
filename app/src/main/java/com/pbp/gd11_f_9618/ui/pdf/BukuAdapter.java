package com.pbp.gd11_f_9618.ui.pdf;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;

import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.pbp.gd11_f_9618.R;
import com.pbp.gd11_f_9618.ui.pdf.api.BukuAPI;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.List;


public class BukuAdapter extends RecyclerView.Adapter<BukuAdapter.adapterBukuViewHolder> {
    private List<Buku> bukuList;
    private List<Buku> bukuListFiltered;
    private Context context;
    private View view;

    public BukuAdapter(Context context, List<Buku> bukuList) {
        this.context            = context;
        this.bukuList           = bukuList;
        this.bukuListFiltered   = bukuList;
    }


    @NonNull
    @Override
    public BukuAdapter.adapterBukuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.activity_adapter_buku, parent, false);
        return new BukuAdapter.adapterBukuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BukuAdapter.adapterBukuViewHolder holder, int position) {
        final Buku buku = bukuListFiltered.get(position);

        NumberFormat formatter = new DecimalFormat("#,###");
        holder.txtNama.setText(buku.getNamaBuku());
        holder.txtPengarang.setText(buku.getPengarang());
        holder.txtHarga.setText("Rp "+ formatter.format(buku.getHarga()));
        Glide.with(context)
                .load(BukuAPI.URL_IMAGE+buku.getGambar())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.ivGambar);

    }

    @Override
    public int getItemCount() {
        return (bukuListFiltered != null) ? bukuListFiltered.size() : 0;
    }

    public class adapterBukuViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNama, txtPengarang, txtHarga;
        private ImageView ivGambar;
        private CardView cardBuku;

        public adapterBukuViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama         = itemView.findViewById(R.id.tvNamaBuku);
            txtPengarang    = itemView.findViewById(R.id.tvPengarang);
            txtHarga        = itemView.findViewById(R.id.tvHarga);
            ivGambar        = itemView.findViewById(R.id.ivGambar);
            cardBuku        = itemView.findViewById(R.id.cardBuku);
        }
    }

}
