package com.webdev22_23.webdevelopment_project.database.dao.implementations;

import com.webdev22_23.webdevelopment_project.database.dao.interfaces.UserDao;
import com.webdev22_23.webdevelopment_project.database.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private Connection conn;
    public UserDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<User> getAll() {
        String sql_str = "SELECT * FROM \"user\";";
        List<User> users = new ArrayList<>();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql_str);

            while(rs.next()){
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("first_name"));
                user.setFirstName(rs.getString("last_name"));
                user.setFirstName(rs.getString("user_type"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User getByPrimaryKey(String username) {
        String query = "SELECT * FROM \"user\" WHERE username = ?;";
        User user = new User();

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            rs.next();
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setFirstName(rs.getString("first_name"));
            user.setFirstName(rs.getString("last_name"));
            user.setFirstName(rs.getString("user_type"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void put(User user) {
        String sql_str = "INSERT INTO \"user\" VALUES (?,?,?,?,?,?);";

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getEmail());
            st.setString(4, user.getFirstName());
            st.setString(5, user.getLastName());
            st.setString(6, user.getUserType());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        String sql_str = "UPDATE \"user\" SET password=?, email=?, first_name=?, last_name=?, user_type=? WHERE username=?;";

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setString(1, user.getPassword());
            st.setString(2, user.getEmail());
            st.setString(3, user.getFirstName());
            st.setString(4, user.getLastName());
            st.setString(5, user.getUserType());
            st.setString(6, user.getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User user) {
        String sql_str = "DELETE FROM \"user\" WHERE username=?;";

        try {
            PreparedStatement st = conn.prepareStatement(sql_str);
            st.setString(1, user.getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
