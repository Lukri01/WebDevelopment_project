package com.webdev22_23.webdevelopment_project.database.dao.implementations;

import com.webdev22_23.webdevelopment_project.database.dao.interfaces.RoomDao;
import com.webdev22_23.webdevelopment_project.database.model.Room;
import com.webdev22_23.webdevelopment_project.database.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomDaoImpl implements RoomDao {

    private Connection conn;
    public RoomDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Room> getAll() {
        String sql_str = "SELECT * FROM room;";
        List<Room> rooms = new ArrayList<>();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql_str);

            while(rs.next()){
                Room room = new Room();
                room.setRoomId((UUID)rs.getObject("room_id"));
                room.setPrice(rs.getFloat("price"));
                room.setTitle(rs.getString("title"));
                room.setBody(rs.getString("body"));
                room.setLocation(rs.getString("location"));
                room.setLandlord(rs.getString("landlord"));
                room.setBeds(rs.getInt("beds"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rooms;
    }

    @Override
    public Room getByPrimaryKey(UUID uuid) {
        String sql_str = "SELECT * FROM room WHERE room_id = ?;";
        Room room = new Room();

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setObject(1, uuid);
            ResultSet rs = st.executeQuery();
            rs.next();
            room.setRoomId((UUID)rs.getObject("room_id"));
            room.setPrice(rs.getFloat("price"));
            room.setTitle(rs.getString("title"));
            room.setBody(rs.getString("body"));
            room.setLocation(rs.getString("location"));
            room.setLandlord(rs.getString("landlord"));
            room.setBeds(rs.getInt("beds"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return room;
    }

    @Override
    public void put(Room room) {
        String sql_str = "INSERT INTO room VALUES (?,?,?,?,?,?,?,?);";

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setObject(1, room.getRoomId());
            st.setFloat(2, room.getPrice());
            st.setString(3, room.getTitle());
            st.setString(4, room.getBody());
            st.setString(5, room.getLocation());
            st.setString(6, room.getLandlord());
            st.setInt(7, room.getBeds());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Room room) {
        String sql_str = "UPDATE room SET price=?, title=?, body=?, location=?, landlord=?, beds=? WHERE room_id=?;";

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setFloat(1, room.getPrice());
            st.setString(2, room.getTitle());
            st.setString(3, room.getBody());
            st.setString(4, room.getLocation());
            st.setString(5, room.getLandlord());
            st.setInt(6, room.getBeds());
            st.setObject(7, room.getRoomId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Room room) {
        String sql_str = "DELETE FROM room WHERE room_id=?;";

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setObject(1, room.getRoomId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Room> getAllRoomsFromUser(User user) {
        String sql_str = "SELECT * FROM room r WHERE r.landlord=?;";
        List<Room> rooms = new ArrayList<>();

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setObject(1, user.getUsername());
            ResultSet rs = st.executeQuery(sql_str);

            while(rs.next()){
                Room room = new Room();
                room.setRoomId((UUID)rs.getObject("room_id"));
                room.setPrice(rs.getFloat("price"));
                room.setTitle(rs.getString("title"));
                room.setBody(rs.getString("body"));
                room.setLocation(rs.getString("location"));
                room.setLandlord(rs.getString("landlord"));
                room.setBeds(rs.getInt("beds"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rooms;
    }

    @Override
    public List<Room> getAllEmptyRoomsForLocationAndDate(String location, Date date_from, Date date_to) {
        String sql_str = "SELECT r.* FROM room r, booking b WHERE r.\"location\" = ? EXCEPT SELECT DISTINCT r.* FROM room r, booking b  WHERE b.room_id = r.room_id AND NOT (? < b.date_from AND ? < b.date_to) OR (? > b.date_from AND ? > b.date_to);";
        List<Room> rooms = new ArrayList<>();

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setString(1, location);
            st.setDate(2, date_from);
            st.setDate(3, date_to);
            st.setDate(4, date_from);
            st.setDate(5, date_to);
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                Room room = new Room();
                room.setRoomId((UUID)rs.getObject("room_id"));
                room.setPrice(rs.getFloat("price"));
                room.setTitle(rs.getString("title"));
                room.setBody(rs.getString("body"));
                room.setLocation(rs.getString("location"));
                room.setLandlord(rs.getString("landlord"));
                room.setBeds(rs.getInt("beds"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rooms;
    }
}
