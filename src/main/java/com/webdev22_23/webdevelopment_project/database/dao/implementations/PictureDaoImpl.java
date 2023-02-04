
package com.webdev22_23.webdevelopment_project.database.dao.implementations;

import com.webdev22_23.webdevelopment_project.database.dao.interfaces.PictureDao;
import com.webdev22_23.webdevelopment_project.database.model.Picture;
import com.webdev22_23.webdevelopment_project.database.model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PictureDaoImpl implements PictureDao {

    private Connection conn;
    public PictureDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Picture> getAll() {
        String sql_str = "SELECT * FROM picture;";
        List<Picture> pictures = new ArrayList<>();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql_str);

            while(rs.next()){
                Picture picture = new Picture();
                picture.setPictureId((UUID)rs.getObject("picture_id"));
                picture.setData(rs.getString("data"));
                pictures.add(picture);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pictures;
    }

    @Override
    public Picture getByPrimaryKey(UUID uuid) {
        String sql_str = "SELECT * FROM picture WHERE picture_id = ?;";
        Picture picture = new Picture();

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setObject(1, uuid);
            ResultSet rs = st.executeQuery();
            rs.next();
            picture.setPictureId((UUID)rs.getObject("picture_id"));
            picture.setData(rs.getString("data"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return picture;
    }

    @Override
    public void put(Picture picture) {
        String sql_str = "INSERT INTO picture VALUES (?,?);";

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setObject(1, picture.getPictureId());
            st.setString(2, picture.getData());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Picture picture) {
        String sql_str = "UPDATE picture SET data=? WHERE picture_id=?;";

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setString(1, picture.getData());
            st.setObject(2, picture.getPictureId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Picture picture) {
        String sql_str = "DELETE FROM picture WHERE picture_id=?;";

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setObject(1, picture.getPictureId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Picture> getAllPicturesFromRoom(Room room) {
        String sql_str = "SELECT p.picture_id, p.\"data\" FROM picture p, room_picture rp WHERE ?=rp.room_id AND rp.picture_id=p.picture_id;";
        List<Picture> pictures = new ArrayList<>();

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setObject(1, room.getRoomId());
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                Picture picture = new Picture();
                picture.setPictureId((UUID)rs.getObject("picture_id"));
                picture.setData(rs.getString("data"));
                pictures.add(picture);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pictures;
    }
}
