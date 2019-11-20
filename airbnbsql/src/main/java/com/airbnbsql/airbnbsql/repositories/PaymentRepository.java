package com.airbnbsql.airbnbsql.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.airbnbsql.airbnbsql.entities.Payment;
import com.airbnbsql.airbnbsql.entities.PaymentRowMapper;


@Transactional
@Repository
public class PaymentRepository {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PaymentRepository(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<Payment> getAll() {
        String sql = "SELECT * FROM payment";
        RowMapper<Payment> rowMapper = new PaymentRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public Payment getPaymentId(int paymentId) {
        String sql = "SELECT * FROM payment WHERE id = ?";
        RowMapper<Payment> rowMapper = new BeanPropertyRowMapper<Payment>(Payment.class);
        Payment payment = jdbcTemplate.queryForObject(sql, rowMapper, paymentId);
        return payment;

    }

    public void addPayment(Payment payment){
        String sql = "INSERT INTO payment (status, amount) VALUES (?, ?)";
        jdbcTemplate.update(sql, payment.getStatus(), payment.getAmount());

        sql = "SELECT id FROM payment WHERE status = ? and amount=?";
        int paymentId = jdbcTemplate.queryForObject(sql, Integer.class, payment.getStatus(), payment.getAmount());
    
        //Set article id 
        payment.setId(paymentId);
    }

    public void updatePayment(int paymentId, Payment payment){
        String sql = "UPDATE payment SET status=?, amount=? WHERE id=?";
        jdbcTemplate.update(sql, payment.getStatus(), payment.getAmount(), paymentId);
    }

    public boolean paymentExists(int paymentId){
        String sql = "SELECT count(*) FROM payment WHERE id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, paymentId);
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void deletePayment(int paymentId){
        String sql = "DELETE FROM payment WHERE id = ?";
        jdbcTemplate.update(sql, paymentId);
    }
}