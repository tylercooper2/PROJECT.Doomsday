package com.example.projectdoomsday;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private BarcodeController barcodeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barcodeController = new BarcodeController(findViewById(R.id.surface_view), findViewById(R.id.barcode_text), findViewById(R.id.itemTitleTextBox), getApplicationContext(), MainActivity.this);
    }





    @Override
    protected void onPause() {
        super.onPause();
        getSupportActionBar().hide();
        barcodeController.releaseCameraSource();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().hide();
        barcodeController.initialiseDetectorsAndSources();
    }


}
