
package com.webdev22_23.webdevelopment_project.database;

import com.webdev22_23.webdevelopment_project.database.dao.implementations.PictureDaoImpl;
import com.webdev22_23.webdevelopment_project.database.dao.implementations.ReviewDaoImpl;
import com.webdev22_23.webdevelopment_project.database.dao.implementations.RoomDaoImpl;
import com.webdev22_23.webdevelopment_project.database.dao.implementations.UserDaoImpl;
import com.webdev22_23.webdevelopment_project.database.dao.interfaces.PictureDao;
import com.webdev22_23.webdevelopment_project.database.dao.interfaces.ReviewDao;
import com.webdev22_23.webdevelopment_project.database.dao.interfaces.RoomDao;
import com.webdev22_23.webdevelopment_project.database.dao.interfaces.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static ConnectionManager connectionManager = null;
    private Connection conn = null;
    private static final String url = "jdbc:postgresql://localhost:5432/user=postgres&password=postgres";

    private ConnectionManager(){}

    public static ConnectionManager getInstance(){
        if(connectionManager == null){
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }

    public Connection getConnection(){
        if(conn == null){
            try {
                conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }

    public PictureDao getPictureDao(){
        return new PictureDaoImpl(getConnection());
    }

    public ReviewDao getReviewDao(){
        return new ReviewDaoImpl(getConnection());
    }

    public RoomDao getRoomDao(){
        return new RoomDaoImpl(getConnection());
    }

    public UserDao getUserDao(){
        return new UserDaoImpl(getConnection());
    }
}
