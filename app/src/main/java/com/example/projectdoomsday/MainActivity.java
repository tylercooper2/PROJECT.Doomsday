package com.example.projectdoomsday;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Environment;
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
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private BarcodeController barcodeController;
    private JsonUtils jsonUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize JsonUtils and create the file on the device if it does not exist
        jsonUtils = new JsonUtils(getApplicationContext());

        HashMap<String, String> itemHashMap = new HashMap<>();

        //TODO: Comment and add error catching
        //TODO: If the file already exists on the device, do not overwrite it with the assets file, use it instead to create the hashmap
        try
        {
            String jsonItems = readFromAssets();

            JSONArray jsonArray = new JSONArray(jsonItems);

            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String ID = jsonObject.getString("ID");
                String Title = jsonObject.getString("Title");
                itemHashMap.put(ID, Title);
            }
        }
        catch (JSONException e)
        {

        }

        jsonUtils.setItemValues(itemHashMap);
        barcodeController = new BarcodeController(findViewById(R.id.surface_view), findViewById(R.id.barcode_text), findViewById(R.id.itemTitleTextBox), getApplicationContext(), MainActivity.this, jsonUtils);

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

    private String readFromAssets()
    {

        String text;
        StringBuilder stringBuilder = new StringBuilder();

        try
        {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("itemData.json")));

            while((text = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(text).append("\n");
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return stringBuilder.toString();
    }

}
