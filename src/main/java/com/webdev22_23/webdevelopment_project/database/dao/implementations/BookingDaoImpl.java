package com.webdev22_23.webdevelopment_project.database.dao.implementations;

import com.webdev22_23.webdevelopment_project.database.dao.interfaces.BookingDao;
import com.webdev22_23.webdevelopment_project.database.model.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingDaoImpl implements BookingDao {

    private Connection conn;
    public BookingDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Booking> getAll() {
        String sql_str = "SELECT * FROM booking;";
        List<Booking> bookings = new ArrayList<>();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql_str);

            while(rs.next()){
                Booking booking = new Booking();
                booking.setBookingId((UUID)rs.getObject("booking_id"));
                booking.setRoom_id((UUID)rs.getObject("room_id"));
                booking.setRenter(rs.getString("renter"));
                booking.setDate_from(rs.getDate("date_from"));
                booking.setDate_to(rs.getDate("date_to"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookings;
    }

    @Override
    public Booking getByPrimaryKey(UUID uuid) {
        String sql_str = "SELECT * FROM booking WHERE booking_id = ?;";
        Booking booking = new Booking();

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setObject(1, uuid);
            ResultSet rs = st.executeQuery();
            rs.next();
            booking.setBookingId((UUID)rs.getObject("booking_id"));
            booking.setRoom_id((UUID)rs.getObject("room_id"));
            booking.setRenter(rs.getString("renter"));
            booking.setDate_from(rs.getDate("date_from"));
            booking.setDate_to(rs.getDate("date_to"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return booking;
    }

    @Override
    public void put(Booking booking) {
        String sql_str = "INSERT INTO booking VALUES (?,?,?,?,?);";

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setObject(1, booking.getBookingId());
            st.setObject(2, booking.getRoom_id());
            st.setString(3, booking.getRenter());
            st.setDate(4, booking.getDate_from());
            st.setDate(5, booking.getDate_to());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Booking booking) {
        String sql_str = "UPDATE booking SET room_id=?, renter=?, date_from=?, date_to=? WHERE booking_id=?;";

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setObject(1, booking.getRoom_id());
            st.setObject(2, booking.getRenter());
            st.setDate(3, booking.getDate_from());
            st.setDate(4, booking.getDate_to());
            st.setObject(5, booking.getBookingId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Booking booking) {
        String sql_str = "DELETE FROM booking WHERE booking_id=?;";

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setObject(1, booking.getBookingId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
