package com.example.pp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Pet_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail);

        Bundle bundle = getIntent().getExtras();
        String petNAME = bundle.getString("pet_Name");
        String pet_Bd = bundle.getString("pet_Bd");
        String pet_Animal = bundle.getString("pet_Animal");
        String pet_Gender = bundle.getString("pet_Gender");
        String pet_Breed = bundle.getString("pet_Breed");
        String pet_Remarks = bundle.getString("pet_Remarks");

        //byte[] byteArray = getIntent().getByteArrayExtra("image");
        //Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        TextView Name_petH = (TextView) findViewById(R.id.View_petName);
        TextView Name_pet = (TextView) findViewById(R.id.pet_name_view);
        TextView BD_pet_name = (TextView) findViewById(R.id.View_birthday);
        //ImageView ImgIcon = (ImageView) findViewById(R.id.ImgIcon);
        TextView Animal_pet = (TextView) findViewById(R.id.View_animal);
        TextView Gender_pet = (TextView) findViewById(R.id.View_gender);
        TextView Breed_pet = (TextView) findViewById(R.id.View_breed);
        TextView Remarks_pet = (TextView) findViewById(R.id.View_remarks);


        Name_pet.setText(petNAME);
        Name_petH.setText(petNAME);
        BD_pet_name.setText(pet_Bd );
        //ImgIcon.setImageBitmap(bmp);
        Animal_pet.setText(pet_Animal);
        Gender_pet.setText(pet_Gender);
        Breed_pet.setText(pet_Breed);
        Remarks_pet.setText(pet_Remarks);


    }
}