package com.webdev22_23.webdevelopment_project.database.dao.implementations;

import com.webdev22_23.webdevelopment_project.database.dao.interfaces.ReviewDao;
import com.webdev22_23.webdevelopment_project.database.model.Review;
import com.webdev22_23.webdevelopment_project.database.model.Room;
import com.webdev22_23.webdevelopment_project.database.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReviewDaoImpl implements ReviewDao {

    private Connection conn;
    public ReviewDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Review> getAll() {
        String sql_str = "SELECT * FROM review;";
        List<Review> reviews = new ArrayList<>();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql_str);

            while(rs.next()){
                Review review = new Review();
                review.setReviewId((UUID)rs.getObject("review_id"));
                review.setTitle(rs.getString("title"));
                review.setBody(rs.getString("body"));
                review.setRating(rs.getFloat("rating"));
                review.setWrittenBy(rs.getString("written_by"));
                review.setReferedTo((UUID)rs.getObject("refered_to"));
                reviews.add(review);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }

    @Override
    public Review getByPrimaryKey(UUID uuid) {
        String sql_str = "SELECT * FROM review WHERE review_id = ?;";
        Review review = new Review();

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setObject(1, uuid);
            ResultSet rs = st.executeQuery();
            rs.next();
            review.setReviewId((UUID)rs.getObject("review_id"));
            review.setTitle(rs.getString("title"));
            review.setBody(rs.getString("body"));
            review.setRating(rs.getFloat("rating"));
            review.setWrittenBy(rs.getString("written_by"));
            review.setReferedTo((UUID)rs.getObject("refered_to"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return review;
    }

    @Override
    public void put(Review review) {
        String sql_str = "INSERT INTO review VALUES (?,?,?,?,?,?);";

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setObject(1, review.getReviewId());
            st.setString(2, review.getTitle());
            st.setString(3, review.getBody());
            st.setFloat(4, review.getRating());
            st.setString(5, review.getWrittenBy());
            st.setObject(6, review.getReferedTo());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Review review) {
        String sql_str = "UPDATE review SET title=?, body=?, rating=?, written_by=?, refered_to=? WHERE review_id=?;";

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setString(1, review.getTitle());
            st.setString(2, review.getBody());
            st.setFloat(3, review.getRating());
            st.setString(4, review.getWrittenBy());
            st.setObject(5, review.getReferedTo());
            st.setObject(6, review.getReviewId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Review review) {
        String sql_str = "DELETE FROM review WHERE review_id=?;";

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setObject(1, review.getReviewId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Review> getAllReviewsForRoom(Room room) {
        String sql_str = "SELECT * FROM review WHERE review.refered_to=?;";
        List<Review> reviews = new ArrayList<>();

        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(sql_str);
            st.setObject(1, room.getRoomId());
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                Review review = new Review();
                review.setReviewId((UUID)rs.getObject("review_id"));
                review.setTitle(rs.getString("title"));
                review.setBody(rs.getString("body"));
                review.setRating(rs.getFloat("rating"));
                review.setWrittenBy(rs.getString("written_by"));
                review.setReferedTo((UUID)rs.getObject("refered_to"));
                reviews.add(review);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }

    @Override
    public List<Review> getAllReviewsFromUser(User user) {
        String sql_str = "SELECT * FROM review WHERE review.written_by=?;";
        List<Review> reviews = new ArrayList<>();

        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(sql_str);
            st.setObject(1, user.getUsername());
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                Review review = new Review();
                review.setReviewId((UUID)rs.getObject("review_id"));
                review.setTitle(rs.getString("title"));
                review.setBody(rs.getString("body"));
                review.setRating(rs.getFloat("rating"));
                review.setWrittenBy(rs.getString("written_by"));
                review.setReferedTo((UUID)rs.getObject("refered_to"));
                reviews.add(review);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }
}
