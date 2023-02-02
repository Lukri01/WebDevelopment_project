package com.webdev22_23.webdevelopment_project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class IdGenerator {
    private static final String query = "SELECT uuid_generate_v1();";

    public static UUID getId(Connection conn){
        UUID uuid = null;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            uuid = (UUID) rs.getObject("uuid_generate_v1");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return uuid;
    }
}
