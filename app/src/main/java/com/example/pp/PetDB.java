package com.example.pp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PetDB extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> Pet_List;
    private final List<String> Type_List;
    private final List<Bitmap> imageList;


    public PetDB(Activity context, List<String> Pet_List, List<String> Type_List, List<Bitmap> imageList) {
        super(context, R.layout.pet_item, Pet_List);

        this.context = context;
        this.Pet_List = Pet_List;
        this.Type_List = Type_List;
        this.imageList = imageList;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.pet_item, null, true);
        TextView petN = (TextView) rowView.findViewById(R.id.TpetName);
        TextView AnimalT = (TextView) rowView.findViewById(R.id.Atype);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imagePet);

        petN.setText(Pet_List.get(position));
        AnimalT.setText(Type_List.get(position));
        imageView.setImageBitmap(imageList.get(position));
        return rowView;
    }
}
