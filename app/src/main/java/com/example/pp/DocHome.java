package com.example.pp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.pp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.Date;

public class DocHome extends AppCompatActivity {
    private FirebaseAuth mAuth;


    CardView card_DocPP;
    CardView cardScnID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_home);
        mAuth=FirebaseAuth.getInstance();

        card_DocPP=findViewById(R.id.card_DocPrf);
        cardScnID=findViewById(R.id.card_scnID);

        //Date date = new Date();
        //String fullDate = String.valueOf(date.getYear()+1900) + "-" +  String.valueOf(date.getMonth()+1) + "-" +  String.valueOf(date.getDate());
        //Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ", fullDate);


        cardScnID.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScanQR.class));

            }
        });

        card_DocPP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DocProfile.class));

            }
        });

    }

    public void Logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(DocHome.this);
        builder.setTitle("Exit");
        //builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to exit from the app?")
                .setCancelable(true)
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

}