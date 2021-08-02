package com.example.pp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyProfile extends AppCompatActivity {

    TextView nameh,name,nic,adddrs,pHno,eMail,usRname;

    private FirebaseAuth mAuth;
    private FirebaseDatabase root;
    private DatabaseReference ref;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        mAuth=FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance();
        user=mAuth.getCurrentUser();
        Log.d("AAAAAAAAAAAAAAAAAAAAAA",user.getUid());

        String currentU=user.getUid();

        nameh=(TextView)findViewById(R.id.View_user_nameh);
        name=(TextView)findViewById(R.id.View_user_name);
        nic=(TextView)findViewById(R.id.View_nicNo);
        adddrs=(TextView)findViewById(R.id.View_address);
        pHno=(TextView)findViewById(R.id.View_pHNo);
        eMail=(TextView)findViewById(R.id.View_eMail);
        usRname=(TextView)findViewById(R.id.View_usrName);

        ref= root.getReference().child("Users").child(currentU);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String NameH=snapshot.child("user_name").getValue().toString();
                String NIC=snapshot.child("nic_no").getValue().toString();
                String Address=snapshot.child("address").getValue().toString();
                String cNo=snapshot.child("phone_no").getValue().toString();
                String Email=snapshot.child("email").getValue().toString();
                String USRname=snapshot.child("usr_name").getValue().toString();

                nameh.setText(NameH);
                name.setText(NameH);
                nic.setText(NIC);
                adddrs.setText(Address);
                pHno.setText(cNo);
                eMail.setText(Email);
                usRname.setText(USRname);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}