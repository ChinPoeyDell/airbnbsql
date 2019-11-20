package com.airbnbsql.airbnbsql.Controllers;

import java.util.List;

import com.airbnbsql.airbnbsql.entities.Payment;
import com.airbnbsql.airbnbsql.repositories.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PaymentController {

    @Autowired
    PaymentRepository repo;

    @GetMapping(value = "/payment")
    public List<Payment> index() {
        return repo.getAll();
    }
    
    @GetMapping(value="/payment/{id}")
    public Payment show(@PathVariable("id") int id){
        Payment booking = repo.getPaymentId(id);
        return booking;
    }
    
    @PostMapping(value="/payment/{id}")
    public void update(
        @PathVariable("id") int id, 
        @RequestBody Payment newPayment){
        repo.updatePayment(id,newPayment);
    }

    @GetMapping(value = "/testPayment/{id}")
    public Boolean exists(@PathVariable("id") int id){
        return repo.paymentExists(id);
    }

    @DeleteMapping(value = "/payment/{id}")
    public String delete(@PathVariable("id") int id){
        if(repo.paymentExists(id)){
            repo.deletePayment(id);
            return "Payment successfully deleted";
        } else {
            return "Paymnet doesn't exist";
        }
    }
}