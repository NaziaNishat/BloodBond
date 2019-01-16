package com.example.nazia_000.account.classPack;

public class ProfilesClass {

    String name, age, number, blood_group, status, address;

    public ProfilesClass(){

    }

    public ProfilesClass(String name, String age, String number,String blood_group,String status,String address){
        this.name = name;
        this.age = age;
        this.number = number;
        this.blood_group = blood_group;
        this.status = status;
        this.address = address;
    }



    public String getname() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getaddress() { return address; }

    public String getblood_group() { return blood_group; }

    public String getStatus() { return status; }

    public String getnumber() {
        return number;
    }


}
