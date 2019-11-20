package com.airbnbsql.airbnbsql.entities;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class PropertyRowMapper implements RowMapper<Property> {
    @Override
    public Property mapRow(ResultSet row, int rowNum) throws SQLException {
        Property x = new Property();
        x.setId(row.getInt("id"));
        x.setAddress(row.getString("address"));
        x.setLocation(row.getString("location"));
        return x;
    }
}