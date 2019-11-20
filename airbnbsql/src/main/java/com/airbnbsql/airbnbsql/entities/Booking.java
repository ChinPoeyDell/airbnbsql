package com.airbnbsql.airbnbsql.entities;



/**
 * Booking
 */
public class Booking {
    public int id;
    public int price;
    public boolean confirmed;



    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isConfirmed() {
        return this.confirmed;
    }

    public boolean getConfirmed() {
        return this.confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }



    
}