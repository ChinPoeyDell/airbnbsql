package com.airbnbsql.airbnbsql.entities;

/**
 * Property
 */
public class Property {
    public int id;
    public String address;
    public String location;


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    
    
}