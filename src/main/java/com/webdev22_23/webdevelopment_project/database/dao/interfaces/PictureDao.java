
package com.webdev22_23.webdevelopment_project.database.dao.interfaces;

import com.webdev22_23.webdevelopment_project.database.model.Picture;
import com.webdev22_23.webdevelopment_project.database.model.Room;

import java.util.List;
import java.util.UUID;

public interface PictureDao {
    List<Picture> getAll();
    Picture getByPrimaryKey(UUID uuid);
    void put(Picture picture);
    void update(Picture picture);
    void delete(Picture picture);
    List<Picture> getAllPicturesFromRoom(Room room);
}
