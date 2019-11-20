package com.airbnbsql.airbnbsql.entities;


// this class can be converted to JSON automatically
public class User {
    public int id;
    public String name;
    public String contactNo;
    

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return this.contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}