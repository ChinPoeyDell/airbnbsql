package com.airbnbsql.airbnbsql.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.airbnbsql.airbnbsql.entities.Property;
import com.airbnbsql.airbnbsql.entities.PropertyRowMapper;


@Transactional
@Repository
public class PropertyRepository {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PropertyRepository(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<Property> getAll() {
        String sql = "SELECT * FROM property";
        RowMapper<Property> rowMapper = new PropertyRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public Property getPropertyId(int propertyId) {
        String sql = "SELECT * FROM property WHERE id = ?";
        RowMapper<Property> rowMapper = new BeanPropertyRowMapper<Property>(Property.class);
        Property property = jdbcTemplate.queryForObject(sql, rowMapper, propertyId);
        return property;

    }

    public void addProperty(Property property){
        String sql = "INSERT INTO property (address, location) VALUES (?, ?)";
        jdbcTemplate.update(sql, property.getAddress(), property.getLocation());

        sql = "SELECT id FROM property WHERE price = ? and confirmed=?";
        int propertyId = jdbcTemplate.queryForObject(sql, Integer.class, property.getAddress(), property.getLocation());
    
        //Set article id 
        property.setId(propertyId);
    }

    public void updateProperty(int propertyId, Property property){
        String sql = "UPDATE property SET address=?, location=? WHERE id=?";
        jdbcTemplate.update(sql, property.getAddress(), property.getLocation(), propertyId);
    }

    public boolean propertyExists(int propertyId){
        String sql = "SELECT count(*) FROM property WHERE id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, propertyId);
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void deleteProperty(int propertyId){
        String sql = "DELETE FROM property WHERE id = ?";
        jdbcTemplate.update(sql, propertyId);
    }
}