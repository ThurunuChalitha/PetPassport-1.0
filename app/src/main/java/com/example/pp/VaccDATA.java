package com.example.pp;

import com.google.firebase.auth.FirebaseUser;

public class VaccDATA {
    public String DueDateV,VaccineType,pet_idV,VDate;

    public VaccDATA(){

    }

    public VaccDATA(String DueDateV, String VaccineType, String pet_idV, String VDate){
        this.DueDateV=DueDateV;
        this.VaccineType=VaccineType;
        this.pet_idV=pet_idV;
        this.VDate=VDate;

    }

    public String getDueDateV() {
        return DueDateV;
    }

    public String getVaccineType() {
        return VaccineType;
    }

    public String getPet_idV() {
        return pet_idV;
    }

    public String getVDate() {
        return VDate;
    }
}
