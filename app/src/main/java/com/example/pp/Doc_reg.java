package com.example.pp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Doc_reg extends AppCompatActivity implements View.OnClickListener{

    private EditText dUser_name,dslva,dOffice,dPh_no,dEmail,dUsr_name,dPwd,dRe_pwd;
    private ImageView imDr;
    private Button btDr_reg;

    private FirebaseAuth mAuth;
    private FirebaseDatabase root;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_reg);



        root = FirebaseDatabase.getInstance();
        ref = root.getReference("Doctors");
        mAuth=FirebaseAuth.getInstance();

        dUser_name=(EditText)findViewById(R.id.dr_name);
        dslva=(EditText)findViewById(R.id.slva_no);
        dOffice=(EditText)findViewById(R.id.vet_off);
        dPh_no=(EditText)findViewById(R.id.dr_ph_n0);
        dEmail=(EditText)findViewById(R.id.dr_e_mail);
        dUsr_name=(EditText)findViewById(R.id.drusr_name);
        dPwd=(EditText)findViewById(R.id.dr_pwd);
        dRe_pwd=(EditText) findViewById(R.id.dr_re_pwd);

        btDr_reg= (Button) findViewById(R.id.btn_dr_reg);
        btDr_reg.setOnClickListener(this);

        imDr= (ImageView)findViewById(R.id.doc_image);
        imDr.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.doc_image:
                imgDUpload();
                break;
            case R.id.btn_dr_reg:
                DrRegister();
                break;
        }
    }

    private void imgDUpload(){

    }

    private void DrRegister(){

        String dr_name=dUser_name.getText().toString().trim();
        String slva_no=dslva.getText().toString().trim();
        String vet_office=dOffice.getText().toString().trim();
        String d_phone_n0=dPh_no.getText().toString().trim();
        String d_e_mail=dEmail.getText().toString().trim();
        String d_usr_name=dUsr_name.getText().toString().trim();
        String d_pwd=dPwd.getText().toString().trim();
        String dre_pwd=dRe_pwd.getText().toString().trim();


        if (dr_name.isEmpty()){
            dUser_name.setError("Name is required!");
            dUser_name.requestFocus();
            return;
        }
        if (slva_no.isEmpty()){
            dslva.setError("NIC no is required!");
            dslva.requestFocus();
            return;
        }
        if (vet_office.isEmpty()){
            dOffice.setError("Name is required!");
            dOffice.requestFocus();
            return;
        }
        if (d_phone_n0.isEmpty()){
            dPh_no.setError("Name is required!");
            dPh_no.requestFocus();
            return;
        }
        if (d_e_mail.isEmpty()){
            dEmail.setError("Name is required!");
            dEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(d_e_mail).matches()){
            dEmail.setError("Please enter a valid E mail address");
            dEmail.requestFocus();
            return;
        }
        if (dr_name.isEmpty()){
            dUsr_name.setError("Name is required!");
            dUsr_name.requestFocus();
            return;
        }
        if (d_pwd.isEmpty()){
            dPwd.setError("Name is required!");
            dPwd.requestFocus();
            return;
        }
        if(d_pwd.length()<6){
            dPwd.setError("Enter minimum of 6 characters");
            dPwd.requestFocus();
            return;
        }
        if (dre_pwd.isEmpty()){
            dRe_pwd.setError("Name is required!");
            dRe_pwd.requestFocus();
            return;
        }

        if(dre_pwd.length()<6){
            dRe_pwd.setError("Enter minimum of 6 characters");
            dRe_pwd.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(d_e_mail,d_pwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        ref = root.getReference("Doctors");
                        mAuth=FirebaseAuth.getInstance();

                        if(task.isSuccessful()){
                            DrData drdata=new DrData(dr_name,slva_no,vet_office,d_phone_n0,d_e_mail,d_usr_name,mAuth.getCurrentUser().getUid());
                            ref.child(mAuth.getCurrentUser().getUid()).setValue(drdata, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError error, @NonNull  DatabaseReference ref) {
                                    if(task.isSuccessful()){
                                        startActivity(new Intent(Doc_reg.this, DocHome.class));
                                        Toast.makeText(Doc_reg.this, "User Registration successful!", Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(Doc_reg.this,"Failed to this",Toast.LENGTH_LONG).show();
                                    }
                                }

                            });
                        }else{
                            Toast.makeText(Doc_reg.this,"Failed to register",Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }
}