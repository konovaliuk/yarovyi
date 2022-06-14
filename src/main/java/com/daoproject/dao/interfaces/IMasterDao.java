package com.daoproject.dao.interfaces;

import com.daoproject.entities.Master;

import java.util.List;

public interface IMasterDao {
    List<Master> findAll() throws Exception;
    Master findById(long id);
    Master post(Master master);
    Master update(long id, Master master);
    void delete(long id);
}
