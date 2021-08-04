package com.example.pp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class GenQR extends AppCompatActivity {

    EditText editText;
    Button button;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_qr);

        editText=findViewById(R.id.textQR);
        button=findViewById(R.id.btnQR);
        imageView=findViewById(R.id.imgQR);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text =editText.getText().toString();
                if(!text.equals("")){
                    new QRdownloader(imageView).execute("https://api.qrserver.com/v1/create-qr-code/?size=1000x1000&data="+text);
                }else{
                    Toast.makeText(GenQR.this,"Enter text",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}