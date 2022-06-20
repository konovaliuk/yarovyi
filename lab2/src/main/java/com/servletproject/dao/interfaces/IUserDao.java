package com.servletproject.dao.interfaces;

import com.servletproject.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    List<User> findAll() throws Exception;
    User findById(long id) throws SQLException;
    User findByName(String name) throws SQLException;
    User post(User user) throws SQLException;
    User update(long id, User user) throws SQLException;
    void delete(long id) throws SQLException;
    void delete_by_name(String name);
}
