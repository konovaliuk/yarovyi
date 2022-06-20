package com.servletproject.dao.interfaces;

import com.servletproject.entities.Manager;

import java.sql.SQLException;
import java.util.List;

public interface IManagerDao {
    List<Manager> findAll() throws Exception;
    Manager findById(long id) throws SQLException;
    Manager findByName(String name) throws SQLException;
    Manager post(Manager manager) throws SQLException;
    Manager update(long id, Manager manager) throws SQLException;
    void delete(long id) throws SQLException;
}
