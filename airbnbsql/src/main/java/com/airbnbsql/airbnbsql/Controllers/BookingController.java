package com.airbnbsql.airbnbsql.Controllers;

import java.util.List;

import com.airbnbsql.airbnbsql.entities.Booking;
import com.airbnbsql.airbnbsql.repositories.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class BookingController {

    @Autowired
    BookingRepository repo;

    @GetMapping(value = "/booking")
    public List<Booking> index() {
        return repo.getAll();
    }
    
    @GetMapping(value="/booking/{id}")
    public Booking show(@PathVariable("id") int id){
        Booking booking = repo.getBookingId(id);
        return booking;
    }
    
    @PostMapping(value="/booking/{id}")
    public void update(
        @PathVariable("id") int id, 
        @RequestBody Booking newBooking){
        repo.updateBooking(id,newBooking);
    }

    @GetMapping(value = "/testBooking/{id}")
    public Boolean exists(@PathVariable("id") int id){
        return repo.bookingExists(id);
    }

    @DeleteMapping(value = "/booking/{id}")
    public String delete(@PathVariable("id") int id){
        if(repo.bookingExists(id)){
            repo.deleteBooking(id);
            return "Booking successfully deleted";
        } else {
            return "Booking doesn't exist";
        }
    }
}