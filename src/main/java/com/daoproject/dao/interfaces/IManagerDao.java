package com.daoproject.dao.interfaces;

import com.daoproject.entities.Manager;

import java.util.List;

public interface IManagerDao {
    List<Manager> findAll() throws Exception;
    Manager findById(long id);
    Manager post(Manager manager);
    Manager update(long id, Manager manager);
    void delete(long id);
}
