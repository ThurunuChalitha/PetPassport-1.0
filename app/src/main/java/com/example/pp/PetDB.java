package com.example.pp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
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
    private final List<String> eventNamesList;
    private final List<String> dateList;

    public PetDB(Activity context, List<String> eventNamesList,
                                            List<String> dateList)
    {
        super(context, R.layout.pet_item, eventNamesList);

        this.context = context;
        this.eventNamesList = eventNamesList;
        this.dateList = dateList;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.pet_item, null, true);
        TextView petN = (TextView) rowView.findViewById(R.id.TpetName);
        TextView AnimalT = (TextView) rowView.findViewById(R.id.Atype);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.admin_view_event_image);

        petN.setText(eventNamesList.get(position));
        AnimalT.setText(dateList.get(position));
        //imageView.setImageBitmap(imageList.get(position));
        return rowView;
    }
}
