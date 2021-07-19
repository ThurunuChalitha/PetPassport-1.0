package com.example.pp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pp.R;
import com.example.pp.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_reg extends AppCompatActivity implements View.OnClickListener {

    private EditText eUser_name,eNic_no,eAddrs,ePh_no,eEmail,eUsr_name,ePwd,eRe_pwd;
    private ImageView imUser;
    private Button btUser_reg;

    private FirebaseAuth mAuth;
    private FirebaseDatabase root;
    private DatabaseReference ref;
    private DatabaseReference ref1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg);

        mAuth=FirebaseAuth.getInstance();

        root = FirebaseDatabase.getInstance();
        ref = root.getReference("Users");

        eUser_name=(EditText)findViewById(R.id.user_name);
        eNic_no=(EditText)findViewById(R.id.nic_no);
        eAddrs=(EditText)findViewById(R.id.addrs);
        ePh_no=(EditText)findViewById(R.id.ph_n0);
        eEmail=(EditText)findViewById(R.id.e_mail);
        eUsr_name=(EditText)findViewById(R.id.usr_name);
        ePwd=(EditText)findViewById(R.id.pwd);
        eRe_pwd=(EditText) findViewById(R.id.re_pwd);

        btUser_reg=(Button)findViewById(R.id.btn_user_reg);
        btUser_reg.setOnClickListener(this);

        imUser= (ImageView)findViewById(R.id.usr_image);
        imUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.usr_image:
                imgUpload();
                break;
            case R.id.btn_user_reg:
                userRegister();
                break;
        }
    }
    private void imgUpload(){

    }

    private void userRegister(){

        String user_name=eUser_name.getText().toString().trim();
        String nic_no=eNic_no.getText().toString().trim();
        String addrs=eAddrs.getText().toString().trim();
        String ph_n0=ePh_no.getText().toString().trim();
        String e_mail=eEmail.getText().toString().trim();
        String usr_name=eUsr_name.getText().toString().trim();
        String pwd=ePwd.getText().toString().trim();
        String re_pwd=eRe_pwd.getText().toString().trim();

        UserData userdata =new UserData(user_name,nic_no,addrs,ph_n0,e_mail,usr_name,pwd,re_pwd);
        ref.child(nic_no).setValue(userdata);

        if (user_name.isEmpty()){
            eUser_name.setError("Name is required!");
            eUser_name.requestFocus();
            return;
        }
        if (nic_no.isEmpty()){
            eNic_no.setError("NIC no is required!");
            eNic_no.requestFocus();
            return;
        }
        if (addrs.isEmpty()){
            eAddrs.setError("Name is required!");
            eAddrs.requestFocus();
            return;
        }
        if (ph_n0.isEmpty()){
            ePh_no.setError("Name is required!");
            ePh_no.requestFocus();
            return;
        }
        if (e_mail.isEmpty()){
            eEmail.setError("Name is required!");
            eEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(e_mail).matches()){
            eEmail.setError("Please enter a valid E mail address");
            eEmail.requestFocus();
            return;
        }
        if (usr_name.isEmpty()){
            eUsr_name.setError("Name is required!");
            eUsr_name.requestFocus();
            return;
        }
        if (pwd.isEmpty()){
            ePwd.setError("Name is required!");
            ePwd.requestFocus();
            return;
        }
        if(pwd.length()<6){
            ePwd.setError("Enter minimum of 6 characters");
            ePwd.requestFocus();
            return;
        }
        if (re_pwd.isEmpty()){
            eRe_pwd.setError("Name is required!");
            eRe_pwd.requestFocus();
            return;
        }
        if(re_pwd.length()<6){
            eRe_pwd.setError("Enter minimum of 6 characters");
            eRe_pwd.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(e_mail,pwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        ref = root.getReference("Users");

                        if(task.isSuccessful()){
                            UserData userdata=new UserData(user_name,nic_no,addrs,ph_n0,e_mail,usr_name,pwd,re_pwd);
                            ref.child(mAuth.getCurrentUser().getUid()).setValue(userdata, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError error, @NonNull  DatabaseReference ref) {
                                    if(task.isSuccessful()){
                                        startActivity(new Intent(user_reg.this, Homepage.class));
                                        Toast.makeText(user_reg.this, "User Registration successful!", Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(user_reg.this,"Failed to this",Toast.LENGTH_LONG).show();
                                    }
                                }

                            });
                        }else{
                            Toast.makeText(user_reg.this,"Failed to register",Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }
}