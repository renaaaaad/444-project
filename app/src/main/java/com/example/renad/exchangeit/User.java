package com.example.renad.exchangeit;

public class User {
    private String id;
    private String Fname;
    private String Lname;
    private String phoneNumber;
    private String city;

    private String email;

public User(String i  ,String f,String l,String p, String e ,String c){
    id = i;
    Fname=f;
    Lname=l;
    phoneNumber=p;
    email=e;
    city=c;

    //lalalalala
}
  public String getPhoneNumber() {
        return phoneNumber;
    }



    public String getEmail() {
        return email;
    }

    public String getFname() {
        return Fname;
    }



    public String getLname() {
        return Lname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}// the class

