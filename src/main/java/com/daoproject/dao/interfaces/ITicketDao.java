package com.daoproject.dao.interfaces;

import com.daoproject.entities.Ticket;

import java.util.List;

public interface ITicketDao {
    List<Ticket> findAll() throws Exception;
    Ticket findById(long id);
    Ticket post(Ticket ticket);
    Ticket update(long id, Ticket ticket);
    Ticket assign(long id, long master_id);
    void delete(long id);
}
