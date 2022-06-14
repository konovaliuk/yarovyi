package com.daoproject.dao.interfaces;

import com.daoproject.entities.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll() throws Exception;
    User findById(long id);
    User post(User user);
    User update(long id, User user);
    void delete(long id);
    void delete_by_name(String name);
}
