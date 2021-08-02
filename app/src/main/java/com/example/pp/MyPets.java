package com.example.pp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

import java.util.List;

public class MyPets extends AppCompatActivity {

    private FirebaseDatabase rootNode;
    private DatabaseReference petReference;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private List<String> petNameList;
    private List<String> animalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pets);

        rootNode = FirebaseDatabase.getInstance();
        petReference = rootNode.getReference("Pets");
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        String userId = user.getUid();

        petReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    for (DataSnapshot dataSnapshot : task.getResult().getChildren()) {
                        PetData petDataFromFirebase = dataSnapshot.getValue(PetData.class);
                        if (userId != null) {
                            if (petDataFromFirebase != null) {
                                if (userId.equals(petDataFromFirebase.getCurrentUser())) {

                                    petNameList.add(petDataFromFirebase.getPet_name());
                                    animalList.add(petDataFromFirebase.getPet_animal());
                                }
                            }
                        }
                    }
                    executeListView(petNameList, animalList);
                }
            }
        });
    }

    private void executeListView(List<String> eventNamesList, List<String> dateList) {
        PetDB adapter = new PetDB(this, eventNamesList, dateList);
        ListView listView = (ListView) findViewById(R.id.list_pet);
        listView.setAdapter(adapter);
    }
}