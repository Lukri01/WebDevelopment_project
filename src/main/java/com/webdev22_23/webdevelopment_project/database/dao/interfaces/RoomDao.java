package com.webdev22_23.webdevelopment_project.database.dao.interfaces;

import com.webdev22_23.webdevelopment_project.database.model.Room;
import com.webdev22_23.webdevelopment_project.database.model.User;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface RoomDao {
    List<Room> getAll();
    Room getByPrimaryKey(UUID uuid);
    void put(Room room);
    void update(Room room);
    void delete(Room room);
    List<Room> getAllRoomsFromUser(User user);
    List<Room> getAllEmptyRoomsForLocationAndDate(String location, Date date_from, Date date_to);
}
