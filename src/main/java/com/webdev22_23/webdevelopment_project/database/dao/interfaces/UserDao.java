
package com.webdev22_23.webdevelopment_project.database.dao.interfaces;

import com.webdev22_23.webdevelopment_project.database.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();
    User getByPrimaryKey(String username);
    void put(User user);
    void update(User user);
    void delete(User user);
}
