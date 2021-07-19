package com.example.pp;

public class UserData {

    public String user_name,nic_no,address,phone_no,email,usr_name,password,re_password;

    public UserData() {

    }

    public UserData(String user_name,String nic_no,String address,String phone_no,String email,String usr_name){
        this.user_name=user_name;
        this.nic_no=nic_no;
        this.address=address;
        this.phone_no=phone_no;
        this.email=email;
        this.usr_name=usr_name;
       // this.password=password;
       // this.re_password=re_password;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getNic_no() {
        return nic_no;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getEmail() {
        return email;
    }

    public String getUsr_name() { return usr_name;}

}

