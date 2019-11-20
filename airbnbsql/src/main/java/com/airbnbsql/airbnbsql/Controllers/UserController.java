package com.airbnbsql.airbnbsql.Controllers;

import java.util.List;

import com.airbnbsql.airbnbsql.entities.User;
import com.airbnbsql.airbnbsql.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class UserController {

    @Autowired
    UserRepository repo;

    @GetMapping(value = "/user")
    public List<User> index() {
        return repo.getAll();
    }
    
    @GetMapping(value="/user/{id}")
    public User show(@PathVariable("id") int id){
        User user = repo.getUserId(id);
        return user;
    }
    
    @PostMapping(value="/user/{id}")
    public void update(
        @PathVariable("id") int id, 
        @RequestBody User newUser){
        repo.updateUser(id,newUser);
    }

    @GetMapping(value = "/testUser/{id}")
    public Boolean exists(@PathVariable("id") int id){
        return repo.userExists(id);
    }

    @DeleteMapping(value = "/user/{id}")
    public String delete(@PathVariable("id") int id){
        if(repo.userExists(id)){
            repo.deleteUser(id);
            return "User successfully deleted";
        } else {
            return "User doesn't exist";
        }
    }
}