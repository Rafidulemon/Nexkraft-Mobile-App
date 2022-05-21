package com.example.nexkraft;

public class User {
    public String name,firstName,lastName,id,date,email,password;


    public User(){}
    public User(String fName, String lName, String id, String date, String email, String password) {
        this.firstName=fName;
        this.lastName=lName;
        this.id=id;
        this.date=date;
        this.email=email;
        this.password=password;
        this.name=fName+" "+lName;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
