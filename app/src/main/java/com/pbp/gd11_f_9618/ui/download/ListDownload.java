package com.pbp.gd11_f_9618.ui.download;

import java.util.ArrayList;

public class ListDownload {
    public ArrayList<Download> DOWNLOAD;

    public ListDownload(){
        DOWNLOAD = new ArrayList();
        DOWNLOAD.add(PDF);
        DOWNLOAD.add(GAMBAR);
        DOWNLOAD.add(MUSIK);
    }

    public static final Download PDF = new Download("Hello World!","PDF", "pdf",
            0.52, "http://www.pustaka.ut.ac.id/lib/wp-content/uploads/pdfmk/PUST442502-M1.pdf");

    public static final Download GAMBAR = new Download("7 Deadly Sins","Gambar", "jpg",
            0.15, "https://www.kaorinusantara.or.id/wp-content/uploads/2019/09/seven-deadly-sins.jpg");

    public static final Download MUSIK = new Download("Music","Musik", "mp3",
            3.76, "https://pelangidb.com/pbp/download/Passionate_Anthem_Roselia.mp3");
}
