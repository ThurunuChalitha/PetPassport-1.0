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

public class FormVacc extends AppCompatActivity implements View.OnClickListener {

    private EditText eVac;
    private EditText eVacDdate;
    private Button btnVac_add;


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

        eVac=(EditText)findViewById(R.id.pet_vacType);
        eVacDdate=(EditText)findViewById(R.id.pet_vacDdate);

        btnVac_add=(Button)findViewById(R.id.btn_vacEntry);
        btnVac_add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_newEntry:
                vacAdd();
                startActivity(new Intent(FormVacc.this, MyPets.class)); ///CHage this to VaccDis
                Toast.makeText(FormVacc.this, "Pet Registration successful!", Toast.LENGTH_LONG).show();

                break;
        }
    }




    private void vacAdd(){

//        String VaccineType=eVac.getText().toString().trim();
//        String DueDateV=eVacDdate.getText().toString().trim();
//        //String currentUser= current_usr.getText().toString().trim();
//
//        String currentUser=user.getUid();
//
//        VaccDATA vaccDATA =new VaccDATA(DueDateV,VaccineType,pet_idV,VDate);
//        String key = ref.push().getKey();
//        ref.child(key).setValue(vaccDATA, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                Toast.makeText(getApplicationContext(),"Vaccination data added!", Toast.LENGTH_LONG).show();
//            }
//        });


//        if (DueDateV.isEmpty()){
//            eVacDdate.setError("Pet name is required!");
//            eVacDdate.requestFocus();
//            return;
//        }
//        if (VaccineType.isEmpty()){
//            eVac.setError("Gender is required!");
//            eVac.requestFocus();
//            return;


    }
}