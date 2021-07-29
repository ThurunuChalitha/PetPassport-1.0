package com.example.pp;

import com.google.firebase.auth.FirebaseUser;

public class PetData {
    public String pet_name,gender,breed,remarks,pet_bd,currentUser;

    public PetData(){

    }

    public PetData(String pet_name, String gender, String breed, String remarks,String pet_bd,String currentUser){
        this.pet_name=pet_name;
        this.gender=gender;
        this.breed=breed;
        this.remarks=remarks;
        this.currentUser=currentUser;


    }


    public String getPet_bd() { return pet_bd; }

    public String getPet_name() {
        return pet_name;
    }

    public String getGender() {
        return gender;
    }

    public String getBreed() {
        return breed;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getCurrentUser() { return currentUser; }
}
