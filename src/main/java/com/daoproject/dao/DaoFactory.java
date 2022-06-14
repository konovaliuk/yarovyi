package com.daoproject.dao;

import com.daoproject.connection.ConnectionPool;
import com.daoproject.dao.interfaces.*;

public class DaoFactory {
    public static IUserDao createUserDao(ConnectionPool connPool){
        return new UserDao(connPool);
    }
    public static IMasterDao createMasterDao(ConnectionPool connPool) { return new MasterDao(connPool);}
    public static IManagerDao createManagerDao(ConnectionPool connPool) { return new ManagerDao(connPool);}
    public static IRequestDao createRequestDao(ConnectionPool connPool) { return new RequestDao(connPool);}
    public static ITicketDao createTicketDao(ConnectionPool connPool) { return new TicketDao(connPool);}
}
