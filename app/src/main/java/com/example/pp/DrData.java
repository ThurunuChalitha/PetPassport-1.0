package com.example.pp;

public class DrData {
    public String dr_name,slva_no,vet_office,dr_phone_no,dr_email,dr_usr_name;

    public DrData() {

    }

    public DrData(String dr_name,String slva_no,String vet_office,String dr_phone_no,String dr_email,String dr_usr_name){
        this.dr_name=dr_name;
        this.slva_no=slva_no;
        this.vet_office=vet_office;
        this.dr_phone_no=dr_phone_no;
        this.dr_email=dr_email;
        this.dr_usr_name=dr_usr_name;

    }

    public String getDr_name() {
        return dr_name;
    }

    public String getSlva_no() {
        return slva_no;
    }

    public String getVet_office() {
        return vet_office;
    }

    public String getDr_phone_no() {
        return dr_phone_no;
    }

    public String getDr_email() {
        return dr_email;
    }

    public String getDr_usr_name() {
        return dr_usr_name;
    }
}
