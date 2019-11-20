package com.airbnbsql.airbnbsql.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.airbnbsql.airbnbsql.entities.User;
import com.airbnbsql.airbnbsql.entities.UserRowMapper;;


@Transactional
@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<User> getAll() {
        String sql = "SELECT * FROM user";
        RowMapper<User> rowMapper = new UserRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public User getUserId(int userId) {
        String sql = "SELECT * FROM user WHERE id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        User user = jdbcTemplate.queryForObject(sql, rowMapper, userId);
        return user;

    }

    public void addUser(User user){
        String sql = "INSERT INTO user (name, contactNo) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getContactNo());

        sql = "SELECT id FROM user WHERE name = ? and contactNo=?";
        int userId = jdbcTemplate.queryForObject(sql, Integer.class, user.getName(), user.getContactNo());
    
        //Set article id 
        user.setId(userId);
    }

    public void updateUser(int userId, User user){
        String sql = "UPDATE user SET name=?, contactNo=? WHERE id=?";
        jdbcTemplate.update(sql, user.getName(), user.getContactNo(), userId);
    }

    public boolean userExists(int userId){
        String sql = "SELECT count(*) FROM user WHERE id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, userId);
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void deleteUser(int userId){
        String sql = "DELETE FROM user WHERE id = ?";
        jdbcTemplate.update(sql, userId);
    }
}