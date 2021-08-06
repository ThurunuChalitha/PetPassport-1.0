package com.example.pp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GenQR extends AppCompatActivity {

    TextView editText;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_qr);

        Bundle bundle = getIntent().getExtras();
        String pet_key = bundle.getString("pet_key_from_pet_detail");
        String petNAME = bundle.getString("pet_Name_frm_pet_detail");

        editText=findViewById(R.id.textQR);
        imageView=findViewById(R.id.imgQR);

        editText.setText(petNAME);

        if(!pet_key.equals("")){
            new QRdownloader(imageView).execute("https://api.qrserver.com/v1/create-qr-code/?size=1000x1000&data="+pet_key);
        }else{
            Toast.makeText(GenQR.this,"Enter text",Toast.LENGTH_LONG).show();
        }

    }
}