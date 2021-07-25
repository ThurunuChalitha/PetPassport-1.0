package com.example.pp;

public class PetData {
    public String pet_name,gender,breed,remarks;

    public PetData(){

    }

    public PetData(String pet_name, String gender, String breed, String remarks){
        this.pet_name=pet_name;
        this.gender=gender;
        this.breed=breed;
        this.remarks=remarks;

    }

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
}
