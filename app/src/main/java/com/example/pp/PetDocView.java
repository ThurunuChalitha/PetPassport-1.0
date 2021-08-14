package com.example.pp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PetDocView extends AppCompatActivity {

    Button vaccineDr;
    Button testDr;
    Button prescribeDr;

    private FirebaseAuth mAuth;
    private FirebaseDatabase root;
    private DatabaseReference ref;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_doc_view);

        Button vaccineDr=(Button) findViewById(R.id.vac_detail);
        Button testDr=(Button)findViewById(R.id.tests);
        Button prescribeDr=(Button)findViewById(R.id.pres);



        TextView pNameH = (TextView) findViewById(R.id.Dpet_name_view);
        TextView pNameN = (TextView) findViewById(R.id.DView_petName);
        TextView pBd = (TextView) findViewById(R.id.DView_birthday);
        TextView pGender = (TextView) findViewById(R.id.DView_gender);
        TextView pBreed = (TextView) findViewById(R.id.DView_breed);
        TextView pRemks = (TextView) findViewById(R.id.DView_remarks);
        TextView pAnimal = (TextView) findViewById(R.id.DView_animal);

        mAuth=FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance();
        user=mAuth.getCurrentUser();

        //String currentU=user.getUid();


        Bundle bundle = getIntent().getExtras();
        String pet_key = bundle.getString("pet_key");

        ref= root.getReference().child("Pets").child(pet_key);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.child("pet_name").getValue() != null) {
                    String petNameH=snapshot.child("pet_name").getValue().toString();
                    String petAnimal=snapshot.child("pet_animal").getValue().toString();
                    String petGender=snapshot.child("gender").getValue().toString();
                    String petBd=snapshot.child("pet_bd").getValue().toString();
                    String petBreed=snapshot.child("breed").getValue().toString();
                    String petRemks=snapshot.child("remarks").getValue().toString();

                    pNameH.setText(petNameH);
                    pNameN.setText(petNameH);
                    pBd.setText(petAnimal);
                    pGender.setText(petGender);
                    pBreed.setText(petBd);
                    pRemks.setText(petBreed);
                    pAnimal.setText(petRemks);
                } else {
                    pNameH.setText("NO PET FOUND");
                    pNameN.setText("-");
                    pBd.setText("-");
                    pGender.setText("-");
                    pBreed.setText("-");
                    pRemks.setText("-");
                    pAnimal.setText("-");
                    Toast.makeText(getApplicationContext(),"NO PET FOUND!",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}