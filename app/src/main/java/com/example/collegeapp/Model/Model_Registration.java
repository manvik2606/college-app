package com.example.collegeapp.Model;

public class Model_Registration {

    public String firstname;
     public String email;

    public  Model_Registration(){

    }

    public  Model_Registration(String firstname, String email) {
        this.firstname = firstname;
         this.email = email;
     }



    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
