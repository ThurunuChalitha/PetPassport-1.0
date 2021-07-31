package com.example.pp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
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

public class Homepage extends AppCompatActivity {
    private FirebaseAuth mAuth;

    CardView cardAddPet;
    CardView cardMyPrf;
    CardView cardMyPet;
    CardView cardScnID;
    CardView cardCalDoc;

    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        mAuth=FirebaseAuth.getInstance();

        cardAddPet=findViewById(R.id.addPet);
        cardMyPet=findViewById(R.id.card_myPets);
        cardMyPrf=findViewById(R.id.myPrf);
        cardScnID=findViewById(R.id.card_scnID);
        cardCalDoc=findViewById(R.id.card_callDoc);


        cardAddPet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Add_pet.class));
            }
        });

        cardMyPet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Pet_detail.class));
            }
        });

        cardScnID.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        cardMyPrf.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MyProfile.class));

            }
        });

    }

    public void Logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

}