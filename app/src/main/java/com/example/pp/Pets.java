package com.example.pp;

public class Pets {
    private String  pet_name;
    private String  birthday;
    private String  gender;
    private String  breed;
    private String  remarks;
    private String  pet_bd;

    public Pets() {

    }

    public Pets(String pet_name, String birthday, String gender, String breed, String remarks, String pet_bd) {
        this.pet_name = pet_name;
        this.birthday = birthday;
        this.gender = gender;
        this.breed = breed;
        this.remarks = remarks;
        this.pet_bd = pet_bd;
    }

    public String getPet_name() {
        return pet_name;
    }

    public void setPet_name(String pet_name) {
        this.pet_name = pet_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPet_bd() {
        return pet_bd;
    }

    public void setPet_bd(String pet_bd) {
        this.pet_bd = pet_bd;
    }
}


