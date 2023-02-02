package com.webdev22_23.webdevelopment_project.database.dao.interfaces;

import com.webdev22_23.webdevelopment_project.database.model.Review;
import com.webdev22_23.webdevelopment_project.database.model.Room;
import com.webdev22_23.webdevelopment_project.database.model.User;

import java.util.List;
import java.util.UUID;

public interface ReviewDao {
    List<Review> getAll();
    Review getByPrimaryKey(UUID uuid);
    void put(Review review);
    void update(Review review);
    void delete(Review review);
    List<Review> getAllReviewsForRoom(Room room);
    List<Review> getAllReviewsFromUser(User user);
}
