package com.airbnbsql.airbnbsql.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.airbnbsql.airbnbsql.entities.Booking;
import com.airbnbsql.airbnbsql.entities.BookingRowMapper;

/**
 * BookingRepository
 */

@Transactional
@Repository
public class BookingRepository {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookingRepository(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<Booking> getAll() {
        String sql = "SELECT * FROM booking";
        RowMapper<Booking> rowMapper = new BookingRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public Booking getBookingId(int bookingId) {
        String sql = "SELECT * FROM booking WHERE id = ?";
        RowMapper<Booking> rowMapper = new BeanPropertyRowMapper<Booking>(Booking.class);
        Booking booking = jdbcTemplate.queryForObject(sql, rowMapper, bookingId);
        return booking;

    }

    public void addBooking(Booking booking){
        String sql = "INSERT INTO booking (price, confirmed) VALUES (?, ?)";
        jdbcTemplate.update(sql, booking.getPrice(), booking.getConfirmed());

        sql = "SELECT id FROM booking WHERE price = ? and confirmed=?";
        int bookingId = jdbcTemplate.queryForObject(sql, Integer.class, booking.getPrice(), booking.getConfirmed());
    
        //Set article id 
        booking.setId(bookingId);
    }

    public void updateBooking(int bookingId, Booking booking){
        String sql = "UPDATE booking SET price=?, confirmed=? WHERE id=?";
        jdbcTemplate.update(sql, booking.getPrice(), booking.getConfirmed(), bookingId);
    }

    public boolean bookingExists(int bookingId){
        String sql = "SELECT count(*) FROM booking WHERE id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, bookingId);
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void deleteBooking(int bookingId){
        String sql = "DELETE FROM booking WHERE id = ?";
        jdbcTemplate.update(sql, bookingId);
    }
}