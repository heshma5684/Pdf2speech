package com.example.myapplication2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;



import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.github.barteksc.pdfviewer.PDFView;
import com.google.api.client.util.store.DataStore;



import java.io.File;

public class StoryTelling extends AppCompatActivity {
    PDFView pdfView;
    Button btnPause;
    Button btnReplay;
    Button btnStop;
    Button btnStart;
    Button btnBackhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        pdfView = findViewById(R.id.pdfView);

        btnPause = findViewById(R.id.Pause);
        btnReplay = findViewById(R.id.Restart);
        btnStart = findViewById(R.id.Start);
        btnStop = findViewById(R.id.Stop);
        btnBackhome = findViewById(R.id.backhome);

        btnBackhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StoryTelling.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }


}
