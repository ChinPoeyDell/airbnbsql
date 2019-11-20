package com.airbnbsql.airbnbsql.entities;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class PaymentRowMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet row, int rowNum) throws SQLException {
        Payment x = new Payment();

        x.setId(row.getInt("id"));
        x.setStatus(row.getBoolean("status"));
        x.setAmount(row.getInt("amount"));
        return x;
    }
}