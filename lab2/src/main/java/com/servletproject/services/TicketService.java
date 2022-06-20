package com.servletproject.services;

import com.servletproject.dao.DaoFactory;
import com.servletproject.entities.Ticket;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class TicketService {
    public Ticket find(int id) {
        Ticket request;
        try {
            request = DaoFactory.createTicketDao().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return request;
    }


    public List<Ticket> getAllTickets() {
        try {
            return DaoFactory.createTicketDao().findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public long createTicket(Ticket ticket) {
        try {
            DaoFactory.createTicketDao().post(ticket);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return ticket.getId();
    }

    public void assignTicket(long id, long master){
        try{
            DaoFactory.createTicketDao().assign(id, master);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
