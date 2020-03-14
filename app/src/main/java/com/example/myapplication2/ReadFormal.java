package com.example.myapplication2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class ReadFormal extends AppCompatActivity {

    private Button btnPause;
    private Button btnPlay;
    private Button btnreplay;
    private Button btnStop;
    private PDFView pdfView;
    private TextToSpeech textToSpeech;
    private Button btnBackhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_formal);

        btnPause = findViewById(R.id.Pause);
        btnPlay = findViewById(R.id.Start);
        btnreplay = findViewById(R.id.Restart);
        btnStop = findViewById(R.id.Stop);
        btnBackhome = findViewById(R.id.backhome);



        pdfView = findViewById(R.id.pdfView);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);



        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener(){
            @Override
            public void onInit(int i) {
                textToSpeech.setLanguage(Locale.US);


            }
        }, "com.google.android.tts");

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);


//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("*/*");
//        startActivityForResult(intent, 7);
        final File file = new File("/sdcard/sudhpaper3.pdf");
        pdfView.fromAsset("sudhpaper3.pdf").load();


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String stringParser;
                    PdfReader pdfReader = new PdfReader(file.getPath());
                    int nop = pdfReader.getNumberOfPages();
                    for (int i = 1; i <= nop; i++) {
                        stringParser = PdfTextExtractor.getTextFromPage(pdfReader, i).toString();
                        pdfReader.close();

                        textToSpeech.speak(stringParser, TextToSpeech.QUEUE_FLUSH, null, null);


                        textToSpeech.playSilence(750, TextToSpeech.QUEUE_ADD, null);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stop = " ";
                textToSpeech.speak(stop, TextToSpeech.QUEUE_FLUSH, null);

            }
        });

        btnreplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        btnBackhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadFormal.this, HomeActivity.class);
                startActivity(intent);
            }
        });




    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch (requestCode) {
//
//            case 7:
//
//                if (resultCode == RESULT_OK) {
//
//                    String PathHolder = data.getData().getPath();
//                    File file = new File(PathHolder);
//
//
//                    //Toast.makeText(ReadFormal.this, PathHolder, Toast.LENGTH_LONG).show();
//
//
//                }
//                break;
//
//        }
//    }
}

