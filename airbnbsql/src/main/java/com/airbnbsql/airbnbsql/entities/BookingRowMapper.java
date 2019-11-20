package com.airbnbsql.airbnbsql.entities;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class BookingRowMapper implements RowMapper<Booking> {
    @Override
    public Booking mapRow(ResultSet row, int rowNum) throws SQLException {
        Booking x = new Booking();

        x.setId(row.getInt("id"));
        x.setPrice(row.getInt("price"));
        x.setConfirmed(row.getBoolean("confirmed"));
        return x;
    }
}