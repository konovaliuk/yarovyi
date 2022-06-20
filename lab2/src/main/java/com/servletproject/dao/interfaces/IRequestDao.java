package com.servletproject.dao.interfaces;

import com.servletproject.entities.Request;

import java.sql.SQLException;
import java.util.List;

public interface IRequestDao {
    List<Request> findAll() throws Exception;
    Request findById(long id) throws SQLException;
    Request post(Request request) throws SQLException;
    Request update(long id, Request request) throws SQLException;
    Request assign(long id, long manager_id) throws SQLException;
    void delete(long id) throws SQLException;
}
