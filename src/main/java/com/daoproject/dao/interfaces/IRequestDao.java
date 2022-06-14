package com.daoproject.dao.interfaces;

import com.daoproject.entities.Request;

import java.util.List;

public interface IRequestDao {
    List<Request> findAll() throws Exception;
    Request findById(long id);
    Request post(Request request);
    Request update(long id, Request request);
    Request assign(long id, long manager_id);
    void delete(long id);
}
