package com.example.pp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    //String user_name,nic_no,address,phone_no,usr_name,password,re_password;
    EditText txtMail,txtPassword;
    Button login, reg;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    private FirebaseDatabase rootNode;
    private DatabaseReference userReference;
    private DatabaseReference docReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMail=findViewById(R.id.text_email);
        txtPassword=findViewById(R.id.text_password);

        login= findViewById(R.id.btn_login);
        //login.setOnClickListener((View.OnClickListener) this);

        reg=findViewById(R.id.btn_reg);
        progressBar=findViewById(R.id.progressB);

        fAuth=FirebaseAuth.getInstance();

        rootNode = FirebaseDatabase.getInstance();
        userReference = rootNode.getReference("Users");
        docReference = rootNode.getReference("Doctors");


        
        if (fAuth.getCurrentUser() != null) {
            String currentUserId = fAuth.getCurrentUser().getUid();
            userReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull  Task<DataSnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DataSnapshot dataSnapshot : task.getResult().getChildren()) {
                            UserData userData = dataSnapshot.getValue(UserData.class);
                            if (userData.user_id.equals(currentUserId)) {
                                startActivity(new Intent(getApplicationContext(),Homepage.class));
                                finish();
                            }
                        }
                    }
                }
            });
            docReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull  Task<DataSnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DataSnapshot dataSnapshot : task.getResult().getChildren()) {
                            DrData drData = dataSnapshot.getValue(DrData.class);
                            if (drData.dr_id.equals(currentUserId)) {
                                startActivity(new Intent(getApplicationContext(),DocHome.class));
                                finish();
                            }
                        }
                    }
                }
            });
        }


        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email= txtMail.getText().toString().trim();
                String pwd= txtPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    txtMail.setError("E mail is required");
                    txtMail.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    txtMail.setError("Please enter a valid E mail address");
                    txtMail.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(pwd)){
                    txtPassword.setError("Password is required");
                    txtPassword.requestFocus();
                    return;
                }

                if(pwd.length()<6){
                    txtPassword.setError("Enter minimum of 6 characters");
                    txtPassword.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);


                fAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            userReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (DataSnapshot dataSnapshot : task.getResult().getChildren()) {
                                            UserData userDataFromFirebase = dataSnapshot.getValue(UserData.class);
                                            if (userDataFromFirebase != null) {
                                                if (email.equals(userDataFromFirebase.getEmail())) {
                                                    startActivity(new Intent(MainActivity.this,Homepage.class));
                                                    Toast.makeText(MainActivity.this,"Login Successful for user!",Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                    }
                                }
                            });
                            docReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (DataSnapshot dataSnapshot : task.getResult().getChildren()) {
                                            DrData drDataFromFirebase = dataSnapshot.getValue(DrData.class);
                                            if (drDataFromFirebase != null) {
                                                if (email.equals(drDataFromFirebase.getDr_email())) {
                                                    startActivity(new Intent(MainActivity.this,DocHome.class));
                                                    Toast.makeText(MainActivity.this,"Login Successful for doctor!",Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(MainActivity.this,"Error!",Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });

            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View unit = getLayoutInflater().inflate(R.layout.user_type, null);
                RadioGroup radioGroup = (RadioGroup) unit.findViewById(R.id.RG_user);

                userType(unit,radioGroup);


            }
        });
    }

    private void userType(View user_type, RadioGroup radioGroup) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select User type");
        builder.setView(user_type);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void radioChecked(View view) {
        Intent  openUser_reg= new Intent(MainActivity.this, user_reg.class);
        Intent openDoc_reg = new Intent(MainActivity.this, Doc_reg.class);

        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.P_owner:
                if (checked){
                    startActivity(openUser_reg);}
                    break;
            case R.id.dr:
                if (checked){
                    startActivity(openDoc_reg);}
                    break;
        }
    }
}