package com.pbp.gd11_f_9618.ui.download;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.downloader.Progress;
import com.downloader.Status;
import com.pbp.gd11_f_9618.R;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.AdapterDownloadHolder> {
    private Context context;
    private List<Download> result;

    public DownloadAdapter(Context context, List<Download> result){
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public AdapterDownloadHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_download, parent, false);
        return new AdapterDownloadHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDownloadHolder holder, int position) {
        final Download download = result.get(position);
        final int[] downloadId = {-1};

        PRDownloader.initialize(context);
        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .build();
        PRDownloader.initialize(context, config);
        String dirPath = UtilityPR.getRootDirPath((Activity) context);
        holder.txtNama.setText(download.getJenis());
        holder.txtSize.setText(Double.toString(download.getSize()));

        //action cancel
        holder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PRDownloader.cancel(downloadId[0]);
            }
        });

        //action download
        holder.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Status.RUNNING == PRDownloader.getStatus(downloadId[0])) {
                    PRDownloader.pause(downloadId[0]);
                    return;
                }

                holder.progressBar.setIndeterminate(true);
                holder.progressBar.getIndeterminateDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadId[0])) {
                    PRDownloader.resume(downloadId[0]);
                    return;
                }

                downloadId[0] = PRDownloader.download(download.getUrl(), dirPath,
                        download.getNama() + "." + download.getEkstensi())
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                holder.progressBar.setIndeterminate(false);
                                holder.btnStart.setEnabled(true);
                                holder.btnCancel.setEnabled(true);
                                holder.btnStart.setText("Hentikan");
                                FancyToast.makeText(context,"Download dimulai!",FancyToast.LENGTH_SHORT,FancyToast.INFO,false).show();
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                holder.btnStart.setText("Teruskan");
                                FancyToast.makeText(context,"Download dihentikan sementara!",FancyToast.LENGTH_SHORT,FancyToast.INFO,false).show();
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                holder.btnStart.setEnabled(true);
                                holder.btnCancel.setEnabled(false);
                                holder.btnStart.setText("Download");
                                holder.txtSize.setText("");
                                downloadId[0] =0;
                                holder.progressBar.setProgress(0);
                                holder.progressBar.setIndeterminate(false);
                                FancyToast.makeText(context,"File batal didownload !",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                holder.txtSize.setText(UtilityPR.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                holder.progressBar.setProgress((int) progressPercent);
                                holder.progressBar.setIndeterminate(false);
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                holder.btnStart.setEnabled(false);
                                holder.btnCancel.setEnabled(false);
                                holder.btnStart.setBackgroundColor(Color.GRAY);
                                holder.btnCancel.setText("Berhasil");
                                holder.btnStart.setText("Downloaded");
                                FancyToast.makeText(context,"File berhasil didownload!",FancyToast.LENGTH_SHORT, FancyToast.SUCCESS,false).show();

                            }

                            @Override
                            public void onError(Error error) {
                                holder.btnStart.setEnabled(true);
                                holder.btnCancel.setEnabled(false);
                                holder.btnStart.setText("Download");
                                holder.txtSize.setText("");
                                downloadId[0] =0;
                                holder.progressBar.setIndeterminate(false);
                                holder.progressBar.setProgress(0);
                                FancyToast.makeText(context,"Kesalahan Jaringan!",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class AdapterDownloadHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        TextView txtNama, txtSize;
        Button btnCancel, btnStart;

        public AdapterDownloadHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.pb);
            txtNama = itemView.findViewById(R.id.tvNama);
            txtSize = itemView.findViewById(R.id.tvProgress);
            btnCancel = itemView.findViewById(R.id.btnCancel);
            btnStart = itemView.findViewById(R.id.btnStart);
        }
    }




}
