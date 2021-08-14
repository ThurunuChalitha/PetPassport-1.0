package com.example.pp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanQR extends AppCompatActivity {

    private TextView textDisp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        textDisp=findViewById(R.id.text_qr);

        ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        //integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setPrompt("Scanning code");
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if(result.getContents()==null){
                textDisp.setText("Cancelled!");
            }
            else{
                textDisp.setText(result.getContents());
                Intent openAcivity = new Intent(ScanQR.this, PetDocView.class);
                openAcivity.putExtra("pet_key", result.getContents());
                startActivity(openAcivity);
            }
        }
        super.onActivityResult(requestCode,resultCode,data);
    }
}