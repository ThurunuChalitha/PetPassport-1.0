package com.example.pp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
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

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MyPets extends AppCompatActivity {

    private FirebaseDatabase rootNode;
    private DatabaseReference petReference;
    private FirebaseAuth auth;
    private FirebaseUser user;
    //private List<String> keyPetList = new ArrayList<>();
    private List<String> petNameList = new ArrayList<>();
    private List<String> animalList = new ArrayList<>();
    private List<String> bdList = new ArrayList<>();
    private List<String> breedList = new ArrayList<>();
    private List<String> genderList = new ArrayList<>();
    private List<String> remarksList = new ArrayList<>();
    private List<Bitmap> imageList = new ArrayList<>();
    PetDB adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pets);

        rootNode = FirebaseDatabase.getInstance();
        petReference = rootNode.getReference("Pets");
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        String userId = user.getUid();
        Bitmap commonImg = BitmapFactory.decodeResource(getResources(), R.drawable.doctor);

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
                                    bdList.add(petDataFromFirebase.getPet_bd());
                                    breedList.add(petDataFromFirebase.getBreed());
                                    genderList.add(petDataFromFirebase.getGender());
                                    remarksList.add(petDataFromFirebase.getRemarks());
                                    imageList.add(Bitmap.createScaledBitmap(commonImg, 500, 500, true));
                                }
                            } else{
                                Toast.makeText(MyPets.this,"No pet entries found, Add pets",Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    executeListView(petNameList, animalList, imageList,bdList,breedList,genderList,remarksList);
                }
            }
        });
    }

    private void executeListView(List<String> eventNamesList, List<String> dateList, List<Bitmap> imageList,
                                 List<String> bdList, List<String> breedList, List<String> genderList,List<String> remarksList ) {

        adapter = new PetDB(this, eventNamesList, dateList, imageList);
        ListView listView = (ListView) findViewById(R.id.list_pet);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent openAcivity = new Intent(MyPets.this, Pet_detail.class);
                openAcivity.putExtra("pet_Name", petNameList.get(position));
                openAcivity.putExtra("pet_Bd", bdList.get(position));
                openAcivity.putExtra("pet_Animal", animalList.get(position));
                openAcivity.putExtra("pet_Gender", genderList.get(position));
                openAcivity.putExtra("pet_Breed", breedList.get(position));
                openAcivity.putExtra("pet_Remarks", remarksList.get(position));

                //ByteArrayOutputStream stream = new ByteArrayOutputStream();
                //Bitmap bmp = bitmapArray.get(position);
                //bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                //byte[] byteArray = stream.toByteArray();
                //openAcivity.putExtra("image",byteArray);

                startActivity(openAcivity);

            }
        });
    }
}