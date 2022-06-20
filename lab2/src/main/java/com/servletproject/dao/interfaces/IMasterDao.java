package com.servletproject.dao.interfaces;

import com.servletproject.entities.Master;

import java.sql.SQLException;
import java.util.List;

public interface IMasterDao {
    List<Master> findAll() throws Exception;
    Master findById(long id) throws SQLException;
    Master findByName(String name) throws SQLException;
    Master post(Master master) throws SQLException;
    Master update(long id, Master master) throws SQLException;
    void delete(long id) throws SQLException;
}
