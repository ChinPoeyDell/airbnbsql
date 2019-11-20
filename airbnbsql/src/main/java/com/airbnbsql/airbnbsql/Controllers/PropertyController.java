package com.airbnbsql.airbnbsql.Controllers;

import java.util.List;

import com.airbnbsql.airbnbsql.entities.Property;
import com.airbnbsql.airbnbsql.repositories.PropertyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class PropertyController {

    @Autowired
    PropertyRepository repo;

    @GetMapping(value = "/property")
    public List<Property> index() {
        return repo.getAll();
    }
    
    @GetMapping(value="/property/{id}")
    public Property show(@PathVariable("id") int id){
        Property property = repo.getPropertyId(id);
        return property;
    }
    
    @PostMapping(value="/property/{id}")
    public void update(
        @PathVariable("id") int id, 
        @RequestBody Property newProperty){
        repo.updateProperty(id,newProperty);
    }

    @GetMapping(value = "/testProperty/{id}")
    public Boolean exists(@PathVariable("id") int id){
        return repo.propertyExists(id);
    }

    @DeleteMapping(value = "/property/{id}")
    public String delete(@PathVariable("id") int id){
        if(repo.propertyExists(id)){
            repo.deleteProperty(id);
            return "Property successfully deleted";
        } else {
            return "Property doesn't exist";
        }
    }
}