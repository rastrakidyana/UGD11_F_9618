package com.pbp.gd11_f_9618.ui.download;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
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

import java.util.ArrayList;

public class DownloadFragment extends Fragment {
    private ArrayList<Download> ListDownload;
    private DownloadViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(DownloadViewModel.class);
        View root = inflater.inflate(R.layout.fragment_download, container, false);

        ListDownload = new ListDownload().DOWNLOAD;

        RecyclerView recyclerView = root.findViewById(R.id.rvdownload);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new DownloadAdapter(getContext(), ListDownload));

        return root;
    }


}