package com.servletproject.services;

import com.servletproject.dao.DaoFactory;
import com.servletproject.entities.Manager;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class ManagerService {
    public boolean checkManager(String username, String password) {
        Manager manager = find(username);
        if (manager == null) return false;
        return (Objects.equals(password, manager.getManagerPassword()));
    }

    public Manager find(int id) {
        Manager user;
        try {
            user = DaoFactory.createManagerDao().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public Manager find(String username) {
        try {
            return  DaoFactory.createManagerDao().findByName(username);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Manager> getAllUsers() {
        try {
            return DaoFactory.createManagerDao().findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public long createManager(Manager user) {
        try {
            DaoFactory.createManagerDao().post(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return user.getId();
    }
}
