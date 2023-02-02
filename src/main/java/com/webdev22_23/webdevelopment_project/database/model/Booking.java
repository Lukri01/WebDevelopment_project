package com.webdev22_23.webdevelopment_project.database.model;

import java.sql.Date;
import java.util.UUID;

public class Booking {
    private UUID booking_id;
    private UUID room_id;
    private String renter;
    private Date date_from;
    private Date date_to;

    public UUID getBookingId() {
        return booking_id;
    }

    public void setBookingId(UUID booking_id) {
        this.booking_id = booking_id;
    }

    public UUID getRoom_id() {
        return room_id;
    }

    public void setRoom_id(UUID room_id) {
        this.room_id = room_id;
    }

    public String getRenter() {
        return renter;
    }

    public void setRenter(String renter) {
        this.renter = renter;
    }

    public Date getDate_from() {
        return date_from;
    }

    public void setDate_from(Date date_from) {
        this.date_from = date_from;
    }

    public Date getDate_to() {
        return date_to;
    }

    public void setDate_to(Date date_to) {
        this.date_to = date_to;
    }
}
