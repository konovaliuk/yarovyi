package com.servletproject.dao.interfaces;

import com.servletproject.entities.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface ITicketDao {
    List<Ticket> findAll() throws Exception;
    Ticket findById(long id) throws SQLException;
    Ticket post(Ticket ticket) throws SQLException;
    Ticket update(long id, Ticket ticket) throws SQLException;
    Ticket assign(long id, long master_id) throws SQLException;
    void delete(long id) throws SQLException;
}
