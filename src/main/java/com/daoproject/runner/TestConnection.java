package com.daoproject.runner;

import com.daoproject.connection.*;
import com.daoproject.dao.DaoFactory;
import com.daoproject.dao.interfaces.ITicketDao;
import com.daoproject.dao.interfaces.IUserDao;
import com.daoproject.entities.Ticket;
import com.daoproject.entities.User;

import java.sql.SQLException;
import java.util.List;

public class TestConnection {

    private ConnectionPool connect(String url, String login, String password) throws SQLException{
        ConnectionPool connectionPool = ConnectionPool.create(url, login, password);
        return connectionPool;
    }

    public static void main(String[] args) throws Exception{
        TestConnection app = new TestConnection();
        ConnectionPool pool = app.connect("jdbc:mysql://localhost:3306/pis_db", "root", "password");
        IUserDao iUserDao = DaoFactory.createUserDao(pool);
        User newUser = new User("John Doe","doe.jo@gmail.com","johndoe_99","qwerty");
        iUserDao.post(newUser);
        User anotherUser = new User("Bob Bobson", "b.ob@yahoo.com", "bb_bbs", "ytrewq");
        iUserDao.post(anotherUser);
        List<User> userList = iUserDao.findAll();
        System.out.println(userList);
        ITicketDao iTicketDao = DaoFactory.createTicketDao(pool);
        List<Ticket> ticketList = iTicketDao.findAll();
        System.out.println(ticketList);
        iUserDao.delete_by_name("John Doe");
        iUserDao.delete_by_name("Bob Bobson");
        userList = iUserDao.findAll();
        System.out.println(userList);
    }
}
