package com.servletproject.services;

import com.servletproject.dao.DaoFactory;
import com.servletproject.entities.Master;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class MasterService {
    public boolean checkMaster(String username, String password) {
        Master user = find(username);
        if (user == null) return false;
        return (Objects.equals(password, user.getMasterPassword()));
    }

    public Master find(int id) {
        Master user;
        try {
            user = DaoFactory.createMasterDao().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public Master find(String username) {
        try {
            return  DaoFactory.createMasterDao().findByName(username);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Master> getAllUsers() {
        try {
            return DaoFactory.createMasterDao().findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public long createMaster(Master user) {
        try {
            DaoFactory.createMasterDao().post(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return user.getId();
    }
}
