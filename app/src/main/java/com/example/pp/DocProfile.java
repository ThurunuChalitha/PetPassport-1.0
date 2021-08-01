package com.example.pp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DocProfile extends AppCompatActivity {

    TextView drnameh,drname,slva,vetOFF,dRpHno,dReMail,dRusRname;

    private FirebaseAuth mAuth;
    private FirebaseDatabase root;
    private DatabaseReference ref;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_profile);

        mAuth=FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance();
        user=mAuth.getCurrentUser();

        String currentU=user.getUid();

        drnameh=(TextView)findViewById(R.id.View_doc_nameh);
        drname=(TextView)findViewById(R.id.View_doc_name);
        slva=(TextView)findViewById(R.id.View_slvaNo);
        vetOFF=(TextView)findViewById(R.id.View_vetOffice);
        dRpHno=(TextView)findViewById(R.id.View_pHDNo);
        dReMail=(TextView)findViewById(R.id.View_DeMail);
        dRusRname=(TextView)findViewById(R.id.View_usrDName);

        ref= root.getReference().child("Doctors").child(currentU);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String NameH=snapshot.child("dr_name").getValue().toString();
                String Name=snapshot.child("dr_name").getValue().toString();
                String NIC=snapshot.child("slva_no").getValue().toString();
                String Address=snapshot.child("vet_office").getValue().toString();
                String cNo=snapshot.child("dr_phone_no").getValue().toString();
                String Email=snapshot.child("dr_email").getValue().toString();
                String USRname=snapshot.child("dr_usr_name").getValue().toString();

                drnameh.setText(NameH);
                drname.setText(Name);
                slva.setText(NIC);
                vetOFF.setText(Address);
                dRpHno.setText(cNo);
                dReMail.setText(Email);
                dRusRname.setText(USRname);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}