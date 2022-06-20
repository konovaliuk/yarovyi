package com.servletproject.services;

import com.servletproject.dao.UserDao;
import com.servletproject.entities.User;
import com.servletproject.dao.DaoFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class UserService {
    public boolean checkUser(String username, String password) {
        User user = find(username);
        if (user == null) return false;
        return (Objects.equals(password, user.getUserPassword()));
    }

    public User find(int id) {
        User user;
        try {
            user = DaoFactory.createUserDao().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public User find(String username) {
        try {
            return  DaoFactory.createUserDao().findByName(username);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getAllUsers() {
        try {
            return DaoFactory.createUserDao().findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public long createUser(User user) {
        try {
            DaoFactory.createUserDao().post(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return user.getId();
    }
}
