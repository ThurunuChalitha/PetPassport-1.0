package com.example.pp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_pet extends AppCompatActivity implements View.OnClickListener {

    private EditText ePet_name;
    private EditText eGender;
    private EditText eBreed;
    private EditText eRemarks;
    private EditText eBirthday;
    private String eUser;
    private ImageView imPet;
    private Button btPet_reg;


    private FirebaseAuth mAuth;
    private FirebaseDatabase root;
    private DatabaseReference ref;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        mAuth=FirebaseAuth.getInstance();

        root = FirebaseDatabase.getInstance();
        user=mAuth.getCurrentUser();
        ref = root.getReference("Pets");

        ePet_name=(EditText)findViewById(R.id.pet_name);
        eGender=(EditText)findViewById(R.id.gender);
        eBreed=(EditText)findViewById(R.id.breed);
        eRemarks=(EditText)findViewById(R.id.remarks);
        eBirthday=(EditText)findViewById(R.id.pet_bd);


        btPet_reg=(Button)findViewById(R.id.btn_newEntry);
        btPet_reg.setOnClickListener(this);

        imPet= (ImageView)findViewById(R.id.pet_image);
        imPet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pet_image:
                imgUpload();
                break;
            case R.id.btn_newEntry:
                petRegister();
                startActivity(new Intent(Add_pet.this, MyPets.class));
                Toast.makeText(Add_pet.this, "Pet Registration successful!", Toast.LENGTH_LONG).show();

                break;
        }
    }

    private void imgUpload(){

    }


    private void petRegister(){

        String pet_name=ePet_name.getText().toString().trim();
        String gender=eGender.getText().toString().trim();
        String breed=eBreed.getText().toString().trim();
        String remarks=eRemarks.getText().toString().trim();
        String pet_bd=eBirthday.getText().toString().trim();
        //String currentUser= current_usr.getText().toString().trim();

        String currentUser=user.getUid();

        PetData petdata =new PetData(pet_name,gender,breed,remarks,pet_bd,currentUser);
        String key = ref.push().getKey();
        ref.child(key).setValue(petdata, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(getApplicationContext(),"pet registration success", Toast.LENGTH_LONG).show();
            }
        });


        if (pet_name.isEmpty()){
            ePet_name.setError("Pet name is required!");
            ePet_name.requestFocus();
            return;
        }
        if (gender.isEmpty()){
            eGender.setError("Gender is required!");
            eGender.requestFocus();
            return;
        }
        if (breed.isEmpty()){
            eBreed.setError("Breed is required!");
            eBreed.requestFocus();
            return;
        }
        if (remarks.isEmpty()){
            eRemarks.setError("Remarks are required!");
            eRemarks.requestFocus();
            return;
        }if (pet_bd.isEmpty()){
            eBirthday.setError("Remarks are required!");
            eBirthday.requestFocus();
            return;
        }

    }
}