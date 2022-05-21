package com.example.nexkraft;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;


import com.github.barteksc.pdfviewer.PDFView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ViewPdf extends AppCompatActivity {
    PDFView pdfView;




    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String path = getExternalFilesDir(null).getAbsolutePath().toString()+"Application_"+getCurrentTime() + "_" + getTodaysDate() + "leave.pdf";
        StrictMode.VmPolicy.Builder builder=new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();


        pdfView=findViewById(R.id.view_pdf);
        File file = new File(path);

        pdfView.fromFile(file)
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .enableAnnotationRendering(true)
                .defaultPage(0)
                .scrollHandle(null)
                .password(null)
                .load();
    }


    private String getCurrentTime() {
        return new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
    }

    private String getTodaysDate(){
        return new SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault()).format(new Date());
    }
}