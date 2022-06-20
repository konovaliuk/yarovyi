package com.servletproject.dao;

import com.servletproject.dao.interfaces.*;

public class DaoFactory {
    public static IUserDao createUserDao(){
        return new UserDao();
    }
    public static IMasterDao createMasterDao() { return new MasterDao();}
    public static IManagerDao createManagerDao() { return new ManagerDao();}
    public static IRequestDao createRequestDao() { return new RequestDao();}
    public static ITicketDao createTicketDao() { return new TicketDao();}
    public static IReviewDao createRewievDao() { return new ReviewDao();}
}
