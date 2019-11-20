package com.airbnbsql.airbnbsql.entities;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet row, int rowNum) throws SQLException {
        User x = new User();
        x.setId(row.getInt("id"));
        x.setName(row.getString("name"));
        x.setContactNo(row.getString("contactNo"));
        return x;
    }
}