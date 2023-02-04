
package com.webdev22_23.webdevelopment_project.database.dao.interfaces;

import com.webdev22_23.webdevelopment_project.database.model.Booking;

import java.util.List;
import java.util.UUID;

public interface BookingDao {
    List<Booking> getAll();
    Booking getByPrimaryKey(UUID uuid);
    void put(Booking booking);
    void update(Booking booking);
    void delete(Booking booking);
}
